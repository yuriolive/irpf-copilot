/*     */ package serpro.ppgd.irpf.gui.rendTributacaoExclusiva;
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
/*     */ import serpro.ppgd.gui.xbeans.JEditAlfa;
/*     */ import serpro.ppgd.gui.xbeans.autocomplete.JAutoCompleteComboBox;
/*     */ import serpro.ppgd.gui.xbeans.autocomplete.JAutoCompleteEditCodigo;
/*     */ import serpro.ppgd.infraestrutura.PlataformaPPGD;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.PainelAbaAb;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoIf;
/*     */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroAuxiliarAb;
/*     */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroOutrosRendimentos;
/*     */ import serpro.ppgd.irpf.rendTributacaoExclusiva.ItemRendTributExclusivaGenerico;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.negocio.Codigo;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ 
/*     */ 
/*     */ public class PainelAbaRendTributEclusivaDetalhes
/*     */   extends PainelAbaAb
/*     */ {
/*     */   private PainelDemonstrativoIf painelPai;
/*     */   private ItemQuadroAuxiliarAb item;
/*     */   private String codTipoSelecionado;
/*     */   private PainelAbaAb painelDetalheExibido;
/*  38 */   private String descricaoCompleta = ""; private boolean primeiraTrocaTipoItem; private ItemRendTributExclusivaGenerico itemInicial; private boolean emEdicao; private boolean novoItemBem; private static final String HELP_ID = "Fichas da Declaração/Rendimentos Sujeitos à Tributação Exclusiva//Definitiva"; public JAutoCompleteEditCodigo edtAutoCompTipoRendTributExclusiva; private JEditAlfa edtTipoRend;
/*     */   private JPanel jPanel1;
/*     */   private JLabel lblNome;
/*     */   private JPanel painelTela;
/*     */   
/*     */   public PainelAbaRendTributEclusivaDetalhes(PainelDemonstrativoIf painelPai, String codTipo, ItemQuadroAuxiliarAb itemSelecionado, boolean emEdicao, boolean novoItemBem) {
/*  44 */     super(painelPai);
/*  45 */     this.primeiraTrocaTipoItem = false;
/*  46 */     this.painelPai = painelPai;
/*  47 */     this.item = itemSelecionado;
/*  48 */     this.emEdicao = emEdicao;
/*  49 */     this.novoItemBem = novoItemBem;
/*  50 */     this.itemInicial = new ItemRendTributExclusivaGenerico();
/*  51 */     initComponents();
/*  52 */     adicionarObservadorSelecaoTipoRendimento();
/*  53 */     PlataformaPPGD.getPlataforma().setHelpID((JComponent)this, "Fichas da Declaração/Rendimentos Sujeitos à Tributação Exclusiva//Definitiva");
/*     */ 
/*     */     
/*  56 */     if (emEdicao) {
/*  57 */       gravarItemInicial();
/*  58 */       JAutoCompleteComboBox combo = (JAutoCompleteComboBox)this.edtAutoCompTipoRendTributExclusiva.getComponenteEditor();
/*  59 */       combo.setSelectedItemPorCodigo(codTipo);
/*  60 */       this.primeiraTrocaTipoItem = true;
/*     */ 
/*     */       
/*  63 */       ((JComboBox)this.edtAutoCompTipoRendTributExclusiva.getComponenteEditor()).setEnabled(false);
/*     */ 
/*     */       
/*  66 */       this.edtAutoCompTipoRendTributExclusiva.setVisible(false);
/*  67 */       this.edtTipoRend.setVisible(true);
/*  68 */       this.edtTipoRend.getInformacao().setConteudo(this.codTipoSelecionado + " - " + this.codTipoSelecionado);
/*  69 */       this.edtTipoRend.getInformacao().setReadOnly(true);
/*     */     } else {
/*  71 */       this.edtTipoRend.setVisible(false);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void adicionarObservadorSelecaoTipoRendimento() {
/*  76 */     Codigo codigo = (Codigo)this.edtAutoCompTipoRendTributExclusiva.getInformacao();
/*  77 */     codigo.addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/*  80 */             if ("-1".equals(valorNovo)) {
/*  81 */               String codTipoSelecionadoAnterior = (String)valorAntigo;
/*  82 */               if ("06".equals(codTipoSelecionadoAnterior) || "10".equals(codTipoSelecionadoAnterior) || "11"
/*  83 */                 .equals(codTipoSelecionadoAnterior) || "12".equals(codTipoSelecionadoAnterior)) {
/*  84 */                 ((TableModelRendTributExclusiva)((PainelAbaRendTributExclusivaLista)PainelAbaRendTributEclusivaDetalhes.this.getPainelPai().getAbas()[0]).getTabela().getModel()).removerItem(PainelAbaRendTributEclusivaDetalhes.this.item, codTipoSelecionadoAnterior);
/*     */               }
/*  86 */               PainelAbaRendTributEclusivaDetalhes.this.painelTela.removeAll();
/*  87 */               PainelAbaRendTributEclusivaDetalhes.this.painelDetalheExibido = null;
/*  88 */             } else if ("".equals(valorNovo)) {
/*  89 */               String codTipoSelecionadoAnterior = (String)valorAntigo;
/*  90 */               if (!"-1".equals(codTipoSelecionadoAnterior) && !codTipoSelecionadoAnterior.isEmpty() && PainelAbaRendTributEclusivaDetalhes.this.item != null) {
/*  91 */                 ((TableModelRendTributExclusiva)((PainelAbaRendTributExclusivaLista)PainelAbaRendTributEclusivaDetalhes.this.getPainelPai().getAbas()[0]).getTabela().getModel()).removerItem(PainelAbaRendTributEclusivaDetalhes.this.item, codTipoSelecionadoAnterior);
/*     */               }
/*  93 */               PainelAbaRendTributEclusivaDetalhes.this.painelTela.removeAll();
/*  94 */               PainelAbaRendTributEclusivaDetalhes.this.painelDetalheExibido = null;
/*     */             } else {
/*  96 */               PainelAbaRendTributEclusivaDetalhes.this.painelTela.removeAll();
/*  97 */               PainelAbaRendTributEclusivaDetalhes.this.codTipoSelecionado = (String)valorNovo;
/*  98 */               PainelAbaRendTributEclusivaDetalhes.this.descricaoCompleta = ((Codigo)PainelAbaRendTributEclusivaDetalhes.this.edtAutoCompTipoRendTributExclusiva.getInformacao()).getConteudoAtual(2);
/*  99 */               if (PainelAbaRendTributEclusivaDetalhes.this.item != null && PainelAbaRendTributEclusivaDetalhes.this.primeiraTrocaTipoItem) {
/* 100 */                 String codTipoSelecionadoAnterior = (String)valorAntigo;
/* 101 */                 if (!"-1".equals(codTipoSelecionadoAnterior)) {
/* 102 */                   ((TableModelRendTributExclusiva)((PainelAbaRendTributExclusivaLista)PainelAbaRendTributEclusivaDetalhes.this.getPainelPai().getAbas()[0]).getTabela().getModel()).removerItem(PainelAbaRendTributEclusivaDetalhes.this.item, codTipoSelecionadoAnterior);
/*     */                 }
/* 104 */                 PainelAbaRendTributEclusivaDetalhes.this.item = ((TableModelRendTributExclusiva)((PainelAbaRendTributExclusivaLista)PainelAbaRendTributEclusivaDetalhes.this.getPainelPai().getAbas()[0]).getTabela().getModel()).novoItemRendimento(PainelAbaRendTributEclusivaDetalhes.this.codTipoSelecionado);
/*     */               } else {
/* 106 */                 PainelAbaRendTributEclusivaDetalhes.this.primeiraTrocaTipoItem = true;
/*     */               } 
/*     */ 
/*     */ 
/*     */               
/* 111 */               if (PainelAbaRendTributEclusivaDetalhes.this.item == null) {
/* 112 */                 PainelAbaRendTributEclusivaDetalhes.this.item = ((TableModelRendTributExclusiva)((PainelAbaRendTributExclusivaLista)PainelAbaRendTributEclusivaDetalhes.this.getPainelPai().getAbas()[0]).getTabela().getModel()).novoItemRendimento(PainelAbaRendTributEclusivaDetalhes.this.codTipoSelecionado);
/*     */               }
/*     */               
/* 115 */               int codTipo = Integer.valueOf(PainelAbaRendTributEclusivaDetalhes.this.codTipoSelecionado).intValue();
/*     */               
/* 117 */               if (codTipo == 6 || codTipo == 10 || codTipo == 11) {
/* 118 */                 PainelAbaRendTributEclusivaDetalhes.this.painelDetalheExibido = new PainelAbaDetalhesTributExclusiva01X(null, PainelAbaRendTributEclusivaDetalhes.this.codTipoSelecionado, PainelAbaRendTributEclusivaDetalhes.this.descricaoCompleta, PainelAbaRendTributEclusivaDetalhes.this.item, true);
/*     */               }
/* 120 */               else if (codTipo == 99) {
/* 121 */                 PainelAbaRendTributEclusivaDetalhes.this.painelDetalheExibido = new PainelAbaDetalhesTributExclusiva02X(null, PainelAbaRendTributEclusivaDetalhes.this.codTipoSelecionado, PainelAbaRendTributEclusivaDetalhes.this.descricaoCompleta, PainelAbaRendTributEclusivaDetalhes.this.item, true);
/*     */               } 
/*     */ 
/*     */ 
/*     */               
/* 126 */               PainelAbaRendTributEclusivaDetalhes.this.painelTela.add((Component)PainelAbaRendTributEclusivaDetalhes.this.painelDetalheExibido);
/* 127 */               PainelAbaRendTributEclusivaDetalhes.this.painelTela.revalidate();
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
/*     */   
/*     */   private void initComponents() {
/* 145 */     this.jPanel1 = new JPanel();
/* 146 */     this.lblNome = new JLabel();
/* 147 */     this.edtAutoCompTipoRendTributExclusiva = new JAutoCompleteEditCodigo((Informacao)new Codigo(null, "Tipo rendimento tributação exclusiva", CadastroTabelasIRPF.recuperarTiposRendimentosTributacaoExclusiva()));
/* 148 */     this.painelTela = new JPanel();
/* 149 */     this.edtTipoRend = new JEditAlfa();
/*     */     
/* 151 */     setBackground(new Color(241, 245, 249));
/*     */     
/* 153 */     this.jPanel1.setBackground(Color.white);
/* 154 */     this.jPanel1.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/*     */     
/* 156 */     this.lblNome.setText("Tipo de Rendimento");
/* 157 */     this.lblNome.setAlignmentY(0.0F);
/*     */     
/* 159 */     this.edtAutoCompTipoRendTributExclusiva.setFocusable(false);
/*     */     
/* 161 */     this.painelTela.setBackground(new Color(254, 254, 254));
/* 162 */     this.painelTela.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/* 163 */     this.painelTela.setLayout(new BoxLayout(this.painelTela, 2));
/*     */     
/* 165 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 166 */     this.jPanel1.setLayout(jPanel1Layout);
/* 167 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout
/* 168 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 169 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 170 */           .addContainerGap()
/* 171 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 172 */             .addComponent(this.painelTela, -1, -1, 32767)
/* 173 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 174 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 175 */                 .addComponent((Component)this.edtTipoRend, GroupLayout.Alignment.LEADING, -1, -1, 32767)
/* 176 */                 .addComponent(this.lblNome, GroupLayout.Alignment.LEADING)
/* 177 */                 .addComponent((Component)this.edtAutoCompTipoRendTributExclusiva, GroupLayout.Alignment.LEADING, -1, 458, 32767))
/* 178 */               .addGap(413, 413, 413)))
/* 179 */           .addContainerGap()));
/*     */     
/* 181 */     jPanel1Layout.setVerticalGroup(jPanel1Layout
/* 182 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 183 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 184 */           .addContainerGap()
/* 185 */           .addComponent(this.lblNome, -2, 16, -2)
/* 186 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 187 */           .addComponent((Component)this.edtAutoCompTipoRendTributExclusiva, -2, -1, -2)
/* 188 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 189 */           .addComponent((Component)this.edtTipoRend, -2, -1, -2)
/* 190 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 191 */           .addComponent(this.painelTela, -1, 166, 32767)
/* 192 */           .addContainerGap()));
/*     */ 
/*     */     
/* 195 */     this.edtAutoCompTipoRendTributExclusiva.getAccessibleContext().setAccessibleName("Tipo de Rendimento");
/* 196 */     this.edtAutoCompTipoRendTributExclusiva.getAccessibleContext().setAccessibleDescription("");
/* 197 */     this.edtTipoRend.getAccessibleContext().setAccessibleName("Tipo de Rendimento");
/* 198 */     this.edtTipoRend.getAccessibleContext().setAccessibleDescription("");
/*     */     
/* 200 */     GroupLayout layout = new GroupLayout((Container)this);
/* 201 */     setLayout(layout);
/* 202 */     layout.setHorizontalGroup(layout
/* 203 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 204 */         .addGroup(layout.createSequentialGroup()
/* 205 */           .addContainerGap()
/* 206 */           .addComponent(this.jPanel1, -1, -1, 32767)
/* 207 */           .addContainerGap()));
/*     */     
/* 209 */     layout.setVerticalGroup(layout
/* 210 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 211 */         .addGroup(layout.createSequentialGroup()
/* 212 */           .addContainerGap()
/* 213 */           .addComponent(this.jPanel1, -1, -1, 32767)
/* 214 */           .addContainerGap()));
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
/* 228 */     return "Rendimentos";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComAjuda() {
/* 233 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComVoltar() {
/* 238 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void executaVoltar() {
/* 243 */     ControladorGui.acionarPainel(getPainelPai());
/*     */   }
/*     */ 
/*     */   
/*     */   public JComponent getDefaultFocus() {
/* 248 */     return this.edtAutoCompTipoRendTributExclusiva.getComponenteEditor();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloPainel() {
/* 253 */     boolean ehTransmitida = IRPFFacade.getInstancia().getDeclaracao().getCopiaIdentificador().isTransmitida();
/* 254 */     if (this.emEdicao) {
/* 255 */       if (ehTransmitida) {
/* 256 */         return "Detalhe Rendimento Sujeito à Tributação Exclusiva/Definitiva";
/*     */       }
/* 258 */       return "Editar Rendimento Sujeito à Tributação Exclusiva/Definitiva";
/*     */     } 
/*     */     
/* 261 */     return "Novo Rendimento Sujeito à Tributação Exclusiva/Definitiva";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void preExibir() {
/* 267 */     super.preExibir();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComCancelar() {
/* 272 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void executaCancelar() {
/* 277 */     if (this.novoItemBem) {
/* 278 */       this.item.clear();
/* 279 */     } else if (!this.edtAutoCompTipoRendTributExclusiva.getInformacao().isVazio()) {
/* 280 */       restaurarItemInicial();
/* 281 */       if (!this.emEdicao) {
/* 282 */         ((TableModelRendTributExclusiva)((PainelAbaRendTributExclusivaLista)getPainelPai().getAbas()[0]).getTabela().getModel()).removerItem(this.item, this.codTipoSelecionado);
/*     */       }
/*     */     } 
/* 285 */     ControladorGui.acionarPainel(getPainelPai());
/*     */   }
/*     */   
/*     */   public void gravarItemInicial() {
/* 289 */     this.itemInicial.setCpfBeneficiario((this.item.getCpfBeneficiario() == null) ? "" : this.item.getCpfBeneficiario().naoFormatado());
/* 290 */     this.itemInicial.setNIFontePagadora((this.item.getNIFontePagadora() == null) ? "" : this.item.getNIFontePagadora().naoFormatado());
/* 291 */     this.itemInicial.setNomeFontePagadora((this.item.getNomeFontePagadora() == null) ? "" : this.item.getNomeFontePagadora().naoFormatado());
/* 292 */     this.itemInicial.setTipoBeneficiario((this.item.getTipoBeneficiario() == null) ? "" : this.item.getTipoBeneficiario().naoFormatado());
/* 293 */     this.itemInicial.setValor((this.item.getValor() == null) ? "" : this.item.getValor().naoFormatado());
/*     */ 
/*     */     
/* 296 */     if (this.item instanceof ItemQuadroOutrosRendimentos) {
/* 297 */       ItemQuadroOutrosRendimentos itemAtual = (ItemQuadroOutrosRendimentos)this.item;
/* 298 */       this.itemInicial.setDescricaoRendimento((itemAtual.getDescricaoRendimento() == null) ? "" : itemAtual.getDescricaoRendimento().naoFormatado());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void restaurarItemInicial() {
/* 303 */     if (this.item.getTipoBeneficiario() != null) this.item.getTipoBeneficiario().setConteudo(this.itemInicial.getTipoBeneficiario()); 
/* 304 */     if (this.item.getCpfBeneficiario() != null) this.item.getCpfBeneficiario().setConteudo(this.itemInicial.getCpfBeneficiario()); 
/* 305 */     if (this.item.getNIFontePagadora() != null) this.item.getNIFontePagadora().setConteudo(this.itemInicial.getNIFontePagadora()); 
/* 306 */     if (this.item.getNomeFontePagadora() != null) this.item.getNomeFontePagadora().setConteudo(this.itemInicial.getNomeFontePagadora()); 
/* 307 */     if (this.item.getValor() != null) this.item.getValor().setConteudo(this.itemInicial.getValor());
/*     */ 
/*     */     
/* 310 */     if (this.item instanceof ItemQuadroOutrosRendimentos) {
/* 311 */       ItemQuadroOutrosRendimentos itemAtual = (ItemQuadroOutrosRendimentos)this.item;
/* 312 */       if (itemAtual.getDescricaoRendimento() != null) itemAtual.getDescricaoRendimento().setConteudo(this.itemInicial.getDescricaoRendimento());
/*     */     
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getHelpID() {
/* 318 */     return "Fichas da Declaração/Rendimentos Sujeitos à Tributação Exclusiva//Definitiva";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComFavoritos() {
/* 323 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\rendTributacaoExclusiva\PainelAbaRendTributEclusivaDetalhes.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */