/*    */ package serpro.ppgd.irpf.gui.bens;
/*    */ 
/*    */ import serpro.ppgd.irpf.bens.ProprietarioUsufrutuarioBem;
/*    */ import serpro.ppgd.irpf.bens.ProprietariosUsufrutuariosBem;
/*    */ import serpro.ppgd.irpf.gui.TableListaModel;
/*    */ import serpro.ppgd.negocio.Informacao;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ 
/*    */ 
/*    */ public class TableModelProprietariosUsufrutuarios
/*    */   extends TableListaModel
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public TableModelProprietariosUsufrutuarios(ProprietariosUsufrutuariosBem pColecao) {
/* 16 */     super((ObjetoNegocio)pColecao);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getColumnCount() {
/* 23 */     return 1;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getColumnName(int column) {
/* 29 */     switch (column) {
/*    */       case 0:
/* 31 */         return "CPF/CNPJ";
/*    */     } 
/*    */     
/* 34 */     return "";
/*    */   }
/*    */   
/*    */   public ProprietariosUsufrutuariosBem getProprietariosUsufrutuariosBem() {
/* 38 */     return (ProprietariosUsufrutuariosBem)getObjetoNegocio();
/*    */   }
/*    */ 
/*    */   
/*    */   public Informacao getInformacaoAt(int row, int col) {
/* 43 */     ProprietarioUsufrutuarioBem proprietarioUsufrutuario = getProprietariosUsufrutuariosBem().itens().get(row);
/* 44 */     switch (col) {
/*    */       case 0:
/* 46 */         return (Informacao)proprietarioUsufrutuario.getNi();
/*    */     } 
/*    */     
/* 49 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\bens\TableModelProprietariosUsufrutuarios.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */