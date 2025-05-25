/*    */ package serpro.ppgd.irpf.declaracao.assistida.informeplanosaude;
/*    */ 
/*    */ import java.util.List;
/*    */ import serpro.ppgd.irpf.ValorPositivo;
/*    */ import serpro.ppgd.negocio.Alfa;
/*    */ import serpro.ppgd.negocio.CPF;
/*    */ import serpro.ppgd.negocio.Informacao;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemPagamentoPlanoSaude
/*    */   extends ObjetoNegocio
/*    */ {
/* 24 */   private Alfa codigo = new Alfa(this, "Código do Pagamento");
/* 25 */   private CPF cpfBeneficiario = new CPF(this, "CPF do beneficiário");
/* 26 */   private Alfa nomeBeneficiario = new Alfa(this, "Nome do beneficiário", 60);
/* 27 */   private ValorPositivo valorPago = new ValorPositivo(this, "Valor Pago");
/* 28 */   private Alfa tipoBeneficiario = new Alfa(this, "Tipo de beneficiario", 1);
/* 29 */   private CPF cpfBeneficiarioSelecionado = new CPF(this, "CPF do beneficiario selecionado");
/* 30 */   private Alfa nomeBeneficiarioSelecionado = new Alfa(this, "Nome do beneficiario selecionado", 1);
/*    */   
/*    */   private boolean importar = true;
/*    */   
/*    */   public static final String TIPO_BENEFICIARIO_TITULAR = "1";
/*    */   
/*    */   public static final String TIPO_BENEFICIARIO_DEPENDENTE = "2";
/*    */   
/*    */   public static final String TIPO_BENEFICIARIO_ALIMENTANDO = "3";
/*    */ 
/*    */   
/*    */   public CPF getCpfBeneficiario() {
/* 42 */     return this.cpfBeneficiario;
/*    */   }
/*    */   
/*    */   public Alfa getNomeBeneficiario() {
/* 46 */     return this.nomeBeneficiario;
/*    */   }
/*    */   
/*    */   public ValorPositivo getValorPago() {
/* 50 */     return this.valorPago;
/*    */   }
/*    */   
/*    */   public Alfa getTipoBeneficiario() {
/* 54 */     return this.tipoBeneficiario;
/*    */   }
/*    */   
/*    */   public CPF getCpfBeneficiarioSelecionado() {
/* 58 */     return this.cpfBeneficiarioSelecionado;
/*    */   }
/*    */   
/*    */   public Alfa getNomeBeneficiarioSelecionado() {
/* 62 */     return this.nomeBeneficiarioSelecionado;
/*    */   }
/*    */   
/*    */   public boolean isImportar() {
/* 66 */     return this.importar;
/*    */   }
/*    */   
/*    */   public void setImportar(boolean importar) {
/* 70 */     this.importar = importar;
/*    */   }
/*    */   
/*    */   public Alfa getCodigo() {
/* 74 */     return this.codigo;
/*    */   }
/*    */ 
/*    */   
/*    */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 79 */     List<Informacao> campos = super.recuperarListaCamposPendencia();
/* 80 */     campos.add(this.codigo);
/* 81 */     campos.add(this.tipoBeneficiario);
/* 82 */     campos.add(this.nomeBeneficiarioSelecionado);
/*    */     
/* 84 */     return campos;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\declaracao\assistida\informeplanosaude\ItemPagamentoPlanoSaude.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */