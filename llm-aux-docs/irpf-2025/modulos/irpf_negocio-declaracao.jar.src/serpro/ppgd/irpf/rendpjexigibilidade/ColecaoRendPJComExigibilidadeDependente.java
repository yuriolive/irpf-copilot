/*    */ package serpro.ppgd.irpf.rendpjexigibilidade;
/*    */ 
/*    */ import java.lang.ref.WeakReference;
/*    */ import java.util.Iterator;
/*    */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*    */ import serpro.ppgd.negocio.CPF;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ 
/*    */ public class ColecaoRendPJComExigibilidadeDependente
/*    */   extends ColecaoRendPJComExigibilidadeTitular
/*    */ {
/* 14 */   private WeakReference<DeclaracaoIRPF> declaracaoRef = null;
/*    */   
/*    */   public ColecaoRendPJComExigibilidadeDependente(DeclaracaoIRPF dec) {
/* 17 */     super(dec.getIdentificadorDeclaracao());
/* 18 */     this.declaracaoRef = new WeakReference<>(dec);
/* 19 */     setFicha("Rendimentos Tributáveis Recebidos de PJ com Exigibilidade Suspensa pelos Dependentes");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RendPJComExigibilidadeDependente instanciaNovoObjeto() {
/* 27 */     return new RendPJComExigibilidadeDependente(this.declaracaoRef.get());
/*    */   }
/*    */ 
/*    */   
/*    */   public RendPJComExigibilidadeDependente obterRendimentoPorChave(String niFontePagadora, String cpfBeneficiario) {
/* 32 */     for (RendPJComExigibilidadeTitular rend : itens()) {
/* 33 */       RendPJComExigibilidadeDependente rendDep = (RendPJComExigibilidadeDependente)rend;
/* 34 */       if (rendDep.getNIFontePagadora().naoFormatado().equals(niFontePagadora) && rendDep
/* 35 */         .getCpfDependente().naoFormatado().equals(cpfBeneficiario)) {
/* 36 */         return rendDep;
/*    */       }
/*    */     } 
/* 39 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean possuiDependenteComCPF(String cpf) {
/* 44 */     if ("".equals(cpf.trim())) {
/* 45 */       return false;
/*    */     }
/*    */     
/* 48 */     Iterator<RendPJComExigibilidadeTitular> it = itens().iterator();
/* 49 */     while (it.hasNext()) {
/* 50 */       RendPJComExigibilidadeDependente rend = (RendPJComExigibilidadeDependente)it.next();
/* 51 */       if (rend.getCpfDependente().naoFormatado().equals(cpf)) {
/* 52 */         return true;
/*    */       }
/*    */     } 
/*    */     
/* 56 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void excluirDependentesComCPF(String cpf) {
/* 61 */     Iterator<RendPJComExigibilidadeTitular> it = itens().iterator();
/* 62 */     while (it.hasNext()) {
/* 63 */       RendPJComExigibilidadeDependente rend = (RendPJComExigibilidadeDependente)it.next();
/* 64 */       if (rend.getCpfDependente().naoFormatado().equals(cpf)) {
/* 65 */         it.remove();
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   public Valor obterRendimentosRecebidosPorDependente(CPF cpf) {
/* 71 */     Valor retorno = new Valor();
/*    */     
/* 73 */     Iterator<RendPJComExigibilidadeTitular> it = itens().iterator();
/* 74 */     while (it.hasNext()) {
/* 75 */       RendPJComExigibilidadeDependente rend = (RendPJComExigibilidadeDependente)it.next();
/* 76 */       if (rend.getCpfDependente().naoFormatado().equals(cpf.naoFormatado())) {
/* 77 */         retorno.append('+', (Valor)rend.getRendExigSuspensa());
/*    */       }
/*    */     } 
/* 80 */     return retorno;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendpjexigibilidade\ColecaoRendPJComExigibilidadeDependente.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */