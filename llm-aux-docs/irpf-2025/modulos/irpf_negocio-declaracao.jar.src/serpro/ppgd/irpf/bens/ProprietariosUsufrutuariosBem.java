/*    */ package serpro.ppgd.irpf.bens;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import serpro.ppgd.irpf.gui.atividaderural.PainelDadosImovel;
/*    */ import serpro.ppgd.negocio.Colecao;
/*    */ import serpro.ppgd.negocio.Informacao;
/*    */ import serpro.ppgd.negocio.ObjetoFicha;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
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
/*    */ public class ProprietariosUsufrutuariosBem
/*    */   extends Colecao<ProprietarioUsufrutuarioBem>
/*    */   implements ObjetoFicha
/*    */ {
/*    */   public ProprietarioUsufrutuarioBem instanciaNovoObjeto() {
/* 25 */     ProprietarioUsufrutuarioBem proprietarioUsufrutuarioBem = new ProprietarioUsufrutuarioBem();
/* 26 */     return proprietarioUsufrutuarioBem;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isVazio() {
/* 31 */     Iterator<ProprietarioUsufrutuarioBem> iterator = itens().iterator();
/*    */ 
/*    */     
/* 34 */     while (iterator.hasNext()) {
/* 35 */       ProprietarioUsufrutuarioBem proprietarioUsufrutuarioBem = iterator.next();
/* 36 */       if (!proprietarioUsufrutuarioBem.isVazio()) {
/* 37 */         return false;
/*    */       }
/*    */     } 
/* 40 */     return true;
/*    */   }
/*    */   
/*    */   public ProprietariosUsufrutuariosBem obterCopia() {
/* 44 */     ProprietariosUsufrutuariosBem copia = new ProprietariosUsufrutuariosBem();
/* 45 */     Iterator<ProprietarioUsufrutuarioBem> iterator = itens().iterator();
/*    */     
/* 47 */     while (iterator.hasNext()) {
/* 48 */       ProprietarioUsufrutuarioBem proprietarioUsufrutuarioBem = iterator.next();
/* 49 */       if (proprietarioUsufrutuarioBem != null && !proprietarioUsufrutuarioBem.isVazio()) {
/* 50 */         copia.add(proprietarioUsufrutuarioBem.obterCopia());
/*    */       }
/*    */     } 
/* 53 */     return copia;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getClasseFicha() {
/* 58 */     return PainelDadosImovel.class.getName();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getTituloFichaDashboard() {
/* 63 */     return "Bens e Direitos - Proprietários ou Usufrutuários";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getNomeAba() {
/* 68 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 73 */     List<Informacao> retorno = recuperarCamposInformacao();
/* 74 */     Iterator<ProprietarioUsufrutuarioBem> iterator = itens().iterator();
/*    */     
/* 76 */     while (iterator.hasNext()) {
/* 77 */       ProprietarioUsufrutuarioBem proprietarioUsufrutuarioBem = iterator.next();
/* 78 */       retorno.addAll(proprietarioUsufrutuarioBem.recuperarListaCamposPendencia());
/*    */     } 
/* 80 */     return retorno;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\bens\ProprietariosUsufrutuariosBem.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */