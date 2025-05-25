/*     */ package serpro.ppgd.irpf.gui.bens;
/*     */ import java.awt.AWTKeyStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.KeyStroke;
/*     */ import javax.swing.SwingUtilities;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import serpro.ppgd.gui.xbeans.autocomplete.JAutoCompleteEditCodigo;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.bens.Bem;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoAb;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.negocio.Codigo;
/*     */ import serpro.ppgd.negocio.ElementoTabela;
/*     */ 
/*     */ public class PainelSelecaoCodigoBem extends PainelDemonstrativoAb {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private Codigo codigo;
/*     */   private Bem bem;
/*     */   PainelDemonstrativoAb painelBensDetalhe;
/*     */   
/*     */   public PainelSelecaoCodigoBem(Bem bem, PainelDemonstrativoAb painelBensDetalhe) {
/*  35 */     this.bem = bem;
/*  36 */     this.painelBensDetalhe = painelBensDetalhe;
/*  37 */     this.codigo = new Codigo(null, "Código", filtrarTiposBensPorGrupoPais(bem.getGrupo().naoFormatado(), bem.getPais().naoFormatado()));
/*  38 */     initComponents();
/*  39 */     associarInformacao();
/*  40 */     inibirEnterNavegacaoFocoBotoes();
/*     */   }
/*     */   private JButton btnCancelar; private JButton btnOk; private JAutoCompleteEditCodigo edtCodigoBem; private JPanel jPanel2; private JLabel lblCodigo;
/*     */   private void associarInformacao() {
/*  44 */     this.edtCodigoBem.setInformacao((Informacao)this.codigo);
/*     */   }
/*     */ 
/*     */   
/*     */   private List<ElementoTabela> filtrarTiposBensPorGrupoPais(String grupo, String pais) {
/*  49 */     List<ElementoTabela> resultado = new ArrayList<>();
/*  50 */     if ((pais.equals("105") || pais.equals("")) && (grupo.equals("04") || grupo.equals("07"))) {
/*  51 */       for (ElementoTabela item : CadastroTabelasIRPF.recuperarTipoBens(this.bem.getGrupo().naoFormatado())) {
/*  52 */         String codigo = item.getConteudo(0);
/*  53 */         if (this.bem.isBemApenasBrasil(grupo, codigo)) {
/*  54 */           resultado.add(item);
/*     */         }
/*     */       } 
/*  57 */       return resultado;
/*     */     } 
/*  59 */     return CadastroTabelasIRPF.recuperarTipoBens(this.bem.getGrupo().naoFormatado());
/*     */   }
/*     */ 
/*     */   
/*     */   private void inibirEnterNavegacaoFocoBotoes() {
/*  64 */     Set<AWTKeyStroke> set = new HashSet<>();
/*  65 */     set.add(KeyStroke.getKeyStroke(9, 0));
/*  66 */     this.btnOk.setFocusTraversalKeys(0, set);
/*  67 */     this.btnCancelar.setFocusTraversalKeys(0, set);
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
/*  78 */     this.jPanel2 = new JPanel();
/*  79 */     this.lblCodigo = new JLabel();
/*  80 */     this.btnOk = new JButton();
/*  81 */     this.btnCancelar = new JButton();
/*  82 */     this.edtCodigoBem = new JAutoCompleteEditCodigo();
/*     */     
/*  84 */     setBackground(new Color(241, 245, 249));
/*     */     
/*  86 */     this.jPanel2.setBackground(new Color(255, 255, 255));
/*  87 */     this.jPanel2.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/*     */     
/*  89 */     this.lblCodigo.setFont(FontesUtil.FONTE_NORMAL);
/*  90 */     this.lblCodigo.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  91 */     this.lblCodigo.setText("Código:");
/*     */     
/*  93 */     this.btnOk.setMnemonic('O');
/*  94 */     this.btnOk.setText("OK");
/*  95 */     this.btnOk.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/*  97 */             PainelSelecaoCodigoBem.this.btnOkActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 101 */     this.btnCancelar.setMnemonic('C');
/* 102 */     this.btnCancelar.setText("Cancelar");
/* 103 */     this.btnCancelar.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 105 */             PainelSelecaoCodigoBem.this.btnCancelarActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 109 */     this.edtCodigoBem.setToolTipText("Código");
/*     */     
/* 111 */     GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
/* 112 */     this.jPanel2.setLayout((LayoutManager)jPanel2Layout);
/* 113 */     jPanel2Layout.setHorizontalGroup((GroupLayout.Group)jPanel2Layout
/* 114 */         .createParallelGroup(1)
/* 115 */         .add((GroupLayout.Group)jPanel2Layout.createSequentialGroup()
/* 116 */           .addContainerGap()
/* 117 */           .add(this.lblCodigo)
/* 118 */           .addPreferredGap(0)
/* 119 */           .add((Component)this.edtCodigoBem, -2, 485, -2)
/* 120 */           .addContainerGap(-1, 32767))
/* 121 */         .add(2, (GroupLayout.Group)jPanel2Layout.createSequentialGroup()
/* 122 */           .addContainerGap(-1, 32767)
/* 123 */           .add(this.btnOk)
/* 124 */           .addPreferredGap(1)
/* 125 */           .add(this.btnCancelar)
/* 126 */           .addContainerGap()));
/*     */ 
/*     */     
/* 129 */     jPanel2Layout.linkSize(new Component[] { this.btnCancelar, this.btnOk }, 1);
/*     */     
/* 131 */     jPanel2Layout.setVerticalGroup((GroupLayout.Group)jPanel2Layout
/* 132 */         .createParallelGroup(1)
/* 133 */         .add(2, (GroupLayout.Group)jPanel2Layout.createSequentialGroup()
/* 134 */           .addContainerGap()
/* 135 */           .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(1, false)
/* 136 */             .add(this.lblCodigo, -1, -1, 32767)
/* 137 */             .add((Component)this.edtCodigoBem, -1, -1, 32767))
/* 138 */           .addPreferredGap(1)
/* 139 */           .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(3)
/* 140 */             .add(this.btnCancelar)
/* 141 */             .add(this.btnOk))
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
/* 153 */           .add(this.jPanel2, -1, -1, 32767)
/* 154 */           .addContainerGap()));
/*     */     
/* 156 */     layout.setVerticalGroup((GroupLayout.Group)layout
/* 157 */         .createParallelGroup(1)
/* 158 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 159 */           .addContainerGap()
/* 160 */           .add(this.jPanel2, -1, -1, 32767)
/* 161 */           .addContainerGap()));
/*     */   }
/*     */ 
/*     */   
/*     */   private void btnOkActionPerformed(ActionEvent evt) {
/* 166 */     if (this.codigo.isVazio()) {
/* 167 */       GuiUtil.mostrarAviso(null, "Informe o novo código do bem.");
/*     */     } else {
/* 169 */       ((JDialog)SwingUtilities.getRoot((Component)this)).setVisible(false);
/* 170 */       int indiceSelecionado = ((JComboBox)this.edtCodigoBem.getComponenteEditor()).getSelectedIndex();
/* 171 */       if (this.painelBensDetalhe instanceof PainelBensDetalhe) {
/* 172 */         ((PainelBensDetalhe)this.painelBensDetalhe).atualizarCodigoBem(this.codigo.naoFormatado());
/* 173 */         ((JComboBox)((PainelBensDetalhe)this.painelBensDetalhe).getEdtCodigoBem().getComponenteEditor()).setSelectedIndex(indiceSelecionado);
/* 174 */       } else if (this.painelBensDetalhe instanceof PainelBensDetalheEspolio) {
/* 175 */         ((PainelBensDetalheEspolio)this.painelBensDetalhe).atualizarCodigoBem(this.codigo.naoFormatado());
/* 176 */         ((JComboBox)((PainelBensDetalheEspolio)this.painelBensDetalhe).getEdtCodigoBem().getComponenteEditor()).setSelectedIndex(indiceSelecionado);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void btnCancelarActionPerformed(ActionEvent evt) {
/* 182 */     ((JDialog)SwingUtilities.getRoot((Component)this)).setVisible(false);
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\bens\PainelSelecaoCodigoBem.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */