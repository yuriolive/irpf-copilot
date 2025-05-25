/*    */ package serpro.ppgd.irpf.bens;
/*    */ 
/*    */ import java.util.List;
/*    */ import serpro.ppgd.irpf.gui.atividaderural.PainelDadosImovel;
/*    */ import serpro.ppgd.negocio.Alfa;
/*    */ import serpro.ppgd.negocio.Informacao;
/*    */ import serpro.ppgd.negocio.NI;
/*    */ import serpro.ppgd.negocio.ObjetoFicha;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ import serpro.ppgd.negocio.ValidadorIf;
/*    */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorNI;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ProprietarioUsufrutuarioBem
/*    */   extends ObjetoNegocio
/*    */   implements ObjetoFicha
/*    */ {
/* 32 */   private NI ni = new NI(this, "CPF/CNPJ");
/* 33 */   private Alfa indice = new Alfa(this, "Indice");
/*    */ 
/*    */   
/*    */   public ProprietarioUsufrutuarioBem() {
/* 37 */     getNi().addValidador((ValidadorIf)new ValidadorNI((byte)3));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public NI getNi() {
/* 45 */     return this.ni;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setNi(NI ni) {
/* 52 */     this.ni = ni;
/*    */   }
/*    */   
/*    */   public Alfa getIndice() {
/* 56 */     return this.indice;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isVazio() {
/* 61 */     return this.ni.isVazio();
/*    */   }
/*    */   
/*    */   public ProprietarioUsufrutuarioBem obterCopia() {
/* 65 */     ProprietarioUsufrutuarioBem copia = new ProprietarioUsufrutuarioBem();
/* 66 */     copia.getNi().setConteudo(getNi());
/* 67 */     copia.getIndice().setConteudo(getIndice());
/* 68 */     return copia;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getClasseFicha() {
/* 73 */     return PainelDadosImovel.class.getName();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getTituloFichaDashboard() {
/* 78 */     return "Bens e Direitos - Proprietários ou Usufrutuários";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getNomeAba() {
/* 83 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 88 */     List<Informacao> retorno = super.recuperarListaCamposPendencia();
/* 89 */     retorno.add(getNi());
/* 90 */     return retorno;
/*    */   }
/*    */   
/*    */   public ProprietarioUsufrutuarioBem getCopia() {
/* 94 */     ProprietarioUsufrutuarioBem copia = new ProprietarioUsufrutuarioBem();
/* 95 */     copia.getNi().setConteudo(this.ni);
/* 96 */     return copia;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\bens\ProprietarioUsufrutuarioBem.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */