/*    */ package serpro.ppgd.irpf.doacoes;
/*    */ 
/*    */ import java.lang.ref.WeakReference;
/*    */ import java.util.Iterator;
/*    */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*    */ import serpro.ppgd.negocio.Alfa;
/*    */ import serpro.ppgd.negocio.Colecao;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ public class Doacoes
/*    */   extends Colecao<Doacao>
/*    */ {
/* 14 */   private Valor totalDeducaoIncentivo = new Valor((ObjetoNegocio)this, "");
/*    */   
/* 16 */   private Alfa ultimoIndiceGerado = new Alfa();
/*    */   
/*    */   private static long geradorIndices;
/* 19 */   private WeakReference<DeclaracaoIRPF> declaracaoRef = null;
/*    */   
/*    */   public Doacoes(DeclaracaoIRPF dec) {
/* 22 */     this.totalDeducaoIncentivo.setCasasDecimais(4);
/* 23 */     this.declaracaoRef = new WeakReference<>(dec);
/* 24 */     setFicha("Doações Efetuadas");
/* 25 */     inicializaGeradorIndices();
/*    */   }
/*    */   
/*    */   private void inicializaGeradorIndices() {
/* 29 */     if (this.ultimoIndiceGerado.naoFormatado().trim().equals("")) {
/* 30 */       geradorIndices = 0L;
/*    */     } else {
/* 32 */       geradorIndices = Long.parseLong(this.ultimoIndiceGerado.naoFormatado());
/*    */     } 
/*    */   }
/*    */   
/*    */   private String proximoIndice() {
/* 37 */     this.ultimoIndiceGerado.setConteudo("" + geradorIndices++);
/* 38 */     return this.ultimoIndiceGerado.naoFormatado();
/*    */   }
/*    */ 
/*    */   
/*    */   public void objetoInserido(Doacao doacao) {
/* 43 */     doacao.setFicha(getFicha());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Doacao instanciaNovoObjeto() {
/* 51 */     Doacao item = new Doacao(this.declaracaoRef.get());
/* 52 */     item.getIndice().setConteudo(proximoIndice());
/* 53 */     return item;
/*    */   }
/*    */   
/*    */   public Valor getTotalDeducaoIncentivo() {
/* 57 */     return this.totalDeducaoIncentivo;
/*    */   }
/*    */   
/*    */   public boolean existeValorDoacaoAlto() {
/* 61 */     Iterator<Doacao> it = itens().iterator();
/*    */     
/* 63 */     while (it.hasNext()) {
/* 64 */       Doacao doacao = it.next();
/* 65 */       if (doacao.getValorPago().comparacao(">", "50.000,00")) {
/* 66 */         return true;
/*    */       }
/*    */     } 
/*    */     
/* 70 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\doacoes\Doacoes.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */