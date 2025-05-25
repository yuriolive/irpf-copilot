/*    */ package serpro.ppgd.irpf.gcap.aquisicao;
/*    */ 
/*    */ import serpro.ppgd.irpf.ValorPositivo;
/*    */ import serpro.ppgd.negocio.Alfa;
/*    */ import serpro.ppgd.negocio.Logico;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AquisicaoBemImovel
/*    */   extends Aquisicao
/*    */ {
/* 15 */   private ValorPositivo custoAquisicaoTorna = new ValorPositivo(this, "Custo de aquisição da torna (R$)");
/*    */   
/* 17 */   private ValorPositivo custoAquisicaoTornaOrigemMNDolar = new ValorPositivo(this, "Custo de aquisição da torna - Origem em Moeda Nacional (US$)");
/*    */   
/* 19 */   private ValorPositivo custoAquisicaoTornaOrigemMNReal = new ValorPositivo(this, "Custo de aquisição da torna - Origem em Moeda Nacional (R$)");
/*    */   
/* 21 */   private ValorPositivo custoAquisicaoTornaOrigemMEDolar = new ValorPositivo(this, "Custo de aquisição da torna - Origem em Moeda Estrangeira (US$)");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 28 */   private Logico houveReforma = new Logico(this, "Houve reforma ou ampliação?");
/*    */   
/* 30 */   private ColecaoParcelaAquisicao parcelasAquisicao = new ColecaoParcelaAquisicao();
/*    */   
/* 32 */   private transient Alfa houveReformaAux = new Alfa(this, "Houve reforma ou ampliação?", 1);
/*    */ 
/*    */   
/*    */   public AquisicaoBemImovel() {
/* 36 */     getHouveReforma().addOpcao(Logico.SIM, Logico.LABEL_SIM);
/* 37 */     getHouveReforma().addOpcao(Logico.NAO, Logico.LABEL_NAO);
/*    */     
/* 39 */     getCustoAquisicaoTorna().setReadOnly(true);
/* 40 */     getCustoAquisicaoTornaOrigemMEDolar().setReadOnly(true);
/* 41 */     getCustoAquisicaoTornaOrigemMNDolar().setReadOnly(true);
/* 42 */     getCustoAquisicaoTornaOrigemMNReal().setReadOnly(true);
/* 43 */     setReadOnlyAquisicaoBemImovel();
/*    */   }
/*    */   
/*    */   public void setReadOnlyAquisicaoBemImovel() {
/* 47 */     this.custoAquisicaoTorna.setReadOnly(true);
/* 48 */     this.custoAquisicaoTornaOrigemMNDolar.setReadOnly(true);
/* 49 */     this.custoAquisicaoTornaOrigemMNReal.setReadOnly(true);
/* 50 */     this.custoAquisicaoTornaOrigemMEDolar.setReadOnly(true);
/* 51 */     this.houveReforma.setReadOnly(true);
/*    */   }
/*    */   
/*    */   public Logico getHouveReforma() {
/* 55 */     return this.houveReforma;
/*    */   }
/*    */   
/*    */   public ColecaoParcelaAquisicao getParcelasAquisicao() {
/* 59 */     return this.parcelasAquisicao;
/*    */   }
/*    */   
/*    */   public Alfa getHouveReformaAux() {
/* 63 */     return this.houveReformaAux;
/*    */   }
/*    */   
/*    */   public boolean houveReforma() {
/* 67 */     return Logico.SIM.equals(getHouveReforma().naoFormatado());
/*    */   }
/*    */   
/*    */   public boolean naoHouveReforma() {
/* 71 */     return Logico.NAO.equals(getHouveReforma().naoFormatado());
/*    */   }
/*    */   
/*    */   public ValorPositivo getCustoAquisicaoTorna() {
/* 75 */     return this.custoAquisicaoTorna;
/*    */   }
/*    */   
/*    */   public ValorPositivo getCustoAquisicaoTornaOrigemMNDolar() {
/* 79 */     return this.custoAquisicaoTornaOrigemMNDolar;
/*    */   }
/*    */   
/*    */   public ValorPositivo getCustoAquisicaoTornaOrigemMNReal() {
/* 83 */     return this.custoAquisicaoTornaOrigemMNReal;
/*    */   }
/*    */   
/*    */   public ValorPositivo getCustoAquisicaoTornaOrigemMEDolar() {
/* 87 */     return this.custoAquisicaoTornaOrigemMEDolar;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\aquisicao\AquisicaoBemImovel.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */