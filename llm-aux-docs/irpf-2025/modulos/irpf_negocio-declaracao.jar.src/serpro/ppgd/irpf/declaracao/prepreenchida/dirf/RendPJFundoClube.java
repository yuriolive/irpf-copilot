/*    */ package serpro.ppgd.irpf.declaracao.prepreenchida.dirf;
/*    */ 
/*    */ import serpro.ppgd.negocio.Observador;
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RendPJFundoClube
/*    */   extends RendPJ
/*    */ {
/* 17 */   private Valor valorRendimento3223 = new Valor(this, "Valor do Rendimento 3223");
/* 18 */   private Valor valorRendimento3540 = new Valor(this, "Valor do Rendimento 3540");
/* 19 */   private Valor valorRendimento3556 = new Valor(this, "Valor do Rendimento 3556");
/* 20 */   private Valor valorIRF3223 = new Valor(this, "Valor do IRF 3223");
/* 21 */   private Valor valorIRF3540 = new Valor(this, "Valor do IRF 3540");
/* 22 */   private Valor valorIRF3556 = new Valor(this, "Valor do IRF 3556");
/*    */   
/*    */   public RendPJFundoClube() {
/* 25 */     Observador obsRendimento = new Observador()
/*    */       {
/*    */         public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/* 28 */           Valor rendimento = new Valor();
/* 29 */           rendimento.append('+', RendPJFundoClube.this.getValorRendimento3223());
/* 30 */           rendimento.append('+', RendPJFundoClube.this.getValorRendimento3540());
/* 31 */           rendimento.append('+', RendPJFundoClube.this.getValorRendimento3556());
/* 32 */           RendPJFundoClube.this.getValorRendimento().setConteudo(rendimento);
/*    */         }
/*    */       };
/* 35 */     this.valorRendimento3223.addObservador(obsRendimento);
/* 36 */     this.valorRendimento3540.addObservador(obsRendimento);
/* 37 */     this.valorRendimento3556.addObservador(obsRendimento);
/* 38 */     Observador obsIRPF = new Observador()
/*    */       {
/*    */         public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/* 41 */           Valor irf = new Valor();
/* 42 */           irf.append('+', RendPJFundoClube.this.getValorIRF3223());
/* 43 */           irf.append('+', RendPJFundoClube.this.getValorIRF3540());
/* 44 */           irf.append('+', RendPJFundoClube.this.getValorIRF3556());
/* 45 */           RendPJFundoClube.this.getImpostoRetidoFonte().setConteudo(irf);
/*    */         }
/*    */       };
/* 48 */     this.valorIRF3223.addObservador(obsIRPF);
/* 49 */     this.valorIRF3540.addObservador(obsIRPF);
/* 50 */     this.valorIRF3556.addObservador(obsIRPF);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Valor getValorRendimento3223() {
/* 57 */     return this.valorRendimento3223;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Valor getValorRendimento3540() {
/* 64 */     return this.valorRendimento3540;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Valor getValorRendimento3556() {
/* 71 */     return this.valorRendimento3556;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Valor getValorIRF3223() {
/* 78 */     return this.valorIRF3223;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Valor getValorIRF3540() {
/* 85 */     return this.valorIRF3540;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Valor getValorIRF3556() {
/* 92 */     return this.valorIRF3556;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\declaracao\prepreenchida\dirf\RendPJFundoClube.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */