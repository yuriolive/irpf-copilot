/*    */ package serpro.ppgd.irpf.declaracao.assistida.informerendimentos;
/*    */ 
/*    */ import java.util.List;
/*    */ import serpro.ppgd.irpf.ValorPositivo;
/*    */ import serpro.ppgd.negocio.Alfa;
/*    */ import serpro.ppgd.negocio.CPF;
/*    */ import serpro.ppgd.negocio.Informacao;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
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
/*    */ public class ItemPensaoAlimenticiaRendAcm
/*    */   extends ObjetoNegocio
/*    */ {
/* 22 */   private CPF cpfAlimentando = new CPF(this, "CPF Alimentando");
/* 23 */   private Alfa alimentando = new Alfa(this, "Alimentando", 60);
/* 24 */   private ValorPositivo valor = new ValorPositivo(this, "Valor");
/* 25 */   private Alfa nomeAlimentandoSelecionado = new Alfa(this, "Nome do Alimentando selecionado", 60);
/* 26 */   private CPF cpfAlimentandoSelecionado = new CPF(this, "CPF do Alimentando selecionado");
/*    */ 
/*    */   
/*    */   private boolean importar = true;
/*    */ 
/*    */   
/*    */   public CPF getCpfAlimentando() {
/* 33 */     return this.cpfAlimentando;
/*    */   }
/*    */   
/*    */   public Alfa getAlimentando() {
/* 37 */     return this.alimentando;
/*    */   }
/*    */   
/*    */   public Valor getValor() {
/* 41 */     return (Valor)this.valor;
/*    */   }
/*    */   
/*    */   public Alfa getNomeAlimentandoSelecionado() {
/* 45 */     return this.nomeAlimentandoSelecionado;
/*    */   }
/*    */   
/*    */   public CPF getCpfAlimentandoSelecionado() {
/* 49 */     return this.cpfAlimentandoSelecionado;
/*    */   }
/*    */   
/*    */   public boolean isImportar() {
/* 53 */     return this.importar;
/*    */   }
/*    */   
/*    */   public void setImportar(boolean importar) {
/* 57 */     this.importar = importar;
/*    */   }
/*    */ 
/*    */   
/*    */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 62 */     List<Informacao> campos = super.recuperarListaCamposPendencia();
/* 63 */     campos.add(getNomeAlimentandoSelecionado());
/*    */     
/* 65 */     return super.recuperarListaCamposPendencia();
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\declaracao\assistida\informerendimentos\ItemPensaoAlimenticiaRendAcm.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */