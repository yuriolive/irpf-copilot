/*    */ package serpro.ppgd.irpf.rendIsentos;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import serpro.ppgd.irpf.ValorPositivo;
/*    */ import serpro.ppgd.negocio.Alfa;
/*    */ import serpro.ppgd.negocio.CPF;
/*    */ import serpro.ppgd.negocio.Codigo;
/*    */ import serpro.ppgd.negocio.Informacao;
/*    */ import serpro.ppgd.negocio.NI;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ public abstract class ItemQuadroAuxiliarAb extends ObjetoNegocio {
/*    */   public static final String TIPO_DEPENDENTE = "Dependente";
/*    */   public static final String TIPO_TITULAR = "Titular";
/*    */   protected static final String LABEL_TIPO_BENEFICIARIO = "Beneficiário";
/*    */   protected static final String LABEL_CPF_BENEFICIARIO = "Beneficiário";
/*    */   protected static final String LABEL_VALOR = "Valor";
/*    */   public static final String NOME_VALOR_13SALARIO = "13º Salário";
/*    */   public static final String CODIGO_BEM = "Código do Bem";
/*    */   public static final String CHAVE_BEM_VAZIO = "00000";
/* 23 */   protected Codigo tipoBeneficiario = new Codigo(this, "Beneficiário", new ArrayList());
/* 24 */   protected CPF cpfBeneficiario = new CPF(this, "Beneficiário");
/* 25 */   protected ValorPositivo valor = new ValorPositivo(this, "Valor");
/* 26 */   protected ValorPositivo valor13Salario = new ValorPositivo(this, "13º Salário");
/* 27 */   protected Alfa codBem = new Alfa(this, "Código do Bem");
/*    */   
/* 29 */   protected List<Informacao> camposPendencia = recuperarListaCamposPendencia();
/*    */   
/*    */   public Valor getValor() {
/* 32 */     return (Valor)this.valor;
/*    */   }
/*    */   
/*    */   public ValorPositivo getValor13Salario() {
/* 36 */     return this.valor13Salario;
/*    */   }
/*    */   
/*    */   public Codigo getTipoBeneficiario() {
/* 40 */     return this.tipoBeneficiario;
/*    */   }
/*    */   
/*    */   public CPF getCpfBeneficiario() {
/* 44 */     return this.cpfBeneficiario;
/*    */   }
/*    */   
/*    */   public NI getNIFontePagadora() {
/* 48 */     return null;
/*    */   }
/*    */   
/*    */   public Alfa getNomeFontePagadora() {
/* 52 */     return null;
/*    */   }
/*    */   
/*    */   public Valor getValorRendimento() {
/* 56 */     return getValor();
/*    */   }
/*    */   
/*    */   public Alfa getCodBem() {
/* 60 */     return this.codBem;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void adicionaCamposParaPendencia() {
/* 69 */     List<Informacao> lista = this.camposPendencia;
/* 70 */     lista.add(this.tipoBeneficiario);
/* 71 */     lista.add(this.cpfBeneficiario);
/* 72 */     lista.add(this.valor);
/* 73 */     lista.add(this.valor13Salario);
/*    */   }
/*    */   
/*    */   public void removeCamposParaPendencia() {
/* 77 */     this.camposPendencia.clear();
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendIsentos\ItemQuadroAuxiliarAb.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */