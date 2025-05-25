/*    */ package serpro.ppgd.irpf.atividaderural;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import serpro.ppgd.irpf.ValorPositivo;
/*    */ import serpro.ppgd.irpf.calculos.CalculosMovimentacaoRebanho;
/*    */ import serpro.ppgd.negocio.Informacao;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ import serpro.ppgd.negocio.Observador;
/*    */ import serpro.ppgd.negocio.ValidadorIf;
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ public class ItemMovimentacaoRebanho extends ObjetoNegocio {
/* 14 */   protected ValorPositivo estoqueInicial = new ValorPositivo(this, "Estoque Inicial");
/* 15 */   protected ValorPositivo aquisicoesAno = new ValorPositivo(this, "Aquisições no Ano");
/* 16 */   protected ValorPositivo nascidosAno = new ValorPositivo(this, "Nascidos no Ano");
/* 17 */   protected ValorPositivo consumo = new ValorPositivo(this, "Consumo e Perdas");
/* 18 */   protected ValorPositivo vendas = new ValorPositivo(this, "Vendas no Ano");
/* 19 */   protected ValorPositivo estoqueFinal = new ValorPositivo(this, "Estoque Final");
/*    */ 
/*    */   
/*    */   public ItemMovimentacaoRebanho() {
/* 23 */     getEstoqueInicial().setMaximoDigitosParteInteira(8);
/* 24 */     getEstoqueInicial().addValidador((ValidadorIf)new ValidadorMovimentacaoRebanho(this));
/* 25 */     getAquisicoesAno().addValidador((ValidadorIf)new ValidadorMovimentacaoRebanho(this));
/* 26 */     getNascidosAno().addValidador((ValidadorIf)new ValidadorMovimentacaoRebanho(this));
/* 27 */     getConsumo().addValidador((ValidadorIf)new ValidadorMovimentacaoRebanho(this));
/* 28 */     getVendas().addValidador((ValidadorIf)new ValidadorMovimentacaoRebanho(this));
/*    */     
/* 30 */     getEstoqueInicial().addObservador((Observador)new CalculosMovimentacaoRebanho(this));
/* 31 */     getAquisicoesAno().addObservador((Observador)new CalculosMovimentacaoRebanho(this));
/* 32 */     getNascidosAno().addObservador((Observador)new CalculosMovimentacaoRebanho(this));
/* 33 */     getConsumo().addObservador((Observador)new CalculosMovimentacaoRebanho(this));
/* 34 */     getVendas().addObservador((Observador)new CalculosMovimentacaoRebanho(this));
/*    */   }
/*    */   
/*    */   public Valor getAquisicoesAno() {
/* 38 */     return (Valor)this.aquisicoesAno;
/*    */   }
/*    */   
/*    */   public Valor getConsumo() {
/* 42 */     return (Valor)this.consumo;
/*    */   }
/*    */   
/*    */   public Valor getEstoqueFinal() {
/* 46 */     return (Valor)this.estoqueFinal;
/*    */   }
/*    */   
/*    */   public Valor getEstoqueInicial() {
/* 50 */     return (Valor)this.estoqueInicial;
/*    */   }
/*    */   
/*    */   public Valor getNascidosAno() {
/* 54 */     return (Valor)this.nascidosAno;
/*    */   }
/*    */   
/*    */   public Valor getVendas() {
/* 58 */     return (Valor)this.vendas;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isVazio() {
/* 63 */     Iterator<Informacao> iterator = recuperarListaCamposPendencia().iterator();
/*    */     
/* 65 */     while (iterator.hasNext()) {
/* 66 */       Informacao informacao = iterator.next();
/* 67 */       if (!informacao.isVazio()) {
/* 68 */         return false;
/*    */       }
/*    */     } 
/* 71 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 76 */     List<Informacao> retorno = recuperarCamposInformacao();
/* 77 */     return retorno;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\atividaderural\ItemMovimentacaoRebanho.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */