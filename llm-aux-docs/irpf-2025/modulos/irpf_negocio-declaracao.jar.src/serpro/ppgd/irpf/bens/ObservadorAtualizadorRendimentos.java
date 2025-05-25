/*    */ package serpro.ppgd.irpf.bens;
/*    */ 
/*    */ import java.lang.ref.WeakReference;
/*    */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*    */ import serpro.ppgd.negocio.Observador;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ObservadorAtualizadorRendimentos
/*    */   extends Observador
/*    */ {
/* 18 */   private Bem bem = null;
/* 19 */   private WeakReference<DeclaracaoIRPF> weakDec = null;
/*    */   
/*    */   public ObservadorAtualizadorRendimentos(Bem bem, DeclaracaoIRPF dec) {
/* 22 */     this.bem = bem;
/* 23 */     this.weakDec = new WeakReference<>(dec);
/*    */   }
/*    */ 
/*    */   
/*    */   public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/* 28 */     if ("CNPJ do Fundo".equals(nomePropriedade) || "CPF/CNPJ da fonte pagadora"
/* 29 */       .equals(nomePropriedade) || "CNPJ".equals(nomePropriedade) || "Titular ou dependente"
/* 30 */       .equals(nomePropriedade) || "CPF do dependente".equals(nomePropriedade)) {
/* 31 */       String codigoRendimentoIsento = this.bem.buscarTipoRendimentoIsento(this.bem.getGrupo().naoFormatado(), this.bem.getCodigo().naoFormatado());
/* 32 */       if (codigoRendimentoIsento != null) {
/* 33 */         ((DeclaracaoIRPF)this.weakDec.get()).getRendIsentos().atualizarRendIsento(this.bem, codigoRendimentoIsento);
/*    */       }
/* 35 */       String codigoRendimentoExclusivo = this.bem.buscarTipoRendimentoExclusivo(this.bem.getGrupo().naoFormatado(), this.bem.getCodigo().naoFormatado());
/* 36 */       if (codigoRendimentoExclusivo != null)
/* 37 */         ((DeclaracaoIRPF)this.weakDec.get()).getRendTributacaoExclusiva().atualizarRendExclusivo(this.bem, codigoRendimentoExclusivo); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\bens\ObservadorAtualizadorRendimentos.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */