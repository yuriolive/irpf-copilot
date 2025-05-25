/*     */ package serpro.ppgd.irpf.declaracao.assistida.informerendimentos;
/*     */ 
/*     */ import java.util.List;
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.CPF;
/*     */ import serpro.ppgd.negocio.Codigo;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.NI;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
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
/*     */ public class ItemPagamento
/*     */   extends ObjetoNegocio
/*     */ {
/*  25 */   private Alfa codigo = new Alfa(this, "Código do Pagamento");
/*  26 */   private CPF cpfBeneficiario = new CPF(this, "CPF do beneficiário");
/*  27 */   private Alfa nomeBeneficiario = new Alfa(this, "Nome do beneficiário", 60);
/*  28 */   private NI niPrestadorServico = new NI(this, "CPF ou CNPJ do prestador do serviço");
/*  29 */   private Alfa nomePrestadorServico = new Alfa(this, "Nome do prestador do serviço", 60);
/*  30 */   private ValorPositivo valorPago = new ValorPositivo(this, "Valor Pago");
/*  31 */   private ValorPositivo valorReembolsado = new ValorPositivo(this, "Valor reembolsado");
/*  32 */   private ValorPositivo valorContribuicaoPatrocinador = new ValorPositivo(this, "Valor Pago");
/*  33 */   private ValorPositivo valorDecimoTerceiro = new ValorPositivo(this, "Valor Décimo Terceiro");
/*  34 */   private Alfa tipoResidencia = new Alfa(this, "Tipo de Residência (Brasil ou Exterior)", 1);
/*  35 */   private Alfa tipoPensaoAlimenticia = new Alfa(this, "Tipo de Pensão Alimentícia", 1);
/*  36 */   private Alfa tipoBeneficiario = new Alfa(this, "Tipo de beneficiario", 1);
/*  37 */   private CPF cpfBeneficiarioSelecionado = new CPF(this, "CPF do beneficiario selecionado");
/*  38 */   private Alfa nomeBeneficiarioSelecionado = new Alfa(this, "Nome do beneficiario selecionado", 1);
/*     */   
/*     */   private boolean importar = true;
/*     */   
/*     */   public static final String TIPO_PENSAO_ALIMENTICIA_JUDICIAL = "1";
/*     */   
/*     */   public static final String TIPO_PENSAO_ALIMENTICIA_SEPARACAO_DIVORCIO = "2";
/*     */   
/*     */   public static final String TIPO_BENEFICIARIO_TITULAR = "1";
/*     */   
/*     */   public static final String TIPO_BENEFICIARIO_DEPENDENTE = "2";
/*     */   
/*     */   public static final String TIPO_BENEFICIARIO_ALIMENTANDO = "3";
/*     */   
/*     */   public static final int PAGAMENTO_PREVIPRIVADA = 1;
/*     */   public static final int PAGAMENTO_FAPI = 2;
/*     */   public static final int PAGAMENTO_FUNPRESP = 3;
/*     */   
/*     */   public Alfa getCodigo() {
/*  57 */     return this.codigo;
/*     */   }
/*     */   
/*     */   public CPF getCpfBeneficiario() {
/*  61 */     return this.cpfBeneficiario;
/*     */   }
/*     */   
/*     */   public Alfa getNomeBeneficiario() {
/*  65 */     return this.nomeBeneficiario;
/*     */   }
/*     */   
/*     */   public NI getNiPrestadorServico() {
/*  69 */     return this.niPrestadorServico;
/*     */   }
/*     */   
/*     */   public Alfa getNomePrestadorServico() {
/*  73 */     return this.nomePrestadorServico;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorPago() {
/*  77 */     return this.valorPago;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorReembolsado() {
/*  81 */     return this.valorReembolsado;
/*     */   }
/*     */   
/*     */   public Alfa getTipoResidencia() {
/*  85 */     return this.tipoResidencia;
/*     */   }
/*     */   
/*     */   public Alfa getTipoPensaoAlimenticia() {
/*  89 */     return this.tipoPensaoAlimenticia;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorContribuicaoPatrocinador() {
/*  93 */     return this.valorContribuicaoPatrocinador;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorDecimoTerceiro() {
/*  97 */     return this.valorDecimoTerceiro;
/*     */   }
/*     */   
/*     */   public String obterCodigoMaisDescricaoPagamento(String cod) {
/* 101 */     Codigo codigo = new Codigo(this, "Código", CadastroTabelasIRPF.recuperarTipoPagamentos());
/* 102 */     codigo.setConteudo(cod);
/* 103 */     return codigo.getConteudoAtual(1);
/*     */   }
/*     */   
/*     */   public String obterCodigoMaisDescricaoPagamentoPrevidencia() {
/* 107 */     return obterCodigoMaisDescricaoPagamento(obterCodigoPagamentoPrevidencia());
/*     */   }
/*     */   
/*     */   public String obterCodigoPagamentoPrevidencia() {
/* 111 */     int codigo = -1;
/*     */     try {
/* 113 */       codigo = Integer.parseInt(getCodigo().naoFormatado());
/* 114 */     } catch (NumberFormatException numberFormatException) {}
/* 115 */     switch (codigo) {
/*     */       case 1:
/* 117 */         return "36";
/*     */       case 3:
/* 119 */         return "37";
/*     */       case 2:
/* 121 */         return "36";
/*     */     } 
/* 123 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isImportar() {
/* 128 */     return this.importar;
/*     */   }
/*     */   
/*     */   public void setImportar(boolean importar) {
/* 132 */     this.importar = importar;
/*     */   }
/*     */   
/*     */   public Alfa getTipoBeneficiario() {
/* 136 */     return this.tipoBeneficiario;
/*     */   }
/*     */   
/*     */   public CPF getCpfBeneficiarioSelecionado() {
/* 140 */     return this.cpfBeneficiarioSelecionado;
/*     */   }
/*     */   
/*     */   public Alfa getNomeBeneficiarioSelecionado() {
/* 144 */     return this.nomeBeneficiarioSelecionado;
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 149 */     List<Informacao> campos = super.recuperarListaCamposPendencia();
/* 150 */     campos.add(this.codigo);
/* 151 */     campos.add(this.tipoBeneficiario);
/* 152 */     campos.add(this.nomeBeneficiarioSelecionado);
/*     */     
/* 154 */     return campos;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\declaracao\assistida\informerendimentos\ItemPagamento.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */