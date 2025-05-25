/*    */ package serpro.ppgd.irpf.alimentandos;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*    */ import serpro.ppgd.irpf.gui.ControladorGui;
/*    */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*    */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroPensaoAlimenticia;
/*    */ import serpro.ppgd.irpf.rendacm.RendAcmDependente;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ import serpro.ppgd.negocio.Observador;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ObservadorRemoverPensaoRRA
/*    */   extends Observador
/*    */ {
/*    */   private Alimentando alimentando;
/*    */   
/*    */   public ObservadorRemoverPensaoRRA(Alimentando alimentando) {
/* 27 */     this.alimentando = alimentando;
/*    */   }
/*    */ 
/*    */   
/*    */   public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/* 32 */     String antigo = (String)valorAntigo;
/* 33 */     if (!antigo.isEmpty()) {
/* 34 */       boolean remover = false;
/* 35 */       boolean sair = false;
/* 36 */       DeclaracaoIRPF dec = ControladorGui.getDemonstrativoAberto();
/* 37 */       if (dec != null && !dec.getIdentificadorDeclaracao().getCpf().naoFormatado().equals(antigo)) {
/*    */         
/* 39 */         List<RendAcmDependente> rendimentosacm = dec.getColecaoRendAcmDependente().obterRRAsPorCPF(antigo);
/*    */         
/* 41 */         for (RendAcmDependente rendacm : rendimentosacm) {
/*    */           
/* 43 */           ArrayList<ItemQuadroPensaoAlimenticia> pensoes = new ArrayList<>();
/*    */           
/* 45 */           for (ItemQuadroPensaoAlimenticia pensao : rendacm.getPensaoAlimenticiaQuadroAuxiliar().itens()) {
/*    */             
/* 47 */             if (pensao.getAlimentando().naoFormatado().equals(this.alimentando.getNome().naoFormatado())) {
/*    */               
/* 49 */               if (!remover && !sair)
/*    */               {
/* 51 */                 if (GuiUtil.mostrarConfirma("alimentando_confirma_remover_pensoes_rra", new String[] { this.alimentando.getNome().naoFormatado() })) {
/* 52 */                   remover = true;
/*    */                 } else {
/* 54 */                   sair = true;
/* 55 */                   this.alimentando.getCpfResponsavel().setObservadoresAtivos(false);
/* 56 */                   this.alimentando.getCpfResponsavel().setConteudo(antigo);
/* 57 */                   this.alimentando.getCpfResponsavel().setObservadoresAtivos(true);
/*    */                 } 
/*    */               }
/*    */ 
/*    */               
/* 62 */               pensoes.add(pensao);
/*    */             } 
/*    */ 
/*    */             
/* 66 */             if (sair) {
/*    */               break;
/*    */             }
/*    */           } 
/*    */ 
/*    */           
/* 72 */           if (remover) {
/*    */             
/* 74 */             Iterator<ItemQuadroPensaoAlimenticia> itPensoes = pensoes.iterator();
/*    */             
/* 76 */             while (itPensoes.hasNext()) {
/* 77 */               rendacm.getPensaoAlimenticiaQuadroAuxiliar().remove((ObjetoNegocio)itPensoes.next());
/*    */             }
/*    */           } 
/*    */ 
/*    */           
/* 82 */           if (sair)
/*    */             break; 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\alimentandos\ObservadorRemoverPensaoRRA.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */