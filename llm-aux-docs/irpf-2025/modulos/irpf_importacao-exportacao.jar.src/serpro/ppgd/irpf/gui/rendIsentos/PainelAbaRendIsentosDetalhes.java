/*     */ package serpro.ppgd.irpf.gui.rendIsentos;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.LayoutStyle;
/*     */ import javax.swing.ListCellRenderer;
/*     */ import serpro.ppgd.gui.xbeans.JEditAlfa;
/*     */ import serpro.ppgd.gui.xbeans.autocomplete.JAutoCompleteComboBox;
/*     */ import serpro.ppgd.gui.xbeans.autocomplete.JAutoCompleteEditCodigo;
/*     */ import serpro.ppgd.infraestrutura.PlataformaPPGD;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.PainelAbaAb;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoIf;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroAuxiliarAb;
/*     */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroOutrosRendimentos;
/*     */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroPensaoMolestiaGrave;
/*     */ import serpro.ppgd.irpf.rendIsentos.ItemRendIsentoGenerico;
/*     */ import serpro.ppgd.irpf.rendIsentos.RendIsentos;
/*     */ import serpro.ppgd.irpf.rendpj.RendPJTitular;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.irpf.tabelas.TabelaAliquotasIRPF;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.Codigo;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ public class PainelAbaRendIsentosDetalhes
/*     */   extends PainelAbaAb {
/*     */   private PainelDemonstrativoIf painelPai;
/*     */   private ItemQuadroAuxiliarAb item;
/*     */   private ItemRendIsentoGenerico itemInicial;
/*     */   private String codTipoSelecionado;
/*  45 */   private String descricaoCompleta = ""; private PainelAbaAb painelDetalheExibido; private boolean primeiraTrocaTipoItem; private boolean emEdicao; private boolean novoItemBem; private boolean cancelou = false; private static final String HELP_ID = "Fichas da Declaração/Rendimentos Isentos e Não Tributáveis";
/*     */   public JAutoCompleteEditCodigo edtAutoCompTipoRendIsentos;
/*     */   private JEditAlfa edtTipoRend;
/*     */   private JPanel jPanel1;
/*     */   private JLabel lblNome;
/*     */   private JPanel painelTela;
/*     */   
/*     */   public PainelAbaRendIsentosDetalhes(PainelDemonstrativoIf painelPai, String codTipo, ItemQuadroAuxiliarAb itemSelecionado, boolean emEdicao, boolean novoItemBem) {
/*  53 */     super(painelPai);
/*  54 */     this.primeiraTrocaTipoItem = false;
/*  55 */     this.painelPai = painelPai;
/*  56 */     this.item = itemSelecionado;
/*  57 */     this.emEdicao = emEdicao;
/*  58 */     this.novoItemBem = novoItemBem;
/*  59 */     this.itemInicial = new ItemRendIsentoGenerico();
/*  60 */     PlataformaPPGD.getPlataforma().setHelpID((JComponent)this, "Fichas da Declaração/Rendimentos Isentos e Não Tributáveis");
/*     */     
/*  62 */     initComponents();
/*  63 */     JAutoCompleteComboBox combo = (JAutoCompleteComboBox)this.edtAutoCompTipoRendIsentos.getComponenteEditor();
/*  64 */     combo.setRenderer((ListCellRenderer)new JAutoCompleteComboRendererTooltip());
/*     */     
/*  66 */     adicionarObservadorSelecaoTipoRendimento();
/*     */ 
/*     */     
/*  69 */     if (emEdicao) {
/*  70 */       gravarItemInicial();
/*  71 */       combo.setSelectedItemPorCodigo(codTipo);
/*  72 */       this.primeiraTrocaTipoItem = true;
/*     */ 
/*     */       
/*  75 */       ((JComboBox)this.edtAutoCompTipoRendIsentos.getComponenteEditor()).setEnabled(false);
/*     */ 
/*     */       
/*  78 */       this.edtAutoCompTipoRendIsentos.setVisible(false);
/*  79 */       this.edtTipoRend.setVisible(true);
/*  80 */       this.edtTipoRend.getInformacao().setConteudo(this.codTipoSelecionado + " - " + this.codTipoSelecionado);
/*  81 */       this.edtTipoRend.getInformacao().setReadOnly(true);
/*     */     } else {
/*  83 */       this.edtTipoRend.setVisible(false);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void adicionarObservadorSelecaoTipoRendimento() {
/*  88 */     Codigo codigo = (Codigo)this.edtAutoCompTipoRendIsentos.getInformacao();
/*  89 */     codigo.addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/*  92 */             if ("-1".equals(valorNovo)) {
/*     */               
/*  94 */               String codTipoSelecionadoAnterior = (String)valorAntigo;
/*  95 */               if (codTipoSelecionadoAnterior.length() == 2 && (
/*  96 */                 RendIsentos.heRendIsentoTipo01X(Integer.valueOf(codTipoSelecionadoAnterior).intValue()) || 
/*  97 */                 RendIsentos.heRendIsentoTipo06X(Integer.valueOf(codTipoSelecionadoAnterior).intValue()))) {
/*  98 */                 PainelAbaRendIsentosDetalhes.this.item.getValor().setConteudo(PainelAbaRendIsentosDetalhes.this.itemInicial.getValor());
/*     */               }
/* 100 */               else if (codTipoSelecionadoAnterior.length() == 2 && (
/* 101 */                 RendIsentos.heRendIsentoTipo02X(Integer.valueOf(codTipoSelecionadoAnterior).intValue()) || 
/* 102 */                 RendIsentos.heRendIsentoTipo03X(Integer.valueOf(codTipoSelecionadoAnterior).intValue()) || 
/* 103 */                 RendIsentos.heRendIsentoTipo03XPensaoAlimenticia(Integer.valueOf(codTipoSelecionadoAnterior).intValue()) || 
/* 104 */                 RendIsentos.heRendIsentoTipo04X(Integer.valueOf(codTipoSelecionadoAnterior).intValue()) || 
/* 105 */                 RendIsentos.heRendIsentoTipo05X(Integer.valueOf(codTipoSelecionadoAnterior).intValue()))) {
/* 106 */                 ((TableModelRendIsentos)((PainelAbaRendIsentosLista)PainelAbaRendIsentosDetalhes.this.getPainelPai().getAbas()[0]).getTabela().getModel()).removerItem(PainelAbaRendIsentosDetalhes.this.item, codTipoSelecionadoAnterior);
/*     */               } 
/* 108 */               PainelAbaRendIsentosDetalhes.this.painelTela.removeAll();
/* 109 */               PainelAbaRendIsentosDetalhes.this.painelDetalheExibido = null;
/* 110 */             } else if ("".equals(valorNovo)) {
/* 111 */               String codTipoSelecionadoAnterior = (String)valorAntigo;
/* 112 */               if (!"-1".equals(codTipoSelecionadoAnterior) && !codTipoSelecionadoAnterior.isEmpty() && PainelAbaRendIsentosDetalhes.this.item != null) {
/* 113 */                 ((TableModelRendIsentos)((PainelAbaRendIsentosLista)PainelAbaRendIsentosDetalhes.this.getPainelPai().getAbas()[0]).getTabela().getModel()).removerItem(PainelAbaRendIsentosDetalhes.this.item, codTipoSelecionadoAnterior);
/*     */               }
/* 115 */               PainelAbaRendIsentosDetalhes.this.painelTela.removeAll();
/* 116 */               PainelAbaRendIsentosDetalhes.this.painelDetalheExibido = null;
/*     */             } else {
/* 118 */               PainelAbaRendIsentosDetalhes.this.painelTela.removeAll();
/* 119 */               PainelAbaRendIsentosDetalhes.this.codTipoSelecionado = (String)valorNovo;
/*     */               
/* 121 */               PainelAbaRendIsentosDetalhes.this.descricaoCompleta = ((Codigo)PainelAbaRendIsentosDetalhes.this.edtAutoCompTipoRendIsentos.getInformacao()).getConteudoAtual(2);
/* 122 */               if (PainelAbaRendIsentosDetalhes.this.codTipoSelecionado.equals("01") || PainelAbaRendIsentosDetalhes.this.codTipoSelecionado.equals("02")) {
/* 123 */                 PainelAbaRendIsentosDetalhes.this.descricaoCompleta = ((Codigo)PainelAbaRendIsentosDetalhes.this.edtAutoCompTipoRendIsentos.getInformacao()).getConteudoAtual(3);
/* 124 */                 PainelAbaRendIsentosDetalhes.this.descricaoCompleta = PainelAbaRendIsentosDetalhes.this.descricaoCompleta.replaceAll("\\[negrito\\]", "<b>");
/* 125 */                 PainelAbaRendIsentosDetalhes.this.descricaoCompleta = PainelAbaRendIsentosDetalhes.this.descricaoCompleta.replaceAll("\\[/negrito\\]", "</b>");
/*     */               } 
/*     */ 
/*     */               
/* 129 */               String codTipoSelecionadoAnterior = (String)valorAntigo;
/* 130 */               if (codTipoSelecionadoAnterior.length() == 2 && (
/* 131 */                 RendIsentos.heRendIsentoTipo01X(Integer.valueOf(codTipoSelecionadoAnterior).intValue()) || 
/* 132 */                 RendIsentos.heRendIsentoTipo06X(Integer.valueOf(codTipoSelecionadoAnterior).intValue()))) {
/* 133 */                 PainelAbaRendIsentosDetalhes.this.item.getValor().setConteudo(PainelAbaRendIsentosDetalhes.this.itemInicial.getValor());
/*     */               }
/*     */               
/* 136 */               if (PainelAbaRendIsentosDetalhes.this.item != null && PainelAbaRendIsentosDetalhes.this.primeiraTrocaTipoItem) {
/* 137 */                 if (!"-1".equals(valorAntigo) && (codTipoSelecionadoAnterior.length() == 0 || (codTipoSelecionadoAnterior.length() > 0 && 
/* 138 */                   !RendIsentos.heRendIsentoTipo01X(Integer.valueOf(codTipoSelecionadoAnterior).intValue()) && 
/* 139 */                   !RendIsentos.heRendIsentoTipo06X(Integer.valueOf(codTipoSelecionadoAnterior).intValue())))) {
/* 140 */                   ((TableModelRendIsentos)((PainelAbaRendIsentosLista)PainelAbaRendIsentosDetalhes.this.getPainelPai().getAbas()[0]).getTabela().getModel()).removerItem(PainelAbaRendIsentosDetalhes.this.item, codTipoSelecionadoAnterior);
/*     */                 }
/* 142 */                 PainelAbaRendIsentosDetalhes.this.item = ((TableModelRendIsentos)((PainelAbaRendIsentosLista)PainelAbaRendIsentosDetalhes.this.getPainelPai().getAbas()[0]).getTabela().getModel()).novoItemRendimento(PainelAbaRendIsentosDetalhes.this.codTipoSelecionado);
/*     */               } else {
/* 144 */                 PainelAbaRendIsentosDetalhes.this.primeiraTrocaTipoItem = true;
/*     */               } 
/*     */ 
/*     */ 
/*     */               
/* 149 */               if (PainelAbaRendIsentosDetalhes.this.item == null) {
/* 150 */                 PainelAbaRendIsentosDetalhes.this.item = ((TableModelRendIsentos)((PainelAbaRendIsentosLista)PainelAbaRendIsentosDetalhes.this.getPainelPai().getAbas()[0]).getTabela().getModel()).novoItemRendimento(PainelAbaRendIsentosDetalhes.this.codTipoSelecionado);
/*     */               }
/*     */ 
/*     */               
/* 154 */               if (PainelAbaRendIsentosDetalhes.this.codTipoSelecionado.length() == 2 && (
/* 155 */                 RendIsentos.heRendIsentoTipo01X(Integer.valueOf(PainelAbaRendIsentosDetalhes.this.codTipoSelecionado).intValue()) || 
/* 156 */                 RendIsentos.heRendIsentoTipo06X(Integer.valueOf(PainelAbaRendIsentosDetalhes.this.codTipoSelecionado).intValue()))) {
/* 157 */                 PainelAbaRendIsentosDetalhes.this.itemInicial.setValor(PainelAbaRendIsentosDetalhes.this.item.getValor().naoFormatado());
/*     */               }
/*     */               
/* 160 */               int codTipo = Integer.valueOf(PainelAbaRendIsentosDetalhes.this.codTipoSelecionado).intValue();
/* 161 */               if (RendIsentos.heRendIsentoTipo01X(codTipo)) {
/* 162 */                 PainelAbaRendIsentosDetalhes.this.painelDetalheExibido = new PainelAbaDetalhes01X(null, PainelAbaRendIsentosDetalhes.this.codTipoSelecionado, PainelAbaRendIsentosDetalhes.this.descricaoCompleta, PainelAbaRendIsentosDetalhes.this.item, !PainelAbaRendIsentosDetalhes.this.emEdicao);
/* 163 */               } else if (RendIsentos.heRendIsentoTipo02X(codTipo)) {
/* 164 */                 PainelAbaRendIsentosDetalhes.this.painelDetalheExibido = new PainelAbaDetalhes02X(null, PainelAbaRendIsentosDetalhes.this.codTipoSelecionado, PainelAbaRendIsentosDetalhes.this.descricaoCompleta, PainelAbaRendIsentosDetalhes.this.item, true);
/* 165 */               } else if (RendIsentos.heRendIsentoTipo03X(codTipo)) {
/* 166 */                 PainelAbaRendIsentosDetalhes.this.painelDetalheExibido = new PainelAbaDetalhes03X(null, PainelAbaRendIsentosDetalhes.this.codTipoSelecionado, PainelAbaRendIsentosDetalhes.this.descricaoCompleta, PainelAbaRendIsentosDetalhes.this.item, true);
/* 167 */               } else if (RendIsentos.heRendIsentoTipo03XPensaoAlimenticia(codTipo)) {
/* 168 */                 PainelAbaRendIsentosDetalhes.this.painelDetalheExibido = new PainelAbaDetalhes03XPensaoAlimenticia(null, PainelAbaRendIsentosDetalhes.this.codTipoSelecionado, PainelAbaRendIsentosDetalhes.this.descricaoCompleta, PainelAbaRendIsentosDetalhes.this.item, true);
/* 169 */               } else if (RendIsentos.heRendIsentoTipo04X(codTipo)) {
/* 170 */                 PainelAbaRendIsentosDetalhes.this.painelDetalheExibido = new PainelAbaDetalhes04X(null, PainelAbaRendIsentosDetalhes.this.codTipoSelecionado, PainelAbaRendIsentosDetalhes.this.descricaoCompleta, PainelAbaRendIsentosDetalhes.this.item, true);
/* 171 */               } else if (RendIsentos.heRendIsentoTipo05X(codTipo)) {
/* 172 */                 PainelAbaRendIsentosDetalhes.this.painelDetalheExibido = new PainelAbaDetalhes05X(null, PainelAbaRendIsentosDetalhes.this.codTipoSelecionado, PainelAbaRendIsentosDetalhes.this.descricaoCompleta, PainelAbaRendIsentosDetalhes.this.item, true);
/* 173 */               } else if (RendIsentos.heRendIsentoTipo06X(codTipo)) {
/* 174 */                 PainelAbaRendIsentosDetalhes.this.painelDetalheExibido = new PainelAbaDetalhes06X(null, PainelAbaRendIsentosDetalhes.this.codTipoSelecionado, PainelAbaRendIsentosDetalhes.this.descricaoCompleta, PainelAbaRendIsentosDetalhes.this.item, !PainelAbaRendIsentosDetalhes.this.emEdicao);
/*     */               } 
/*     */ 
/*     */ 
/*     */               
/* 179 */               PainelAbaRendIsentosDetalhes.this.painelTela.add((Component)PainelAbaRendIsentosDetalhes.this.painelDetalheExibido);
/* 180 */               PainelAbaRendIsentosDetalhes.this.painelTela.revalidate();
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
/*     */ 
/*     */ 
/*     */   
/*     */   private void initComponents() {
/* 197 */     this.jPanel1 = new JPanel();
/* 198 */     this.lblNome = new JLabel();
/* 199 */     this.edtAutoCompTipoRendIsentos = new JAutoCompleteEditCodigo((Informacao)new Codigo(null, "Tipo rendimento isento", CadastroTabelasIRPF.recuperarTiposRendimentosIsentos()));
/* 200 */     this.painelTela = new JPanel();
/* 201 */     this.edtTipoRend = new JEditAlfa();
/*     */     
/* 203 */     setBackground(new Color(241, 245, 249));
/*     */     
/* 205 */     this.jPanel1.setBackground(Color.white);
/* 206 */     this.jPanel1.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/*     */     
/* 208 */     this.lblNome.setText("Tipo de Rendimento");
/* 209 */     this.lblNome.setAlignmentY(0.0F);
/*     */     
/* 211 */     this.edtAutoCompTipoRendIsentos.setToolTipText("Tipo de Rendimento");
/*     */     
/* 213 */     this.painelTela.setBackground(new Color(254, 254, 254));
/* 214 */     this.painelTela.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/* 215 */     this.painelTela.setLayout(new BoxLayout(this.painelTela, 2));
/*     */     
/* 217 */     this.edtTipoRend.setMaxChars(300);
/* 218 */     this.edtTipoRend.setName("");
/*     */     
/* 220 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 221 */     this.jPanel1.setLayout(jPanel1Layout);
/* 222 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout
/* 223 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 224 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 225 */           .addContainerGap()
/* 226 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 227 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 228 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 229 */                 .addComponent(this.painelTela, -1, -1, 32767)
/* 230 */                 .addGroup(jPanel1Layout.createSequentialGroup()
/* 231 */                   .addComponent((Component)this.edtAutoCompTipoRendIsentos, -2, 863, -2)
/* 232 */                   .addGap(0, 15, 32767)))
/* 233 */               .addContainerGap())
/* 234 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 235 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 236 */                 .addComponent(this.lblNome)
/* 237 */                 .addComponent((Component)this.edtTipoRend, -2, 863, -2))
/* 238 */               .addGap(0, 0, 32767)))));
/*     */     
/* 240 */     jPanel1Layout.setVerticalGroup(jPanel1Layout
/* 241 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 242 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 243 */           .addContainerGap()
/* 244 */           .addComponent(this.lblNome, -2, 16, -2)
/* 245 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 246 */           .addComponent((Component)this.edtAutoCompTipoRendIsentos, -2, -1, -2)
/* 247 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 248 */           .addComponent((Component)this.edtTipoRend, -2, -1, -2)
/* 249 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 250 */           .addComponent(this.painelTela, -1, 96, 32767)
/* 251 */           .addContainerGap()));
/*     */ 
/*     */     
/* 254 */     this.edtAutoCompTipoRendIsentos.getAccessibleContext().setAccessibleName("Tipo de Rendimento");
/* 255 */     this.edtAutoCompTipoRendIsentos.getAccessibleContext().setAccessibleDescription("");
/* 256 */     this.edtTipoRend.getAccessibleContext().setAccessibleName("Tipo de Rendimento");
/* 257 */     this.edtTipoRend.getAccessibleContext().setAccessibleDescription("");
/*     */     
/* 259 */     GroupLayout layout = new GroupLayout((Container)this);
/* 260 */     setLayout(layout);
/* 261 */     layout.setHorizontalGroup(layout
/* 262 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 263 */         .addGroup(layout.createSequentialGroup()
/* 264 */           .addContainerGap()
/* 265 */           .addComponent(this.jPanel1, -1, -1, 32767)
/* 266 */           .addContainerGap()));
/*     */     
/* 268 */     layout.setVerticalGroup(layout
/* 269 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 270 */         .addGroup(layout.createSequentialGroup()
/* 271 */           .addContainerGap()
/* 272 */           .addComponent(this.jPanel1, -1, -1, 32767)
/* 273 */           .addContainerGap()));
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
/*     */   public String getNomeAba() {
/* 287 */     return "Rendimentos";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComAjuda() {
/* 292 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComVoltar() {
/* 297 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void executaVoltar() {
/* 304 */     if (RendIsentos.TIPO_RENDISENTO_10.equals(this.codTipoSelecionado) && !ControladorGui.isReadOnly())
/*     */     {
/* 306 */       if (ControladorGui.getDemonstrativoAberto().getRendIsentos().isParcelaIsentaAposentadoriaCaso1(this.item.getValor(), (Valor)this.item.getValor13Salario())) {
/*     */         
/* 308 */         Valor salario13Calculado = this.item.getValor().operacao('/', "13");
/* 309 */         Valor novoValorCalculado = this.item.getValor().operacao('-', salario13Calculado);
/*     */         
/* 311 */         PainelMensagemRendimento65Caso1 painelCaso1 = new PainelMensagemRendimento65Caso1(this.item.getValor().formatado(), salario13Calculado.formatado(), novoValorCalculado.formatado());
/* 312 */         GuiUtil.exibeDialog(painelCaso1, true, "Aviso", false);
/*     */         
/* 314 */         if (painelCaso1.isConfirmar()) {
/* 315 */           this.item.getValor().setConteudo(novoValorCalculado);
/* 316 */           this.item.getValor13Salario().setConteudo(salario13Calculado);
/*     */         }
/*     */       
/* 319 */       } else if (ControladorGui.getDemonstrativoAberto().getRendIsentos().isParcelaIsentaAposentadoriaCaso2(this.item.getValor(), (Valor)this.item.getValor13Salario())) {
/*     */         
/* 321 */         PainelMensagemRendimento65Caso2 painelCaso2 = new PainelMensagemRendimento65Caso2();
/* 322 */         GuiUtil.exibeDialog(painelCaso2, true, "Aviso", false);
/*     */         
/* 324 */         if (painelCaso2.isConfirmar()) {
/* 325 */           this.item.getValor().setConteudo(TabelaAliquotasIRPF.ConstantesAliquotas.valorAjusteLimiteAnualFaixa1.getValor());
/* 326 */           this.item.getValor13Salario().setConteudo(TabelaAliquotasIRPF.ConstantesAliquotas.valorAjusteLimiteMEFaixa1mes1.getValor());
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 333 */     this.cancelou = false;
/*     */ 
/*     */     
/* 336 */     if (RendIsentos.TIPO_RENDISENTO_10.equals(this.codTipoSelecionado) && 
/* 337 */       !this.item.getTipoBeneficiario().isVazio() && !this.item.getCpfBeneficiario().isVazio() && !this.item.getNIFontePagadora().isVazio()) {
/* 338 */       RendPJTitular rendPJ = ControladorGui.getDemonstrativoAberto().limiteAposentadoriaExcedido(this.item);
/* 339 */       if (rendPJ != null) {
/* 340 */         String msg = MensagemUtil.getMensagem("rendisentos_limite_aposentadoria_excedido", new String[] { this.item.getCpfBeneficiario().formatado() });
/* 341 */         if (GuiUtil.mostrarConfirma((Component)this, msg)) {
/*     */           
/* 343 */           if (this.item.getValor().comparacao(">", rendPJ.getRendRecebidoPJ())) {
/* 344 */             this.item.getValor().append('-', rendPJ.getRendRecebidoPJ());
/*     */           } else {
/* 346 */             this.item.getValor().clear();
/*     */           } 
/*     */           
/* 349 */           if (this.item.getValor13Salario().comparacao(">", rendPJ.getDecimoTerceiro())) {
/* 350 */             this.item.getValor13Salario().append('-', rendPJ.getDecimoTerceiro());
/*     */           } else {
/* 352 */             this.item.getValor13Salario().clear();
/*     */           } 
/*     */           
/* 355 */           if (ControladorGui.getDemonstrativoAberto().getIdentificadorDeclaracao().getCpf().naoFormatado().equals(this.item.getCpfBeneficiario().naoFormatado())) {
/* 356 */             ControladorGui.getDemonstrativoAberto().getRendPJ().getColecaoRendPJTitular().add((ObjetoNegocio)rendPJ);
/*     */           } else {
/* 358 */             ControladorGui.getDemonstrativoAberto().getRendPJ().getColecaoRendPJDependente().add((ObjetoNegocio)rendPJ);
/*     */           } 
/*     */ 
/*     */           
/* 362 */           if (this.item.getValor().isVazio() && this.item.getValor13Salario().isVazio()) {
/* 363 */             ControladorGui.getDemonstrativoAberto().getRendIsentos().getParcIsentaAposentadoriaQuadroAuxiliar().itens().remove(this.item);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 369 */     ControladorGui.acionarPainel(getPainelPai());
/*     */   }
/*     */ 
/*     */   
/*     */   public JComponent getDefaultFocus() {
/* 374 */     System.out.println("edtAutoCompTipoRendIsentos ...");
/* 375 */     if (this.edtTipoRend.isVisible()) {
/* 376 */       return (JComponent)this.edtTipoRend;
/*     */     }
/* 378 */     return this.edtAutoCompTipoRendIsentos.getComponenteEditor();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTituloPainel() {
/* 384 */     boolean ehTransmitida = IRPFFacade.getInstancia().getDeclaracao().getCopiaIdentificador().isTransmitida();
/* 385 */     if (this.emEdicao) {
/* 386 */       if (ehTransmitida) {
/* 387 */         return "Detalhe Rendimento Isento e Não Tributável";
/*     */       }
/* 389 */       return "Editar Rendimento Isento e Não Tributável";
/*     */     } 
/*     */     
/* 392 */     return "Novo Rendimento Isento e Não Tributável";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTelaComMensagem() {
/* 398 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void preExibir() {
/* 403 */     System.out.println("pre conteudo selecionado:" + ((Codigo)this.edtAutoCompTipoRendIsentos.getInformacao()).getIndiceElementoTabela());
/* 404 */     super.preExibir();
/*     */     
/* 406 */     if (this.item != null && this.item.getCodBem().isVazio()) {
/* 407 */       if (this.painelDetalheExibido instanceof PainelAbaDetalhes02X) {
/* 408 */         ((PainelAbaDetalhes02X)this.painelDetalheExibido).ajustaTipoDependenteNVDA();
/*     */       }
/* 410 */       if (this.painelDetalheExibido instanceof PainelAbaDetalhes03X) {
/* 411 */         ((PainelAbaDetalhes03X)this.painelDetalheExibido).ajustaTipoDependenteNVDA();
/*     */       }
/* 413 */       if (this.painelDetalheExibido instanceof PainelAbaDetalhes04X) {
/* 414 */         ((PainelAbaDetalhes04X)this.painelDetalheExibido).ajustaTipoDependenteNVDA();
/*     */       }
/* 416 */       if (this.painelDetalheExibido instanceof PainelAbaDetalhes05X) {
/* 417 */         ((PainelAbaDetalhes05X)this.painelDetalheExibido).ajustaTipoDependenteNVDA();
/*     */       }
/* 419 */       if (this.painelDetalheExibido instanceof PainelAbaDetalhes03XPensaoAlimenticia) {
/* 420 */         ((PainelAbaDetalhes03XPensaoAlimenticia)this.painelDetalheExibido).ajustaTipoDependenteNVDA();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isTelaComCancelar() {
/* 426 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void executaCancelar() {
/* 431 */     this.cancelou = true;
/* 432 */     if (this.novoItemBem) {
/* 433 */       this.item.clear();
/* 434 */     } else if (!this.edtAutoCompTipoRendIsentos.getInformacao().isVazio()) {
/* 435 */       restaurarItemInicial();
/* 436 */       if (!this.emEdicao && 
/* 437 */         this.codTipoSelecionado.length() == 2 && 
/* 438 */         !RendIsentos.heRendIsentoTipo01X(Integer.valueOf(this.codTipoSelecionado).intValue()) && 
/* 439 */         !RendIsentos.heRendIsentoTipo06X(Integer.valueOf(this.codTipoSelecionado).intValue())) {
/* 440 */         ((TableModelRendIsentos)((PainelAbaRendIsentosLista)getPainelPai().getAbas()[0]).getTabela().getModel()).removerItem(this.item, this.codTipoSelecionado);
/*     */       }
/*     */     } 
/*     */     
/* 444 */     ControladorGui.acionarPainel(getPainelPai());
/*     */   }
/*     */   
/*     */   public void gravarItemInicial() {
/* 448 */     this.itemInicial.setCpfBeneficiario((this.item.getCpfBeneficiario() == null) ? "" : this.item.getCpfBeneficiario().naoFormatado());
/* 449 */     this.itemInicial.setNIFontePagadora((this.item.getNIFontePagadora() == null) ? "" : this.item.getNIFontePagadora().naoFormatado());
/* 450 */     this.itemInicial.setNomeFontePagadora((this.item.getNomeFontePagadora() == null) ? "" : this.item.getNomeFontePagadora().naoFormatado());
/* 451 */     this.itemInicial.setTipoBeneficiario((this.item.getTipoBeneficiario() == null) ? "" : this.item.getTipoBeneficiario().naoFormatado());
/* 452 */     this.itemInicial.setValor((this.item.getValor() == null) ? "" : this.item.getValor().naoFormatado());
/* 453 */     this.itemInicial.setValorRendimento((this.item.getValorRendimento() == null) ? "" : this.item.getValorRendimento().naoFormatado());
/* 454 */     this.itemInicial.setValor13Salario((this.item.getValor13Salario() == null) ? "" : this.item.getValor13Salario().naoFormatado());
/*     */     
/* 456 */     if (this.item instanceof ItemQuadroPensaoMolestiaGrave) {
/* 457 */       ItemQuadroPensaoMolestiaGrave itemAtual = (ItemQuadroPensaoMolestiaGrave)this.item;
/* 458 */       this.itemInicial.setValor13Salario((itemAtual.getValor13Salario() == null) ? "" : itemAtual.getValor13Salario().naoFormatado());
/* 459 */       this.itemInicial.setValorIRRF((itemAtual.getValorIRRF() == null) ? "" : itemAtual.getValorIRRF().naoFormatado());
/* 460 */       this.itemInicial.setValorIRRF13Salario((itemAtual.getValorIRRF13Salario() == null) ? "" : itemAtual.getValorIRRF13Salario().naoFormatado());
/* 461 */       this.itemInicial.setValorPrevidenciaOficial((itemAtual.getValorPrevidenciaOficial() == null) ? "" : itemAtual.getValorPrevidenciaOficial().naoFormatado());
/* 462 */       this.itemInicial.setValorTotal((itemAtual.getValorTotal() == null) ? "" : itemAtual.getValorTotal().naoFormatado());
/*     */     }
/* 464 */     else if (this.item instanceof ItemQuadroOutrosRendimentos) {
/* 465 */       ItemQuadroOutrosRendimentos itemAtual = (ItemQuadroOutrosRendimentos)this.item;
/* 466 */       this.itemInicial.setDescricaoRendimento((itemAtual.getDescricaoRendimento() == null) ? "" : itemAtual.getDescricaoRendimento().naoFormatado());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void restaurarItemInicial() {
/* 471 */     if (this.item.getTipoBeneficiario() != null) this.item.getTipoBeneficiario().setConteudo(this.itemInicial.getTipoBeneficiario()); 
/* 472 */     if (this.item.getCpfBeneficiario() != null) this.item.getCpfBeneficiario().setConteudo(this.itemInicial.getCpfBeneficiario()); 
/* 473 */     if (this.item.getNIFontePagadora() != null) this.item.getNIFontePagadora().setConteudo(this.itemInicial.getNIFontePagadora()); 
/* 474 */     if (this.item.getNomeFontePagadora() != null) this.item.getNomeFontePagadora().setConteudo(this.itemInicial.getNomeFontePagadora()); 
/* 475 */     if (this.item.getValorRendimento() != null) this.item.getValorRendimento().setConteudo(this.itemInicial.getValorRendimento()); 
/* 476 */     if (this.item.getValor() != null) this.item.getValor().setConteudo(this.itemInicial.getValor()); 
/* 477 */     if (this.item.getValor13Salario() != null) this.item.getValor13Salario().setConteudo(this.itemInicial.getValor13Salario());
/*     */     
/* 479 */     if (this.item instanceof ItemQuadroPensaoMolestiaGrave) {
/* 480 */       ItemQuadroPensaoMolestiaGrave itemAtual = (ItemQuadroPensaoMolestiaGrave)this.item;
/* 481 */       if (itemAtual.getValor13Salario() != null) itemAtual.getValor13Salario().setConteudo(this.itemInicial.getValor13Salario()); 
/* 482 */       if (itemAtual.getValorIRRF() != null) itemAtual.getValorIRRF().setConteudo(this.itemInicial.getValorIRRF()); 
/* 483 */       if (itemAtual.getValorIRRF13Salario() != null) itemAtual.getValorIRRF13Salario().setConteudo(this.itemInicial.getValorIRRF13Salario()); 
/* 484 */       if (itemAtual.getValorPrevidenciaOficial() != null) itemAtual.getValorPrevidenciaOficial().setConteudo(this.itemInicial.getValorPrevidenciaOficial()); 
/* 485 */       if (itemAtual.getValorTotal() != null) itemAtual.getValorTotal().setConteudo(this.itemInicial.getValorTotal());
/*     */     
/* 487 */     } else if (this.item instanceof ItemQuadroOutrosRendimentos) {
/* 488 */       ItemQuadroOutrosRendimentos itemAtual = (ItemQuadroOutrosRendimentos)this.item;
/* 489 */       if (itemAtual.getDescricaoRendimento() != null) itemAtual.getDescricaoRendimento().setConteudo(this.itemInicial.getDescricaoRendimento());
/*     */     
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getHelpID() {
/* 495 */     return "Fichas da Declaração/Rendimentos Isentos e Não Tributáveis";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComFavoritos() {
/* 500 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPodeSair() {
/* 506 */     boolean tipo10Incompleto = (RendIsentos.TIPO_RENDISENTO_10.equals(this.codTipoSelecionado) && (this.item.getTipoBeneficiario().isVazio() || this.item.getCpfBeneficiario().isVazio() || this.item.getNIFontePagadora().isVazio()));
/*     */     
/* 508 */     if (tipo10Incompleto && !this.cancelou) {
/*     */       
/* 510 */       GuiUtil.mostrarErro("rendisentos_aposentadoria_incompleta");
/* 511 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 515 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\rendIsentos\PainelAbaRendIsentosDetalhes.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */