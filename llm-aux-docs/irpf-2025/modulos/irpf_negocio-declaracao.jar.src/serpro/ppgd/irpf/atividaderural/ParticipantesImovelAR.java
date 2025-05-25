/*    */ package serpro.ppgd.irpf.atividaderural;
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
/*    */ public class ParticipantesImovelAR
/*    */   extends Colecao<ParticipanteImovelAR>
/*    */   implements ObjetoFicha
/*    */ {
/*    */   public ParticipanteImovelAR instanciaNovoObjeto() {
/* 24 */     ParticipanteImovelAR participanteImovelAR = new ParticipanteImovelAR();
/* 25 */     return participanteImovelAR;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isVazio() {
/* 30 */     Iterator<ParticipanteImovelAR> iterator = itens().iterator();
/*    */ 
/*    */     
/* 33 */     while (iterator.hasNext()) {
/* 34 */       ParticipanteImovelAR participanteImovelAR = iterator.next();
/* 35 */       if (!participanteImovelAR.isVazio()) {
/* 36 */         return false;
/*    */       }
/*    */     } 
/* 39 */     return true;
/*    */   }
/*    */   
/*    */   public ParticipantesImovelAR obterCopia() {
/* 43 */     ParticipantesImovelAR copia = new ParticipantesImovelAR();
/* 44 */     Iterator<ParticipanteImovelAR> iterator = itens().iterator();
/*    */     
/* 46 */     while (iterator.hasNext()) {
/* 47 */       ParticipanteImovelAR participanteImovelAR = iterator.next();
/* 48 */       if (participanteImovelAR != null && !participanteImovelAR.isVazio()) {
/* 49 */         copia.add(participanteImovelAR.obterCopia());
/*    */       }
/*    */     } 
/* 52 */     return copia;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getClasseFicha() {
/* 57 */     return PainelDadosImovel.class.getName();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getTituloFichaDashboard() {
/* 62 */     return "Atividade Rural - Dados do Imóvel Explorado";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getNomeAba() {
/* 67 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 72 */     List<Informacao> retorno = recuperarCamposInformacao();
/* 73 */     Iterator<ParticipanteImovelAR> iterator = itens().iterator();
/*    */     
/* 75 */     while (iterator.hasNext()) {
/* 76 */       ParticipanteImovelAR participanteImovelAR = iterator.next();
/* 77 */       retorno.addAll(participanteImovelAR.recuperarListaCamposPendencia());
/*    */     } 
/* 79 */     return retorno;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\atividaderural\ParticipantesImovelAR.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */