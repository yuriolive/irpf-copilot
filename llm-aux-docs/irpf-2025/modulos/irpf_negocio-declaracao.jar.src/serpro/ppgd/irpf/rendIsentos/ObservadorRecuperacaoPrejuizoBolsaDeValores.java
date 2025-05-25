/*    */ package serpro.ppgd.irpf.rendIsentos;
/*    */ 
/*    */ import java.lang.ref.WeakReference;
/*    */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*    */ import serpro.ppgd.irpf.rendavariavel.ColecaoFundosInvestimentosDependente;
/*    */ import serpro.ppgd.irpf.rendavariavel.ColecaoRendaVariavelDependente;
/*    */ import serpro.ppgd.irpf.rendavariavel.FundosInvestimentos;
/*    */ import serpro.ppgd.irpf.rendavariavel.ItemFundosInvestimentosDependente;
/*    */ import serpro.ppgd.irpf.rendavariavel.ItemRendaVariavelDependente;
/*    */ import serpro.ppgd.irpf.rendavariavel.RendaVariavel;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ import serpro.ppgd.negocio.Observador;
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ public class ObservadorRecuperacaoPrejuizoBolsaDeValores
/*    */   extends Observador
/*    */ {
/* 18 */   private WeakReference<DeclaracaoIRPF> weakDec = null;
/*    */   
/*    */   public ObservadorRecuperacaoPrejuizoBolsaDeValores(DeclaracaoIRPF dec) {
/* 21 */     this.weakDec = new WeakReference<>(dec);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/* 27 */     ((DeclaracaoIRPF)this.weakDec.get()).getRendIsentos().getRecuperacaoPrejuizosBolsaValores().clear();
/*    */     
/* 29 */     Valor totalResultadoNegativoJaneiro = new Valor();
/* 30 */     Valor totalPrejuizoCompensarDezembro = new Valor();
/* 31 */     Valor recuperacaoPrejuizo = new Valor();
/*    */     
/* 33 */     RendaVariavel rendaVariavelTitular = ((DeclaracaoIRPF)this.weakDec.get()).getRendaVariavel();
/* 34 */     ColecaoRendaVariavelDependente colecaoRendaVariavelDependente = ((DeclaracaoIRPF)this.weakDec.get()).getRendaVariavelDependente();
/* 35 */     FundosInvestimentos fundosInvestimentosTitular = ((DeclaracaoIRPF)this.weakDec.get()).getFundosInvestimentos();
/* 36 */     ColecaoFundosInvestimentosDependente colecaoFundosInvestimentosDependente = ((DeclaracaoIRPF)this.weakDec.get()).getFundosInvestimentosDependente();
/*    */     
/* 38 */     totalResultadoNegativoJaneiro.append('+', (Valor)rendaVariavelTitular.getJaneiro().getOperacoesComuns().getResultadoNegativoMesAnterior());
/* 39 */     totalResultadoNegativoJaneiro.append('+', (Valor)rendaVariavelTitular.getJaneiro().getOperacoesDayTrade().getResultadoNegativoMesAnterior());
/* 40 */     totalResultadoNegativoJaneiro.append('+', fundosInvestimentosTitular.getJan().getResultNegativoAnterior());
/*    */     
/* 42 */     totalPrejuizoCompensarDezembro.append('+', rendaVariavelTitular.getDezembro().getOperacoesComuns().getPrejuizoCompensar());
/* 43 */     totalPrejuizoCompensarDezembro.append('+', rendaVariavelTitular.getDezembro().getOperacoesDayTrade().getPrejuizoCompensar());
/* 44 */     totalPrejuizoCompensarDezembro.append('+', fundosInvestimentosTitular.getDez().getPrejuizoCompensar());
/*    */     
/* 46 */     for (ObjetoNegocio rendaVariavelDependente : colecaoRendaVariavelDependente.itens()) {
/* 47 */       totalResultadoNegativoJaneiro.append('+', (Valor)((ItemRendaVariavelDependente)rendaVariavelDependente).getRendaVariavel().getJaneiro()
/* 48 */           .getOperacoesComuns().getResultadoNegativoMesAnterior());
/* 49 */       totalResultadoNegativoJaneiro.append('+', (Valor)((ItemRendaVariavelDependente)rendaVariavelDependente).getRendaVariavel().getJaneiro()
/* 50 */           .getOperacoesDayTrade().getResultadoNegativoMesAnterior());
/*    */       
/* 52 */       totalPrejuizoCompensarDezembro.append('+', ((ItemRendaVariavelDependente)rendaVariavelDependente).getRendaVariavel().getDezembro()
/* 53 */           .getOperacoesComuns().getPrejuizoCompensar());
/* 54 */       totalPrejuizoCompensarDezembro.append('+', ((ItemRendaVariavelDependente)rendaVariavelDependente).getRendaVariavel().getDezembro()
/* 55 */           .getOperacoesDayTrade().getPrejuizoCompensar());
/*    */     } 
/*    */     
/* 58 */     for (ObjetoNegocio fundosInvestimentosDependente : colecaoFundosInvestimentosDependente.itens()) {
/* 59 */       totalResultadoNegativoJaneiro.append('+', ((ItemFundosInvestimentosDependente)fundosInvestimentosDependente).getFundosInvestimentos().getJan()
/* 60 */           .getResultNegativoAnterior());
/* 61 */       totalPrejuizoCompensarDezembro.append('+', ((ItemFundosInvestimentosDependente)fundosInvestimentosDependente).getFundosInvestimentos().getDez()
/* 62 */           .getPrejuizoCompensar());
/*    */     } 
/*    */     
/* 65 */     if (totalResultadoNegativoJaneiro.comparacao(">", totalPrejuizoCompensarDezembro)) {
/*    */       
/* 67 */       recuperacaoPrejuizo.append('+', totalResultadoNegativoJaneiro);
/* 68 */       recuperacaoPrejuizo.append('-', totalPrejuizoCompensarDezembro);
/*    */       
/* 70 */       ((DeclaracaoIRPF)this.weakDec.get()).getRendIsentos().getRecuperacaoPrejuizosBolsaValores().setConteudo(recuperacaoPrejuizo);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendIsentos\ObservadorRecuperacaoPrejuizoBolsaDeValores.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */