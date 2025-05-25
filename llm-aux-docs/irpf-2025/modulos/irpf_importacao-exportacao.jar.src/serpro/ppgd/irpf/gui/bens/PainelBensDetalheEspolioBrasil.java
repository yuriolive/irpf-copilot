/*     */ package serpro.ppgd.irpf.gui.bens;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.LayoutStyle;
/*     */ import serpro.ppgd.gui.xbeans.JEditCEP;
/*     */ import serpro.ppgd.gui.xbeans.JEditCodigo;
/*     */ import serpro.ppgd.gui.xbeans.autocomplete.JAutoCompleteEditCodigo;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.bens.Bem;
/*     */ import serpro.ppgd.irpf.gui.NavegadorHtml;
/*     */ import serpro.ppgd.irpf.util.AplicacaoPropertiesUtil;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ 
/*     */ 
/*     */ public class PainelBensDetalheEspolioBrasil
/*     */   extends JPanel
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private JButton btnConsultaCEP;
/*     */   private JEditCEP edtCEP;
/*     */   private JAutoCompleteEditCodigo edtMunicipio;
/*     */   
/*     */   public PainelBensDetalheEspolioBrasil() {
/*  31 */     initComponents();
/*     */   }
/*     */   private JEditCodigo edtUF; private JLabel jLabel10; private JLabel jLabel11; private JLabel jLabel9;
/*     */   public PainelBensDetalheEspolioBrasil(Bem bem) {
/*  35 */     initComponents();
/*     */     
/*  37 */     associarInformacao(bem);
/*     */   }
/*     */   
/*     */   public void associarInformacao(Bem bem) {
/*  41 */     this.edtUF.setInformacao((Informacao)bem.getUf());
/*  42 */     this.edtMunicipio.setInformacao((Informacao)bem.getMunicipio());
/*  43 */     this.edtCEP.setInformacao((Informacao)bem.getCep());
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
/*  55 */     this.edtUF = new JEditCodigo();
/*  56 */     this.jLabel10 = new JLabel();
/*  57 */     this.jLabel9 = new JLabel();
/*  58 */     this.btnConsultaCEP = new JButton();
/*  59 */     this.edtCEP = new JEditCEP();
/*  60 */     this.jLabel11 = new JLabel();
/*  61 */     this.edtMunicipio = new JAutoCompleteEditCodigo();
/*     */     
/*  63 */     setBackground(new Color(255, 255, 255));
/*     */     
/*  65 */     this.jLabel10.setFont(FontesUtil.FONTE_NORMAL);
/*  66 */     this.jLabel10.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  67 */     this.jLabel10.setText("Município");
/*     */     
/*  69 */     this.jLabel9.setFont(FontesUtil.FONTE_NORMAL);
/*  70 */     this.jLabel9.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  71 */     this.jLabel9.setText("UF");
/*     */     
/*  73 */     this.btnConsultaCEP.setText("Consulta CEP");
/*  74 */     this.btnConsultaCEP.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/*  76 */             PainelBensDetalheEspolioBrasil.this.btnConsultaCEPActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/*  80 */     this.jLabel11.setFont(FontesUtil.FONTE_NORMAL);
/*  81 */     this.jLabel11.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  82 */     this.jLabel11.setText("CEP");
/*     */     
/*  84 */     GroupLayout layout = new GroupLayout(this);
/*  85 */     setLayout(layout);
/*  86 */     layout.setHorizontalGroup(layout
/*  87 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  88 */         .addGroup(layout.createSequentialGroup()
/*  89 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  90 */             .addComponent(this.jLabel9)
/*  91 */             .addComponent((Component)this.edtUF, -2, 99, -2))
/*  92 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  93 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  94 */             .addComponent(this.jLabel10)
/*  95 */             .addComponent((Component)this.edtMunicipio, -2, 350, -2))
/*  96 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  97 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  98 */             .addGroup(layout.createSequentialGroup()
/*  99 */               .addComponent((Component)this.edtCEP, -2, 114, -2)
/* 100 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 101 */               .addComponent(this.btnConsultaCEP))
/* 102 */             .addComponent(this.jLabel11))
/* 103 */           .addContainerGap(-1, 32767)));
/*     */     
/* 105 */     layout.setVerticalGroup(layout
/* 106 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 107 */         .addGroup(layout.createSequentialGroup()
/* 108 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 109 */             .addComponent(this.jLabel10, GroupLayout.Alignment.TRAILING, -2, 14, -2)
/* 110 */             .addComponent(this.jLabel11)
/* 111 */             .addComponent(this.jLabel9))
/* 112 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 113 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
/* 114 */             .addComponent((Component)this.edtCEP, GroupLayout.Alignment.LEADING, -1, -1, 32767)
/* 115 */             .addComponent((Component)this.edtUF, GroupLayout.Alignment.LEADING, -1, -1, 32767)
/* 116 */             .addComponent((Component)this.edtMunicipio, -2, 27, -2)
/* 117 */             .addComponent(this.btnConsultaCEP, -2, 0, 32767))));
/*     */ 
/*     */     
/* 120 */     this.edtUF.getAccessibleContext().setAccessibleName("UF");
/* 121 */     this.edtCEP.getAccessibleContext().setAccessibleName("CEP");
/* 122 */     this.edtMunicipio.getAccessibleContext().setAccessibleName("Município");
/*     */   }
/*     */   
/*     */   private void btnConsultaCEPActionPerformed(ActionEvent evt) {
/* 126 */     NavegadorHtml.executarNavegadorComMsgErro(AplicacaoPropertiesUtil.getSiteCorreiosBuscaCEP());
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\bens\PainelBensDetalheEspolioBrasil.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */