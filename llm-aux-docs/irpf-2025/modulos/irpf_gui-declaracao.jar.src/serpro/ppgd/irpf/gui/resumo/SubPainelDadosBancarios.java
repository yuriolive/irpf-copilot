/*     */ package serpro.ppgd.irpf.gui.resumo;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Desktop;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.event.ItemEvent;
/*     */ import java.awt.event.ItemListener;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.net.URI;
/*     */ import java.util.List;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JSeparator;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.LayoutStyle;
/*     */ import javax.swing.border.Border;
/*     */ import serpro.ppgd.gui.xbeans.JEditAlfa;
/*     */ import serpro.ppgd.gui.xbeans.JEditMascara;
/*     */ import serpro.ppgd.gui.xbeans.autocomplete.JAutoCompleteComboBox;
/*     */ import serpro.ppgd.gui.xbeans.autocomplete.JAutoCompleteEditCPF;
/*     */ import serpro.ppgd.gui.xbeans.autocomplete.JAutoCompleteEditCodigo;
/*     */ import serpro.ppgd.infraestrutura.PlataformaPPGD;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.bens.Bens;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoAb;
/*     */ import serpro.ppgd.irpf.gui.dialogs.PainelDadosRestituicao;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.irpf.resumo.CalculoImposto;
/*     */ import serpro.ppgd.irpf.resumo.ObservadorDebitoAutomatico;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.irpf.tabelas.CodigoTabelaLinks;
/*     */ import serpro.ppgd.irpf.util.IRPFUtil;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.Codigo;
/*     */ import serpro.ppgd.negocio.ElementoTabela;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SubPainelDadosBancarios
/*     */   extends PainelDemonstrativoAb
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  56 */   private WeakReference<DeclaracaoIRPF> weakDec = null;
/*     */   
/*  58 */   private JLabel lblTextoExplic1 = null;
/*     */   
/*  60 */   private JLabel lblTextoExplic2 = null;
/*     */   
/*  62 */   private JLabel lblCodigoBanco = null; private JCheckBox chkPix; private JAutoCompleteEditCodigo cmbBanco; private JAutoCompleteEditCPF cmbContas;
/*     */   private JEditMascara edtAgencia;
/*     */   private JEditMascara edtConta;
/*     */   private JEditAlfa edtDV;
/*     */   private JEditAlfa edtOperacao;
/*     */   private JSeparator jsSeparador;
/*     */   private JLabel lblAgencia;
/*     */   
/*     */   public SubPainelDadosBancarios() {
/*  71 */     initComponents();
/*     */   }
/*     */   private JLabel lblBanco; private JLabel lblConta; private JLabel lblContasBens; private JLabel lblContasBens1;
/*     */   private JLabel lblDV;
/*     */   
/*     */   public SubPainelDadosBancarios(JLabel lblTextoExplic1, JLabel lblTextoExplic2, JLabel lblCodigoBanco) {
/*  77 */     initComponents();
/*     */     
/*  79 */     this.lblTextoExplic1 = lblTextoExplic1;
/*  80 */     this.lblTextoExplic2 = lblTextoExplic2;
/*  81 */     this.lblCodigoBanco = lblCodigoBanco;
/*     */   }
/*     */   private JLabel lblInfoContas; private JLabel lblInfoPix; private JLabel lblOperacao; private JLabel lblTipoConta; private JPanel pnlInformacoesBancarias;
/*     */   
/*     */   private WeakReference<DeclaracaoIRPF> getWeakDec() {
/*  86 */     return this.weakDec;
/*     */   }
/*     */ 
/*     */   
/*     */   private void alterarTextoLblInfoContas() {
/*  91 */     this.lblInfoContas.addMouseListener(new MouseAdapter()
/*     */         {
/*     */           public void mouseClicked(MouseEvent evt) {
/*  94 */             GuiUtil.mostrarInfoHTML(SubPainelDadosBancarios.this.retornaUrlBancosRedeArrecadadora().getConteudo(2));
/*     */           }
/*     */           
/*     */           public void mouseEntered(MouseEvent evt) {
/*  98 */             SubPainelDadosBancarios.this.lblInfoContas.setCursor(new Cursor(12));
/*     */           }
/*     */           
/*     */           public void mouseExited(MouseEvent evt) {
/* 102 */             SubPainelDadosBancarios.this.lblInfoContas.setCursor(new Cursor(0));
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private ElementoTabela retornaUrlBancosRedeArrecadadora() {
/* 110 */     ElementoTabela elemento = new ElementoTabela();
/* 111 */     if (((DeclaracaoIRPF)this.weakDec.get()).getResumo().getCalculoImposto().getSaldoImpostoPagar().comparacao(">", "0,00")) {
/* 112 */       elemento.setConteudo(0, CadastroTabelasIRPF.recuperarDescricaoLink(CodigoTabelaLinks.CODIGO_LinkDebitoAutomatico));
/* 113 */       elemento.setConteudo(1, CadastroTabelasIRPF.recuperarURLLink(CodigoTabelaLinks.CODIGO_LinkDebitoAutomatico));
/* 114 */       elemento.setConteudo(2, "<html><body>Selecione uma das contas cadastradas na ficha de Bens e Direitos, <br> para programação do pagamento via débito automático. Somente <br> <a href=\"" + elemento.getConteudo(1) + "\" target=\"_blank\">" + elemento.getConteudo(0) + "</a> podem ser selecionados.<br><br></body></html>");
/*     */     } else {
/* 116 */       elemento.setConteudo(0, CadastroTabelasIRPF.recuperarDescricaoLink(CodigoTabelaLinks.CODIGO_LinkImpostoARestituir));
/* 117 */       elemento.setConteudo(1, CadastroTabelasIRPF.recuperarURLLink(CodigoTabelaLinks.CODIGO_LinkImpostoARestituir));
/* 118 */       elemento.setConteudo(2, "<html><body>Selecione uma das contas cadastradas na ficha de Bens e Direitos, <br> como a conta em que deseja receber a restituição, desde que seja em um dos <br> <a href=\"" + elemento.getConteudo(1) + "\" target=\"_blank\">" + elemento.getConteudo(0) + "</a>. É possível selecionar o recebimento de <br> restituição por Pix; nesse caso, a chave Pix é o CPF do titular da declaração.</body></html>");
/*     */     } 
/*     */     
/* 121 */     String texto = !elemento.getConteudo(1).equals("") ? ("<html><a href=''>" + elemento.getConteudo(0) + "</a></html>") : "<html>Contas desta declaração em bancos da rede arrecadadora</html>";
/* 122 */     this.lblContasBens.setText(texto);
/* 123 */     return elemento;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void alterarLblContasBens() {
/* 129 */     this.lblContasBens.setText("<html><a href=''>" + retornaUrlBancosRedeArrecadadora().getConteudo(0) + "</a></html>");
/* 130 */     this.lblContasBens.addMouseListener(new MouseAdapter()
/*     */         {
/*     */           public void mouseClicked(MouseEvent evt) {
/*     */             try {
/* 134 */               Desktop.getDesktop().browse(new URI(SubPainelDadosBancarios.this.retornaUrlBancosRedeArrecadadora().getConteudo(1)));
/* 135 */             } catch (Exception ex) {
/* 136 */               ex.printStackTrace();
/*     */             } 
/*     */           }
/*     */ 
/*     */           
/*     */           public void mouseEntered(MouseEvent evt) {
/* 142 */             PlataformaPPGD.getPlataforma().getJanelaPrincipal().setCursor(Cursor.getPredefinedCursor(12));
/*     */           }
/*     */ 
/*     */           
/*     */           public void mouseExited(MouseEvent evt) {
/* 147 */             PlataformaPPGD.getPlataforma().getJanelaPrincipal().setCursor(Cursor.getPredefinedCursor(0));
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDeclaracao(DeclaracaoIRPF dec) {
/* 155 */     this.weakDec = new WeakReference<>(dec);
/* 156 */     adicionarObservadores();
/* 157 */     atualizaLabelContaDebitoCredito();
/* 158 */     mostrarComboContas();
/* 159 */     alterarLblContasBens();
/*     */   }
/*     */   
/*     */   private void associaInformacao() {
/* 163 */     this.cmbBanco.setInformacao((Informacao)((DeclaracaoIRPF)this.weakDec.get()).getResumo().getCalculoImposto().getBanco());
/* 164 */     this.cmbBanco.getInformacao().addObservador(this.cmbBanco.getInformacao().getNomeCampo(), new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/* 168 */             if (valorNovo.toString().isEmpty()) {
/* 169 */               ((JAutoCompleteComboBox)SubPainelDadosBancarios.this.cmbBanco.getComponenteEditor()).setSelectedItem(null);
/*     */             }
/*     */           }
/*     */         });
/*     */     
/* 174 */     this.edtAgencia.setInformacao((Informacao)((DeclaracaoIRPF)this.weakDec.get()).getResumo().getCalculoImposto().getAgencia());
/* 175 */     this.edtOperacao.setInformacao((Informacao)((DeclaracaoIRPF)this.weakDec.get()).getResumo().getCalculoImposto().getOperacao());
/* 176 */     this.edtConta.setInformacao((Informacao)((DeclaracaoIRPF)this.weakDec.get()).getResumo().getCalculoImposto().getContaCredito());
/* 177 */     this.edtDV.setInformacao((Informacao)((DeclaracaoIRPF)this.weakDec.get()).getResumo().getCalculoImposto().getDvContaCredito());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void configurarTela(boolean debito, boolean painelAba) {
/* 183 */     if (!painelAba) {
/* 184 */       Color bg = new Color(241, 245, 249);
/* 185 */       this.pnlInformacoesBancarias.setBackground(bg);
/* 186 */       this.pnlInformacoesBancarias.getParent().setBackground(bg);
/* 187 */       this.pnlInformacoesBancarias.setBorder(BorderFactory.createEmptyBorder());
/*     */       
/* 189 */       this.chkPix.setBackground(bg);
/*     */     } else {
/* 191 */       Color bg = new Color(255, 255, 255);
/* 192 */       Color bgPai = new Color(241, 245, 249);
/* 193 */       this.pnlInformacoesBancarias.getParent().setBackground(bgPai);
/* 194 */       this.pnlInformacoesBancarias.setBackground(bg);
/* 195 */       Color color = new Color(212, 231, 233);
/* 196 */       this.pnlInformacoesBancarias.setBorder(BorderFactory.createLineBorder(color));
/*     */       
/* 198 */       this.chkPix.setBackground(bg);
/*     */     } 
/* 200 */     if (debito) {
/* 201 */       this.chkPix.setVisible(false);
/* 202 */       this.chkPix.setSelected(false);
/* 203 */       Dimension dimension = new Dimension(527, 222);
/* 204 */       this.pnlInformacoesBancarias.getParent().setPreferredSize(dimension);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void adicionarObservadores() {
/* 212 */     ((DeclaracaoIRPF)this.weakDec.get()).getResumo().getCalculoImposto().getImpostoRestituir().addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/* 216 */             SubPainelDadosBancarios.this.atualizaLabelContaDebitoCredito();
/* 217 */             SubPainelDadosBancarios.this.alterarTextoLblInfoContas();
/* 218 */             SubPainelDadosBancarios.this.repaint();
/*     */           }
/*     */         });
/*     */     
/* 222 */     ((DeclaracaoIRPF)this.weakDec.get()).getResumo().getCalculoImposto().getDebitoAutomatico().addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/* 226 */             SubPainelDadosBancarios.this.mostrarComboContas();
/* 227 */             SubPainelDadosBancarios.this.habilitarBancoDadosConta();
/* 228 */             SubPainelDadosBancarios.this.retornaUrlBancosRedeArrecadadora();
/*     */           }
/*     */         });
/*     */     
/* 232 */     ObservadorDebitoAutomatico observadorDebitoAutomatico = new ObservadorDebitoAutomatico(((DeclaracaoIRPF)this.weakDec.get()).getResumo().getCalculoImposto());
/* 233 */     observadorDebitoAutomatico.habilitaDesabilitaDadosBancarios();
/*     */   }
/*     */ 
/*     */   
/*     */   public void preExibir() {
/* 238 */     atualizaLabelContaDebitoCredito();
/*     */   }
/*     */ 
/*     */   
/*     */   public void configurarComponentes() {
/* 243 */     associaInformacao();
/* 244 */     preencherComboContas();
/*     */     
/* 246 */     final Codigo banco = ((DeclaracaoIRPF)this.weakDec.get()).getResumo().getCalculoImposto().getBanco();
/* 247 */     final Codigo tipoConta = ((DeclaracaoIRPF)this.weakDec.get()).getResumo().getCalculoImposto().getTipoConta();
/*     */ 
/*     */     
/* 250 */     this.chkPix.addItemListener(new ItemListener()
/*     */         {
/*     */           public void itemStateChanged(ItemEvent e)
/*     */           {
/* 254 */             if (e.getStateChange() == 1) {
/* 255 */               String codigo = "4";
/* 256 */               tipoConta.setConteudo(codigo);
/* 257 */             } else if (e.getStateChange() == 2) {
/* 258 */               tipoConta.clear();
/*     */             } 
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 264 */     final JComboBox jContas = (JComboBox)this.cmbContas.getComponenteEditor();
/* 265 */     jContas.addItemListener(new ItemListener()
/*     */         {
/*     */           public void itemStateChanged(ItemEvent e) {
/* 268 */             if (jContas.getSelectedItem() != null) {
/*     */ 
/*     */               
/* 271 */               ElementoTabela et = (ElementoTabela)jContas.getSelectedItem();
/* 272 */               String codigoBanco = et.getConteudo(2);
/* 273 */               String agencia = et.getConteudo(3);
/* 274 */               String conta = et.getConteudo(4);
/* 275 */               String dvConta = et.getConteudo(5);
/* 276 */               String nomeBanco = et.getConteudo(6);
/* 277 */               String operacao = et.getConteudo(7);
/* 278 */               String tipoContaCaixa = et.getConteudo(8);
/* 279 */               String inContaPagamento = et.getConteudo(9);
/* 280 */               String grupo = et.getConteudo(10);
/* 281 */               String codigoBem = et.getConteudo(11);
/* 282 */               SubPainelDadosBancarios.this.setarCampos(codigoBanco, agencia, conta, dvConta, nomeBanco, operacao, tipoContaCaixa, inContaPagamento, grupo, codigoBem);
/*     */             } 
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 288 */     banco.addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/* 292 */             if (nomePropriedade.equals("Banco")) {
/* 293 */               SubPainelDadosBancarios.this.configuraNumeroCaracteresDV(banco.naoFormatado(), tipoConta.naoFormatado());
/* 294 */               SubPainelDadosBancarios.this.configurarConta(banco, tipoConta);
/* 295 */               SubPainelDadosBancarios.this.habilitarCampoOperacao();
/*     */             } 
/*     */           }
/*     */         });
/*     */     
/* 300 */     tipoConta.addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/* 304 */             if (nomePropriedade.equals("Tipo de Conta")) {
/* 305 */               SubPainelDadosBancarios.this.configurarPix(tipoConta.naoFormatado());
/* 306 */               SubPainelDadosBancarios.this.mostrarComboContas();
/* 307 */               SubPainelDadosBancarios.this.lblTipoConta.setText(tipoConta.getConteudoAtual(1));
/* 308 */               SubPainelDadosBancarios.this.configuraNumeroCaracteresDV(banco.naoFormatado(), tipoConta.naoFormatado());
/* 309 */               SubPainelDadosBancarios.this.configurarConta(banco, tipoConta);
/* 310 */               SubPainelDadosBancarios.this.habilitarCampoOperacao();
/* 311 */               SubPainelDadosBancarios.this.setarVisibilidadeCampos(tipoConta.naoFormatado());
/* 312 */               SubPainelDadosBancarios.this.configurarTextoExplicativo(tipoConta.naoFormatado());
/*     */             } 
/*     */           }
/*     */         });
/*     */     
/* 317 */     configurarConta(banco, tipoConta);
/* 318 */     configuraNumeroCaracteresDV(banco.naoFormatado(), tipoConta.naoFormatado());
/* 319 */     habilitarCampoOperacao();
/*     */     
/* 321 */     this.chkPix.setSelected("4".equals(tipoConta.naoFormatado()));
/* 322 */     configurarPix(tipoConta.naoFormatado());
/* 323 */     mostrarComboContas();
/*     */     
/* 325 */     setarVisibilidadeCampos(tipoConta.naoFormatado());
/* 326 */     configurarTextoExplicativo(tipoConta.naoFormatado());
/* 327 */     habilitarBancoDadosConta();
/*     */     
/* 329 */     alterarTextoLblInfoContas();
/*     */     
/* 331 */     this.lblTipoConta.setText(tipoConta.getConteudoAtual(1));
/*     */   }
/*     */   
/*     */   public void preencherComboContas() {
/* 335 */     Bens bens = ((DeclaracaoIRPF)this.weakDec.get()).getBens();
/* 336 */     boolean impostoAPagar = ((DeclaracaoIRPF)this.weakDec.get()).getResumo().getCalculoImposto().getSaldoImpostoPagar().comparacao(">", "0,00");
/* 337 */     boolean flagDebito = impostoAPagar;
/*     */     
/* 339 */     List<ElementoTabela> contas = CadastroTabelasIRPF.obterBensComContasCadastradas(bens, flagDebito);
/*     */     
/* 341 */     this.cmbContas.setDados(contas);
/*     */     
/* 343 */     boolean dadosValidos = ((DeclaracaoIRPF)this.weakDec.get()).getResumo().getCalculoImposto().validarContaBancaria(contas);
/*     */     
/* 345 */     if (!dadosValidos) {
/* 346 */       ((DeclaracaoIRPF)this.weakDec.get()).getResumo().getCalculoImposto().getTipoConta().clear();
/*     */       
/* 348 */       this.cmbBanco.getInformacao().clear();
/*     */ 
/*     */ 
/*     */       
/* 352 */       limpaDadosConta();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void limpaDadosConta() {
/* 357 */     this.edtOperacao.getInformacao().setConteudo("");
/* 358 */     this.edtAgencia.getInformacao().setConteudo("");
/* 359 */     this.edtConta.getInformacao().setConteudo("");
/* 360 */     this.edtDV.getInformacao().setConteudo("");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void configurarPix(String codigo) {
/* 368 */     if (codigo.equals("4")) {
/*     */ 
/*     */       
/* 371 */       this.cmbBanco.getInformacao().clear();
/*     */ 
/*     */       
/* 374 */       limpaDadosConta();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setarVisibilidadeCampos(String codigo) {
/* 381 */     if ("4".equals(codigo)) {
/*     */ 
/*     */       
/* 384 */       this.lblBanco.setVisible(false);
/* 385 */       this.cmbBanco.setVisible(false);
/* 386 */       this.lblAgencia.setVisible(false);
/* 387 */       this.edtAgencia.setVisible(false);
/* 388 */       this.lblConta.setVisible(false);
/* 389 */       this.edtConta.setVisible(false);
/* 390 */       this.lblDV.setVisible(false);
/* 391 */       this.edtDV.setVisible(false);
/*     */       
/* 393 */       this.lblInfoContas.setVisible(false);
/* 394 */       this.lblContasBens.setVisible(false);
/* 395 */       this.cmbContas.setVisible(false);
/*     */       
/* 397 */       this.lblTipoConta.setVisible(false);
/*     */     } else {
/*     */       
/* 400 */       this.lblBanco.setVisible(true);
/* 401 */       this.cmbBanco.setVisible(true);
/* 402 */       this.lblAgencia.setVisible(true);
/* 403 */       this.edtAgencia.setVisible(true);
/* 404 */       this.lblConta.setVisible(true);
/* 405 */       this.edtConta.setVisible(true);
/* 406 */       this.lblDV.setVisible(true);
/* 407 */       this.edtDV.setVisible(true);
/*     */       
/* 409 */       this.lblInfoContas.setVisible(true);
/* 410 */       this.lblContasBens.setVisible(true);
/* 411 */       this.cmbContas.setVisible(true);
/*     */       
/* 413 */       this.lblTipoConta.setVisible(true);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void configurarTextoExplicativo(String codigo) {
/* 419 */     if (this.lblTextoExplic1 != null && this.lblTextoExplic2 != null) {
/* 420 */       if ("4".equals(codigo)) {
/* 421 */         this.lblTextoExplic1.setText(PainelDadosRestituicao.TEXTO_EXP_PIX);
/* 422 */         this.lblTextoExplic2.setText("");
/* 423 */         this.lblCodigoBanco.setText("");
/*     */       } else {
/* 425 */         this.lblTextoExplic1.setText(PainelDadosRestituicao.TEXTO_EXP_1);
/* 426 */         this.lblTextoExplic2.setText(PainelDadosRestituicao.TEXTO_EXP_2);
/* 427 */         this.lblCodigoBanco.setText(PainelDadosRestituicao.TEXTO_CODIGO_BANCO);
/*     */       }
/*     */     
/* 430 */     } else if ("4".equals(codigo)) {
/* 431 */       this.lblInfoPix.setText(PainelDadosRestituicao.TEXTO_EXP_PIX);
/*     */     } else {
/* 433 */       this.lblInfoPix.setText("");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void habilitarBancoDadosConta() {
/* 440 */     ((JComboBox)this.cmbBanco.getComponenteEditor()).setEnabled(false);
/* 441 */     ((JTextField)this.edtOperacao.getComponenteEditor()).setEnabled(false);
/* 442 */     ((JTextField)this.edtAgencia.getComponenteEditor()).setEnabled(false);
/* 443 */     ((JTextField)this.edtConta.getComponenteEditor()).setEnabled(false);
/* 444 */     ((JTextField)this.edtDV.getComponenteEditor()).setEnabled(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setarCampos(String codBanco, String agencia, String conta, String dvConta, String nomeBanco, String operacao, String tipoContaCaixa, String inContaPagamento, String grupo, String codigoBem) {
/* 455 */     this.edtOperacao.getInformacao().setConteudo(operacao);
/* 456 */     this.cmbBanco.getInformacao().setConteudo(codBanco);
/* 457 */     ElementoTabela elb = new ElementoTabela();
/* 458 */     elb.setConteudo(0, codBanco);
/* 459 */     elb.setConteudo(1, nomeBanco);
/* 460 */     ((JAutoCompleteComboBox)this.cmbBanco.getComponenteEditor()).setSelectedItem(elb);
/*     */     
/* 462 */     if ("1".equals(inContaPagamento)) {
/* 463 */       ((DeclaracaoIRPF)this.weakDec.get()).getResumo().getCalculoImposto().getTipoConta().setConteudo("3");
/* 464 */     } else if ("04".equals(grupo) && "01".equals(codigoBem)) {
/* 465 */       ((DeclaracaoIRPF)this.weakDec.get()).getResumo().getCalculoImposto().getTipoConta().setConteudo("2");
/* 466 */     } else if ("06".equals(grupo) && "01".equals(codigoBem)) {
/* 467 */       ((DeclaracaoIRPF)this.weakDec.get()).getResumo().getCalculoImposto().getTipoConta().setConteudo("1");
/*     */     } else {
/* 469 */       ((DeclaracaoIRPF)this.weakDec.get()).getResumo().getCalculoImposto().getTipoConta().clear();
/*     */     } 
/*     */     
/* 472 */     this.edtAgencia.getInformacao().setConteudo(agencia);
/* 473 */     this.edtConta.getInformacao().setConteudo(conta);
/* 474 */     this.edtDV.getInformacao().setConteudo(dvConta);
/*     */ 
/*     */     
/* 477 */     this.lblTipoConta.setText(((DeclaracaoIRPF)this.weakDec.get()).getResumo().getCalculoImposto().getTipoConta().getConteudoAtual(1));
/*     */     
/* 479 */     JComboBox jConta = (JComboBox)this.cmbContas.getComponenteEditor();
/* 480 */     jConta.setSelectedIndex(-1);
/*     */   }
/*     */ 
/*     */   
/*     */   private void configuraNumeroCaracteresDV(String banco, String codTipoConta) {
/* 485 */     String dv = this.edtDV.getConteudo().toString();
/* 486 */     if ("3".equals(codTipoConta)) {
/* 487 */       this.edtDV.setMaxChars(2);
/* 488 */       ((Alfa)this.edtDV.getInformacao()).setMaximoCaracteres(2);
/* 489 */     } else if (CalculoImposto.getBancosDebito().contains(banco)) {
/* 490 */       this.edtDV.setMaxChars(1);
/* 491 */       ((Alfa)this.edtDV.getInformacao()).setMaximoCaracteres(1);
/* 492 */       if (dv != null && dv.trim().length() > 1) {
/* 493 */         this.edtDV.getInformacao().setConteudo(dv.substring(0, 1));
/*     */       }
/*     */     }
/* 496 */     else if ("104".equals(banco)) {
/* 497 */       this.edtDV.setMaxChars(1);
/* 498 */       ((Alfa)this.edtDV.getInformacao()).setMaximoCaracteres(1);
/*     */     } else {
/* 500 */       this.edtDV.setMaxChars(2);
/* 501 */       ((Alfa)this.edtDV.getInformacao()).setMaximoCaracteres(2);
/*     */     } 
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
/*     */   private void configurarConta(Codigo banco, Codigo tipoConta) {
/* 515 */     this.edtConta.setMascara(gerarMascaraConta(20));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String gerarMascaraConta(int digitos) {
/* 522 */     return IRPFUtil.repetirString("*", digitos);
/*     */   }
/*     */   
/*     */   private void mostrarComboContas() {
/* 526 */     Bens bens = ((DeclaracaoIRPF)this.weakDec.get()).getBens();
/* 527 */     String tipoConta = ((DeclaracaoIRPF)this.weakDec.get()).getResumo().getCalculoImposto().getTipoConta().naoFormatado();
/* 528 */     boolean impostoArestituir = ((DeclaracaoIRPF)this.weakDec.get()).getResumo().getCalculoImposto().getImpostoRestituir().comparacao(">", "0,00");
/* 529 */     boolean impostoAPagar = ((DeclaracaoIRPF)this.weakDec.get()).getResumo().getCalculoImposto().getSaldoImpostoPagar().comparacao(">", "0,00");
/* 530 */     boolean flagDebito = impostoAPagar;
/* 531 */     if (CadastroTabelasIRPF.obterBensComContasCadastradas(bens, flagDebito).size() > 0 && (impostoArestituir || impostoAPagar))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 537 */       if ((impostoArestituir && !"4".equals(tipoConta)) || (impostoAPagar && "autorizado"
/* 538 */         .equals(((DeclaracaoIRPF)this.weakDec.get()).getResumo().getCalculoImposto().getDebitoAutomatico().naoFormatado()))) {
/* 539 */         ((JComboBox)this.cmbContas.getComponenteEditor()).setEnabled(true);
/*     */       } else {
/* 541 */         ((JComboBox)this.cmbContas.getComponenteEditor()).setEnabled(false);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void habilitarCampoOperacao() {
/* 553 */     if (this.weakDec.get() != null && ((DeclaracaoIRPF)this.weakDec
/* 554 */       .get()).getResumo().getCalculoImposto().temOperacao()) {
/* 555 */       this.lblOperacao.setVisible(true);
/* 556 */       this.edtOperacao.setVisible(true);
/*     */     } else {
/* 558 */       this.lblOperacao.setVisible(false);
/* 559 */       this.edtOperacao.setVisible(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void atualizaLabelContaDebitoCredito() {
/* 570 */     if (((DeclaracaoIRPF)this.weakDec.get()).getResumo().getCalculoImposto().getImpostoRestituir().comparacao(">", "0,00")) {
/* 571 */       this.lblConta.setText("Conta para crédito");
/*     */     } else {
/* 573 */       this.lblConta.setText("Conta para débito");
/*     */     } 
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
/*     */   private void initComponents() {
/* 587 */     this.pnlInformacoesBancarias = new JPanel();
/* 588 */     this.lblContasBens = new JLabel();
/* 589 */     this.cmbContas = new JAutoCompleteEditCPF();
/* 590 */     this.jsSeparador = new JSeparator();
/* 591 */     this.cmbBanco = new JAutoCompleteEditCodigo();
/* 592 */     this.lblBanco = new JLabel();
/* 593 */     this.edtAgencia = new JEditMascara();
/* 594 */     this.lblAgencia = new JLabel();
/* 595 */     this.edtConta = new JEditMascara();
/* 596 */     this.lblConta = new JLabel();
/* 597 */     this.lblDV = new JLabel();
/* 598 */     this.edtDV = new JEditAlfa();
/* 599 */     this.lblOperacao = new JLabel();
/* 600 */     this.edtOperacao = new JEditAlfa();
/* 601 */     this.chkPix = new JCheckBox();
/* 602 */     this.lblInfoPix = new JLabel();
/* 603 */     this.lblTipoConta = new JLabel();
/* 604 */     this.lblInfoContas = new JLabel();
/* 605 */     this.lblContasBens1 = new JLabel();
/*     */     
/* 607 */     setBackground(new Color(255, 255, 255));
/* 608 */     setPreferredSize(new Dimension(550, 273));
/*     */     
/* 610 */     this.pnlInformacoesBancarias.setBackground(new Color(255, 255, 255));
/* 611 */     this.pnlInformacoesBancarias.setBorder(BorderFactory.createTitledBorder(null, "Informações bancárias", 0, 0, FontesUtil.FONTE_TITULO_NORMAL, new Color(0, 74, 106)));
/*     */     
/* 613 */     this.lblContasBens.setFont(FontesUtil.FONTE_NORMAL);
/* 614 */     this.lblContasBens.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 615 */     this.lblContasBens.setLabelFor((Component)this.cmbBanco);
/* 616 */     this.lblContasBens.setText("bancos da rede arrecadadora");
/* 617 */     this.lblContasBens.setBorder((Border)null);
/* 618 */     this.lblContasBens.setMaximumSize(new Dimension(165, 20));
/* 619 */     this.lblContasBens.setMinimumSize(new Dimension(165, 20));
/* 620 */     this.lblContasBens.setName("");
/* 621 */     this.lblContasBens.setPreferredSize(new Dimension(165, 20));
/*     */     
/* 623 */     this.cmbBanco.setToolTipText("Informe o código do banco ou parte do nome para pesquisa; ou selecione o banco utilizando a seta à direita.");
/*     */     
/* 625 */     this.lblBanco.setFont(FontesUtil.FONTE_NORMAL);
/* 626 */     this.lblBanco.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 627 */     this.lblBanco.setLabelFor((Component)this.cmbBanco);
/* 628 */     this.lblBanco.setText("Banco");
/*     */     
/* 630 */     this.edtAgencia.setCaracteresValidos("0123456789 ");
/* 631 */     this.edtAgencia.setMascara("****");
/*     */     
/* 633 */     this.lblAgencia.setFont(FontesUtil.FONTE_NORMAL);
/* 634 */     this.lblAgencia.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 635 */     this.lblAgencia.setLabelFor((Component)this.edtAgencia);
/* 636 */     this.lblAgencia.setText("Agência (sem DV)");
/*     */     
/* 638 */     this.edtConta.setCaracteresValidos("0123456789 ");
/* 639 */     this.edtConta.setMascara("********************");
/*     */     
/* 641 */     this.lblConta.setFont(FontesUtil.FONTE_NORMAL);
/* 642 */     this.lblConta.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 643 */     this.lblConta.setLabelFor((Component)this.edtConta);
/* 644 */     this.lblConta.setText("Conta para crédito");
/*     */     
/* 646 */     this.lblDV.setFont(FontesUtil.FONTE_NORMAL);
/* 647 */     this.lblDV.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 648 */     this.lblDV.setLabelFor((Component)this.edtDV);
/* 649 */     this.lblDV.setText("DV");
/*     */     
/* 651 */     this.edtDV.setMaxChars(2);
/* 652 */     ((JTextField)this.edtDV.getComponenteEditor()).setHorizontalAlignment(0);
/*     */     
/* 654 */     this.lblOperacao.setFont(FontesUtil.FONTE_NORMAL);
/* 655 */     this.lblOperacao.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 656 */     this.lblOperacao.setLabelFor((Component)this.edtOperacao);
/* 657 */     this.lblOperacao.setText("Operação");
/*     */     
/* 659 */     this.edtOperacao.setMaxChars(3);
/* 660 */     ((JTextField)this.edtDV.getComponenteEditor()).setHorizontalAlignment(0);
/*     */     
/* 662 */     this.chkPix.setBackground(new Color(255, 255, 255));
/* 663 */     this.chkPix.setText("Pix (a chave será o CPF do titular da declaração)");
/*     */     
/* 665 */     this.lblInfoPix.setFont(FontesUtil.FONTE_NORMAL);
/* 666 */     this.lblInfoPix.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 667 */     this.lblInfoPix.setHorizontalAlignment(0);
/*     */     
/* 669 */     this.lblTipoConta.setFont(FontesUtil.FONTE_NORMAL);
/* 670 */     this.lblTipoConta.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 671 */     this.lblTipoConta.setHorizontalAlignment(2);
/* 672 */     this.lblTipoConta.setText("Tipo Conta");
/*     */     
/* 674 */     this.lblInfoContas.setFont(FontesUtil.FONTE_TITULO_MAIOR);
/* 675 */     this.lblInfoContas.setForeground(new Color(0, 74, 106));
/* 676 */     this.lblInfoContas.setIcon(new ImageIcon(getClass().getResource("/icones/png10px/info.png")));
/*     */     
/* 678 */     this.lblContasBens1.setFont(FontesUtil.FONTE_NORMAL);
/* 679 */     this.lblContasBens1.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 680 */     this.lblContasBens1.setLabelFor((Component)this.cmbBanco);
/* 681 */     this.lblContasBens1.setText("Contas desta declaração em");
/*     */     
/* 683 */     GroupLayout pnlInformacoesBancariasLayout = new GroupLayout(this.pnlInformacoesBancarias);
/* 684 */     this.pnlInformacoesBancarias.setLayout(pnlInformacoesBancariasLayout);
/* 685 */     pnlInformacoesBancariasLayout.setHorizontalGroup(pnlInformacoesBancariasLayout
/* 686 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 687 */         .addGroup(pnlInformacoesBancariasLayout.createSequentialGroup()
/* 688 */           .addContainerGap()
/* 689 */           .addGroup(pnlInformacoesBancariasLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 690 */             .addGroup(pnlInformacoesBancariasLayout.createSequentialGroup()
/* 691 */               .addGroup(pnlInformacoesBancariasLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 692 */                 .addGroup(pnlInformacoesBancariasLayout.createSequentialGroup()
/* 693 */                   .addComponent(this.lblBanco, -1, -1, 32767)
/* 694 */                   .addGap(80, 80, 80))
/* 695 */                 .addGroup(pnlInformacoesBancariasLayout.createSequentialGroup()
/* 696 */                   .addGroup(pnlInformacoesBancariasLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 697 */                     .addComponent(this.lblAgencia, -2, 123, -2)
/* 698 */                     .addComponent(this.lblConta, -2, 139, -2))
/* 699 */                   .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767)))
/* 700 */               .addGroup(pnlInformacoesBancariasLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 701 */                 .addGroup(pnlInformacoesBancariasLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
/* 702 */                   .addGroup(pnlInformacoesBancariasLayout.createSequentialGroup()
/* 703 */                     .addComponent((Component)this.edtAgencia, -2, 104, -2)
/* 704 */                     .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767)
/* 705 */                     .addComponent(this.lblOperacao)
/* 706 */                     .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 707 */                     .addComponent((Component)this.edtOperacao, -2, 65, -2))
/* 708 */                   .addGroup(GroupLayout.Alignment.LEADING, pnlInformacoesBancariasLayout.createSequentialGroup()
/* 709 */                     .addComponent((Component)this.edtConta, -2, 180, -2)
/* 710 */                     .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 711 */                     .addComponent(this.lblDV)
/* 712 */                     .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 713 */                     .addComponent((Component)this.edtDV, -2, 65, -2)))
/* 714 */                 .addComponent((Component)this.cmbBanco, -2, 300, -2)))
/* 715 */             .addComponent(this.lblTipoConta, -1, -1, 32767)
/* 716 */             .addComponent(this.lblInfoPix, -1, -1, 32767)
/* 717 */             .addGroup(pnlInformacoesBancariasLayout.createSequentialGroup()
/* 718 */               .addGroup(pnlInformacoesBancariasLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 719 */                 .addComponent(this.chkPix)
/* 720 */                 .addComponent((Component)this.cmbContas, -2, 479, -2)
/* 721 */                 .addGroup(pnlInformacoesBancariasLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 722 */                   .addGroup(GroupLayout.Alignment.LEADING, pnlInformacoesBancariasLayout.createSequentialGroup()
/* 723 */                     .addComponent(this.lblInfoContas)
/* 724 */                     .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 725 */                     .addComponent(this.lblContasBens1, -2, 162, -2)
/* 726 */                     .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 727 */                     .addComponent(this.lblContasBens, -2, -1, -2))
/* 728 */                   .addComponent(this.jsSeparador, -2, 467, -2)))
/* 729 */               .addGap(25, 25, 25)))));
/*     */     
/* 731 */     pnlInformacoesBancariasLayout.setVerticalGroup(pnlInformacoesBancariasLayout
/* 732 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 733 */         .addGroup(pnlInformacoesBancariasLayout.createSequentialGroup()
/* 734 */           .addContainerGap()
/* 735 */           .addComponent(this.chkPix)
/* 736 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 737 */           .addGroup(pnlInformacoesBancariasLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 738 */             .addGroup(pnlInformacoesBancariasLayout.createSequentialGroup()
/* 739 */               .addComponent(this.jsSeparador, -2, 6, -2)
/* 740 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 741 */               .addGroup(pnlInformacoesBancariasLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 742 */                 .addComponent(this.lblContasBens, -2, -1, -2)
/* 743 */                 .addComponent(this.lblContasBens1)))
/* 744 */             .addComponent(this.lblInfoContas))
/* 745 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767)
/* 746 */           .addComponent((Component)this.cmbContas, -2, -1, -2)
/* 747 */           .addGap(12, 12, 12)
/* 748 */           .addComponent(this.lblTipoConta, -2, 32, -2)
/* 749 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 750 */           .addGroup(pnlInformacoesBancariasLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 751 */             .addComponent((Component)this.cmbBanco, -2, -1, -2)
/* 752 */             .addComponent(this.lblBanco, -2, 32, -2))
/* 753 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 754 */           .addGroup(pnlInformacoesBancariasLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 755 */             .addGroup(pnlInformacoesBancariasLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 756 */               .addComponent((Component)this.edtAgencia, -2, -1, -2)
/* 757 */               .addComponent(this.lblOperacao)
/* 758 */               .addComponent((Component)this.edtOperacao, -2, -1, -2))
/* 759 */             .addComponent(this.lblAgencia, -2, 28, -2))
/* 760 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 761 */           .addGroup(pnlInformacoesBancariasLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 762 */             .addGroup(pnlInformacoesBancariasLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 763 */               .addComponent((Component)this.edtConta, -1, -1, 32767)
/* 764 */               .addComponent(this.lblDV)
/* 765 */               .addComponent((Component)this.edtDV, -2, -1, -2))
/* 766 */             .addComponent(this.lblConta, -2, 32, -2))
/* 767 */           .addGap(24, 24, 24)
/* 768 */           .addComponent(this.lblInfoPix)
/* 769 */           .addContainerGap()));
/*     */ 
/*     */     
/* 772 */     pnlInformacoesBancariasLayout.linkSize(1, new Component[] { (Component)this.edtConta, (Component)this.edtDV, this.lblDV });
/*     */     
/* 774 */     this.cmbContas.getAccessibleContext().setAccessibleName("Contas pré-cadastradas");
/* 775 */     this.cmbContas.getAccessibleContext().setAccessibleDescription("");
/* 776 */     this.cmbBanco.getAccessibleContext().setAccessibleName("Banco");
/* 777 */     this.edtAgencia.getAccessibleContext().setAccessibleName("Agência (sem dígito verificador)");
/* 778 */     this.edtConta.getAccessibleContext().setAccessibleName("Conta para crédito");
/* 779 */     this.edtDV.getAccessibleContext().setAccessibleName("Dígito Verificador");
/* 780 */     this.edtOperacao.getAccessibleContext().setAccessibleName("Operação");
/* 781 */     this.edtOperacao.getAccessibleContext().setAccessibleDescription("");
/*     */     
/* 783 */     GroupLayout layout = new GroupLayout((Container)this);
/* 784 */     setLayout(layout);
/* 785 */     layout.setHorizontalGroup(layout
/* 786 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 787 */         .addGroup(layout.createSequentialGroup()
/* 788 */           .addContainerGap()
/* 789 */           .addComponent(this.pnlInformacoesBancarias, -1, -1, 32767)
/* 790 */           .addContainerGap()));
/*     */     
/* 792 */     layout.setVerticalGroup(layout
/* 793 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 794 */         .addGroup(layout.createSequentialGroup()
/* 795 */           .addContainerGap()
/* 796 */           .addComponent(this.pnlInformacoesBancarias, -1, -1, 32767)
/* 797 */           .addContainerGap()));
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
/*     */   public JAutoCompleteEditCodigo getCmbBanco() {
/* 824 */     return this.cmbBanco;
/*     */   }
/*     */   
/*     */   public JAutoCompleteEditCPF getCmbContas() {
/* 828 */     return this.cmbContas;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JEditMascara getEdtAgencia() {
/* 836 */     return this.edtAgencia;
/*     */   }
/*     */   
/*     */   public JEditMascara getEdtConta() {
/* 840 */     return this.edtConta;
/*     */   }
/*     */   
/*     */   public JEditAlfa getEdtDV() {
/* 844 */     return this.edtDV;
/*     */   }
/*     */   
/*     */   public JEditAlfa getEdtOperacao() {
/* 848 */     return this.edtOperacao;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\resumo\SubPainelDadosBancarios.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */