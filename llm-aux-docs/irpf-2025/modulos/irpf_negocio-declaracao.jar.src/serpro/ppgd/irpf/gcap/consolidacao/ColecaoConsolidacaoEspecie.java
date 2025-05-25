/*    */ package serpro.ppgd.irpf.gcap.consolidacao;
/*    */ 
/*    */ import serpro.ppgd.irpf.ValorPositivo;
/*    */ import serpro.ppgd.irpf.gcap.GCAP;
/*    */ import serpro.ppgd.irpf.gcap.especie.ColecaoMoedaAlienada;
/*    */ import serpro.ppgd.irpf.gcap.especie.MoedasAlienadasMensal;
/*    */ import serpro.ppgd.irpf.gui.ControladorGui;
/*    */ import serpro.ppgd.negocio.Colecao;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ColecaoConsolidacaoEspecie
/*    */   extends Colecao<ConsolidacaoEspecie>
/*    */ {
/* 17 */   private ValorPositivo ganhoCapitalTotal = new ValorPositivo((ObjetoNegocio)this, "Ganho de capital total (R$)", 11, 2);
/* 18 */   private ValorPositivo impostoDevido = new ValorPositivo((ObjetoNegocio)this, "Imposto devido (R$)", 11, 2);
/*    */   
/*    */   public ColecaoConsolidacaoEspecie() {
/* 21 */     super(ConsolidacaoEspecie.class.getName());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ValorPositivo getGanhoCapitalTotal() {
/* 28 */     return this.ganhoCapitalTotal;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ValorPositivo getImpostoDevido() {
/* 35 */     return this.impostoDevido;
/*    */   }
/*    */   
/*    */   public void consolidar() {
/* 39 */     if (ControladorGui.getDemonstrativoAberto() != null) {
/* 40 */       ValorPositivo lGanhoCapitalTotal = new ValorPositivo();
/* 41 */       ValorPositivo lImpostoDevido = new ValorPositivo();
/*    */       
/* 43 */       for (ConsolidacaoEspecie consolidacao : itens()) {
/* 44 */         lGanhoCapitalTotal.append('+', (Valor)consolidacao.getGanhoCapitalTotal());
/*    */         
/* 46 */         GCAP gcap = ControladorGui.getDemonstrativoAberto().getGCAP();
/* 47 */         lImpostoDevido.append('+', (Valor)gcap.getColecaoTotalizacaoMoedasAlienadas().obterTotalizacaoPorCPF(consolidacao
/* 48 */               .getCpf().naoFormatado()).obterValorAnual(MoedasAlienadasMensal.CONST_CAMPO_IMPOSTO_DEVIDO));
/*    */       } 
/*    */ 
/*    */       
/* 52 */       getGanhoCapitalTotal().setConteudo((Valor)lGanhoCapitalTotal);
/* 53 */       getImpostoDevido().setConteudo((Valor)lImpostoDevido);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void adicionarConsolidacaoEspecia(ColecaoMoedaAlienada colecaoMoeda) {
/* 58 */     GCAP gcap = ControladorGui.getDemonstrativoAberto().getGCAP();
/* 59 */     ConsolidacaoEspecie consolidacaoEspecie = new ConsolidacaoEspecie();
/* 60 */     consolidacaoEspecie.getCpf().setConteudo(colecaoMoeda.getCpf());
/* 61 */     consolidacaoEspecie.getDataInicioPermanencia().setConteudo(colecaoMoeda.getDataInicioPermanencia());
/* 62 */     consolidacaoEspecie.getDataFimPermanencia().setConteudo(colecaoMoeda.getDataFimPermanencia());
/* 63 */     consolidacaoEspecie.getAliquotaMedia().setConteudo((Valor)colecaoMoeda.getAliquotaMedia());
/* 64 */     consolidacaoEspecie.getGanhoCapitalTotal().setConteudo((Valor)colecaoMoeda.getGanhoCapitalTotal());
/*    */     
/* 66 */     consolidacaoEspecie.getImpostoDevido().setConteudo((Valor)gcap
/* 67 */         .getColecaoTotalizacaoMoedasAlienadas().obterTotalizacaoPorCPF(colecaoMoeda
/* 68 */           .getCpf().naoFormatado()).obterValorAnual(MoedasAlienadasMensal.CONST_CAMPO_IMPOSTO_DEVIDO));
/*    */ 
/*    */ 
/*    */     
/* 72 */     gcap.removerObjetoGCAP(consolidacaoEspecie, this);
/* 73 */     add(consolidacaoEspecie);
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\consolidacao\ColecaoConsolidacaoEspecie.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */