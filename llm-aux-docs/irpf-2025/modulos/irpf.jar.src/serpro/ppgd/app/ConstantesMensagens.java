/*    */ package serpro.ppgd.app;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
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
/*    */ public class ConstantesMensagens
/*    */ {
/* 17 */   private static Map<String, String> mensagens = null;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public enum ConstantesEnum
/*    */   {
/* 24 */     MSG_ATUALIZAR_ERRO_VERIFICACAO,
/* 25 */     MSG_TAREFA_RETORNOU_ERRO;
/*    */     
/*    */     public String getChave() {
/* 28 */       return ConstantesMensagens.getMensagens().get(name());
/*    */     }
/*    */   }
/*    */   
/*    */   public static Map<String, String> getMensagens() {
/* 33 */     if (mensagens == null) {
/* 34 */       carregarMensagens();
/*    */     }
/* 36 */     return mensagens;
/*    */   }
/*    */   
/*    */   private static void carregarMensagens() {
/* 40 */     mensagens = new HashMap<>();
/* 41 */     mensagens.put(ConstantesEnum.MSG_ATUALIZAR_ERRO_VERIFICACAO.name(), "atualizar.erro.verificacao");
/* 42 */     mensagens.put(ConstantesEnum.MSG_TAREFA_RETORNOU_ERRO.name(), "msg_tarefa_retornou_erro");
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\irpf.jar!\serpro\ppgd\app\ConstantesMensagens.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */