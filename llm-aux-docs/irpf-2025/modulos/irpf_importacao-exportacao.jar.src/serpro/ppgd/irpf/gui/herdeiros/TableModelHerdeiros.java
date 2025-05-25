/*    */ package serpro.ppgd.irpf.gui.herdeiros;
/*    */ 
/*    */ import serpro.ppgd.irpf.gui.TableListaModel;
/*    */ import serpro.ppgd.irpf.herdeiros.Herdeiro;
/*    */ import serpro.ppgd.irpf.herdeiros.Herdeiros;
/*    */ import serpro.ppgd.negocio.Colecao;
/*    */ import serpro.ppgd.negocio.Informacao;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ 
/*    */ 
/*    */ public class TableModelHerdeiros
/*    */   extends TableListaModel
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public TableModelHerdeiros(Herdeiros pObj) {
/* 17 */     super((ObjetoNegocio)pObj);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getColumnCount() {
/* 22 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public Informacao getInformacaoAt(int row, int col) {
/* 27 */     Colecao herdeiros = (Colecao)getObjetoNegocio();
/* 28 */     Herdeiro item = herdeiros.itens().get(row);
/* 29 */     switch (col) {
/*    */       case 0:
/* 31 */         return (Informacao)item.getNiHerdeiro();
/*    */       case 1:
/* 33 */         return (Informacao)item.getNome();
/*    */     } 
/* 35 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getColumnName(int column) {
/* 40 */     switch (column) {
/*    */       case 0:
/* 42 */         return "<html><center>CPF/CNPJ</center></html>";
/*    */       case 1:
/* 44 */         return "<html><center>Nome</center></html>";
/*    */     } 
/* 46 */     return "";
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\herdeiros\TableModelHerdeiros.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */