/*     */ package serpro.ppgd.irpf.gui.resumo;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Point;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.LayoutStyle;
/*     */ import serpro.ppgd.gui.xbeans.JEditValor;
/*     */ import serpro.ppgd.gui.xbeans.JFlipComponentes;
/*     */ import serpro.ppgd.infraestrutura.PlataformaPPGD;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoAb;
/*     */ import serpro.ppgd.irpf.gui.componente.JDegradePanel;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ 
/*     */ public class PainelCalculoImposto extends PainelDemonstrativoAb {
/*     */   private static final long serialVersionUID = 1L;
/*  30 */   private SubPainelParcelamentoInfoBancarias subPainelParcelamentoInfoBancarias = new SubPainelParcelamentoInfoBancarias();
/*     */   private static final String HELP_ID_COMPLETA = "Fichas da Declaração/Resumo da Declaração/Cálculo do Imposto";
/*  32 */   private SubPainelDadosBancarios subPainelDadosBancarios = new SubPainelDadosBancarios();
/*     */   private static final String HELP_ID_SIMPLIFICADA = "Fichas da Declaração/Resumo da Declaração/Cálculo do Imposto (Desconto Simplificado)";
/*  34 */   private SubPainelCalculoImposto subPainelCalculoImposto = new SubPainelCalculoImposto();
/*     */   
/*  36 */   private SubPainelCalculoImpostoSimplificado subPainelCalculoImpostoSimplificado = new SubPainelCalculoImpostoSimplificado();
/*     */   private JEditValor edtValor;
/*     */   
/*     */   public PainelCalculoImposto() {
/*  40 */     initComponents();
/*  41 */     this.edtValor.getComponenteEditor().setFont(FontesUtil.FONTE_TITULO_NORMAL);
/*  42 */     adicionarObservadores();
/*     */   }
/*     */   
/*     */   private JFlipComponentes flipImpostoDevidoPago;
/*     */   private JFlipComponentes flipParcelamentoInfoBancarias;
/*     */   private JLabel lblImpostoPagarRestituir;
/*     */   private JLabel lblValor;
/*     */   private JPanel pnlImposto;
/*     */   private JPanel pnlImpostoPagarRestituir;
/*     */   private JDegradePanel pnlValor;
/*     */   
/*     */   private void initComponents() {
/*  54 */     this.flipImpostoDevidoPago = new JFlipComponentes();
/*  55 */     this.lblImpostoPagarRestituir = new JLabel();
/*  56 */     this.pnlImpostoPagarRestituir = new JPanel();
/*  57 */     this.pnlImposto = new JPanel();
/*  58 */     this.lblValor = new JLabel();
/*  59 */     this.pnlValor = new JDegradePanel();
/*  60 */     this.edtValor = new JEditValor();
/*  61 */     this.flipParcelamentoInfoBancarias = new JFlipComponentes();
/*     */     
/*  63 */     setBackground(new Color(241, 245, 249));
/*  64 */     setForeground(new Color(255, 255, 255));
/*     */     
/*  66 */     this.flipImpostoDevidoPago.setComponenteA((JComponent)this.subPainelCalculoImposto);
/*  67 */     this.flipImpostoDevidoPago.setComponenteB((JComponent)this.subPainelCalculoImpostoSimplificado);
/*     */     
/*  69 */     this.lblImpostoPagarRestituir.setFont(FontesUtil.FONTE_TITULO_NORMAL);
/*  70 */     this.lblImpostoPagarRestituir.setForeground(new Color(0, 74, 106));
/*  71 */     this.lblImpostoPagarRestituir.setLabelFor((Component)this.edtValor);
/*  72 */     this.lblImpostoPagarRestituir.setText("Imposto a Pagar");
/*     */     
/*  74 */     this.pnlImpostoPagarRestituir.setBackground(new Color(255, 255, 255));
/*  75 */     this.pnlImpostoPagarRestituir.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/*     */     
/*  77 */     this.pnlImposto.setBackground(new Color(255, 255, 255));
/*     */     
/*  79 */     this.lblValor.setFont(FontesUtil.FONTE_TITULO_NORMAL);
/*  80 */     this.lblValor.setForeground(new Color(0, 74, 106));
/*  81 */     this.lblValor.setLabelFor((Component)this.pnlValor);
/*  82 */     this.lblValor.setText("Valor");
/*     */     
/*  84 */     this.pnlValor.setBackground(new Color(212, 231, 233));
/*  85 */     this.pnlValor.setCorFinal(new Color(203, 224, 243));
/*  86 */     this.pnlValor.setCorInicial(new Color(180, 200, 222));
/*  87 */     this.pnlValor.setPontoFimDegrade(new Point(132, 0));
/*  88 */     this.pnlValor.setPontoInicioDegrade(new Point(132, 100));
/*  89 */     this.pnlValor.setPreferredSize(new Dimension(264, 100));
/*     */     
/*  91 */     GroupLayout pnlValorLayout = new GroupLayout((Container)this.pnlValor);
/*  92 */     this.pnlValor.setLayout(pnlValorLayout);
/*  93 */     pnlValorLayout.setHorizontalGroup(pnlValorLayout
/*  94 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  95 */         .addGroup(GroupLayout.Alignment.TRAILING, pnlValorLayout.createSequentialGroup()
/*  96 */           .addContainerGap(31, 32767)
/*  97 */           .addComponent((Component)this.edtValor, -2, 221, -2)
/*  98 */           .addContainerGap()));
/*     */     
/* 100 */     pnlValorLayout.setVerticalGroup(pnlValorLayout
/* 101 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 102 */         .addGroup(pnlValorLayout.createSequentialGroup()
/* 103 */           .addGap(36, 36, 36)
/* 104 */           .addComponent((Component)this.edtValor, -2, -1, -2)
/* 105 */           .addContainerGap(29, 32767)));
/*     */ 
/*     */     
/* 108 */     this.edtValor.getAccessibleContext().setAccessibleName(IRPFFacade.getInstancia().getDeclaracao().getResumo().getCalculoImposto().getImpostoRestituir().comparacao(">", "0,00") ? "Imposto a Restituir" : "Imposto a Pagar");
/*     */     
/* 110 */     GroupLayout pnlImpostoLayout = new GroupLayout(this.pnlImposto);
/* 111 */     this.pnlImposto.setLayout(pnlImpostoLayout);
/* 112 */     pnlImpostoLayout.setHorizontalGroup(pnlImpostoLayout
/* 113 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 114 */         .addGroup(pnlImpostoLayout.createSequentialGroup()
/* 115 */           .addContainerGap()
/* 116 */           .addGroup(pnlImpostoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 117 */             .addComponent((Component)this.pnlValor, -1, -1, 32767)
/* 118 */             .addComponent(this.lblValor))
/* 119 */           .addContainerGap()));
/*     */     
/* 121 */     pnlImpostoLayout.setVerticalGroup(pnlImpostoLayout
/* 122 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 123 */         .addGroup(pnlImpostoLayout.createSequentialGroup()
/* 124 */           .addGap(28, 28, 28)
/* 125 */           .addComponent(this.lblValor)
/* 126 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 127 */           .addComponent((Component)this.pnlValor, -2, -1, -2)
/* 128 */           .addContainerGap(22, 32767)));
/*     */ 
/*     */     
/* 131 */     this.flipParcelamentoInfoBancarias.setComponenteA((JComponent)this.subPainelParcelamentoInfoBancarias);
/* 132 */     this.flipParcelamentoInfoBancarias.setComponenteB((JComponent)this.subPainelDadosBancarios);
/*     */     
/* 134 */     GroupLayout pnlImpostoPagarRestituirLayout = new GroupLayout(this.pnlImpostoPagarRestituir);
/* 135 */     this.pnlImpostoPagarRestituir.setLayout(pnlImpostoPagarRestituirLayout);
/* 136 */     pnlImpostoPagarRestituirLayout.setHorizontalGroup(pnlImpostoPagarRestituirLayout
/* 137 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 138 */         .addGroup(pnlImpostoPagarRestituirLayout.createSequentialGroup()
/* 139 */           .addComponent(this.pnlImposto, -2, -1, -2)
/* 140 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 141 */           .addComponent((Component)this.flipParcelamentoInfoBancarias, -1, 427, 32767)));
/*     */     
/* 143 */     pnlImpostoPagarRestituirLayout.setVerticalGroup(pnlImpostoPagarRestituirLayout
/* 144 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 145 */         .addComponent(this.pnlImposto, -2, -1, -2)
/* 146 */         .addComponent((Component)this.flipParcelamentoInfoBancarias, -1, -1, 32767));
/*     */ 
/*     */     
/* 149 */     GroupLayout layout = new GroupLayout((Container)this);
/* 150 */     setLayout(layout);
/* 151 */     layout.setHorizontalGroup(layout
/* 152 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 153 */         .addGroup(layout.createSequentialGroup()
/* 154 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/* 155 */             .addGroup(layout.createSequentialGroup()
/* 156 */               .addContainerGap()
/* 157 */               .addComponent(this.lblImpostoPagarRestituir))
/* 158 */             .addGroup(layout.createSequentialGroup()
/* 159 */               .addGap(12, 12, 12)
/* 160 */               .addComponent(this.pnlImpostoPagarRestituir, -2, -1, -2))
/* 161 */             .addComponent((Component)this.flipImpostoDevidoPago, -1, -1, 32767))
/* 162 */           .addContainerGap(-1, 32767)));
/*     */     
/* 164 */     layout.setVerticalGroup(layout
/* 165 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 166 */         .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
/* 167 */           .addComponent((Component)this.flipImpostoDevidoPago, -1, 374, 32767)
/* 168 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 169 */           .addComponent(this.lblImpostoPagarRestituir)
/* 170 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 171 */           .addComponent(this.pnlImpostoPagarRestituir, -2, -1, -2)
/* 172 */           .addContainerGap()));
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
/*     */   private void adicionarObservadores() {
/* 187 */     IRPFFacade.getInstancia().getIdDeclaracaoAberto().getTipoDeclaracao().addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/* 191 */             PainelCalculoImposto.this.atualizaSubPainelImposto();
/* 192 */             PainelCalculoImposto.this.repaint();
/* 193 */             PainelCalculoImposto.this.revalidate();
/*     */           }
/*     */         });
/* 196 */     IRPFFacade.getInstancia().getDeclaracao().getResumo().getCalculoImposto().getImpostoRestituir().addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/* 200 */             PainelCalculoImposto.this.atualizaImpostoPagarRestituir();
/*     */             
/* 202 */             if (ControladorGui.getPainelAtualmenteExibido() == PainelCalculoImposto.this) {
/* 203 */               PainelCalculoImposto.this.preExibir();
/*     */             }
/*     */ 
/*     */             
/* 207 */             PainelCalculoImposto.this.repaint();
/* 208 */             PainelCalculoImposto.this.revalidate();
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloPainel() {
/* 215 */     return "Cálculo do Imposto";
/*     */   }
/*     */ 
/*     */   
/*     */   public ImageIcon getImagemTitulo() {
/* 220 */     return GuiUtil.getImage("/icones/png40px/RE_calc.png");
/*     */   }
/*     */ 
/*     */   
/*     */   private void configurarSubPainelDadosBancarios() {
/* 225 */     DeclaracaoIRPF dec = IRPFFacade.getInstancia().getDeclaracao();
/*     */     
/* 227 */     this.subPainelDadosBancarios.setDeclaracao(dec);
/* 228 */     this.subPainelDadosBancarios.configurarComponentes();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void preExibir() {
/* 234 */     this.subPainelParcelamentoInfoBancarias = new SubPainelParcelamentoInfoBancarias();
/* 235 */     this.subPainelDadosBancarios = new SubPainelDadosBancarios();
/* 236 */     this.flipParcelamentoInfoBancarias.setComponenteA((JComponent)this.subPainelParcelamentoInfoBancarias);
/* 237 */     this.flipParcelamentoInfoBancarias.setComponenteB((JComponent)this.subPainelDadosBancarios);
/* 238 */     atualizaSubPainelImposto();
/* 239 */     atualizaImpostoPagarRestituir();
/* 240 */     configurarSubPainelDadosBancarios();
/* 241 */     this.subPainelCalculoImpostoSimplificado.configurarVisualizacaoImpostoLei14754();
/* 242 */     this.subPainelCalculoImposto.configurarVisualizacaoImpostoLei14754();
/*     */   }
/*     */   
/*     */   private void atualizaSubPainelImposto() {
/* 246 */     DeclaracaoIRPF dec = IRPFFacade.getInstancia().getDeclaracao();
/* 247 */     if (dec.getIdentificadorDeclaracao() != null && dec.getIdentificadorDeclaracao().isCompleta()) {
/* 248 */       PlataformaPPGD.getPlataforma().setHelpID((JComponent)this, "Fichas da Declaração/Resumo da Declaração/Cálculo do Imposto");
/* 249 */       ControladorGui.alterarHelpId("Fichas da Declaração/Resumo da Declaração/Cálculo do Imposto");
/* 250 */       dec.getModeloCompleta().resumoCalculoImposto();
/* 251 */       dec.getModeloCompleta().aplicaValoresNaDeclaracao();
/* 252 */       this.flipImpostoDevidoPago.exibeComponenteA();
/*     */     } else {
/* 254 */       PlataformaPPGD.getPlataforma().setHelpID((JComponent)this, "Fichas da Declaração/Resumo da Declaração/Cálculo do Imposto (Desconto Simplificado)");
/* 255 */       ControladorGui.alterarHelpId("Fichas da Declaração/Resumo da Declaração/Cálculo do Imposto (Desconto Simplificado)");
/* 256 */       dec.getModeloSimplificada().resumoCalculoImposto();
/* 257 */       dec.getModeloSimplificada().aplicaValoresNaDeclaracao();
/* 258 */       this.flipImpostoDevidoPago.exibeComponenteB();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void atualizaImpostoPagarRestituir() {
/* 264 */     DeclaracaoIRPF dec = IRPFFacade.getInstancia().getDeclaracao();
/* 265 */     if (dec.getResumo().getCalculoImposto().getImpostoRestituir().comparacao(">", "0,00")) {
/* 266 */       this.lblImpostoPagarRestituir.setVisible(true);
/* 267 */       this.pnlImposto.setVisible(true);
/* 268 */       this.flipParcelamentoInfoBancarias.setVisible(true);
/* 269 */       this.lblImpostoPagarRestituir.setText("Imposto a Restituir");
/* 270 */       this.edtValor.setInformacao((Informacao)dec.getResumo().getCalculoImposto().getImpostoRestituir());
/* 271 */       this.flipParcelamentoInfoBancarias.exibeComponenteB();
/* 272 */     } else if (dec.getResumo().getCalculoImposto().getSaldoImpostoPagar().comparacao(">", "0,00")) {
/* 273 */       this.lblImpostoPagarRestituir.setVisible(true);
/* 274 */       this.pnlImposto.setVisible(true);
/* 275 */       this.flipParcelamentoInfoBancarias.setVisible(true);
/* 276 */       this.lblImpostoPagarRestituir.setText("Imposto a Pagar");
/* 277 */       this.edtValor.setInformacao((Informacao)dec.getResumo().getCalculoImposto().getSaldoImpostoPagar());
/* 278 */       this.flipParcelamentoInfoBancarias.exibeComponenteA();
/*     */     } else {
/* 280 */       this.lblImpostoPagarRestituir.setVisible(false);
/* 281 */       this.pnlImposto.setVisible(false);
/* 282 */       this.flipParcelamentoInfoBancarias.setVisible(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHelpID() {
/* 288 */     DeclaracaoIRPF dec = IRPFFacade.getInstancia().getDeclaracao();
/* 289 */     if (dec.getIdentificadorDeclaracao() != null && dec.getIdentificadorDeclaracao().isCompleta()) {
/* 290 */       return "Fichas da Declaração/Resumo da Declaração/Cálculo do Imposto";
/*     */     }
/* 292 */     return "Fichas da Declaração/Resumo da Declaração/Cálculo do Imposto (Desconto Simplificado)";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JComponent getDefaultFocus() {
/* 298 */     return ((PainelDemonstrativoAb)this.flipImpostoDevidoPago.getAtivo()).getDefaultFocus();
/*     */   }
/*     */   
/*     */   public JFlipComponentes getFlipParcelamentoInfoBancarias() {
/* 302 */     return this.flipParcelamentoInfoBancarias;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComFavoritos() {
/* 307 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\resumo\PainelCalculoImposto.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */