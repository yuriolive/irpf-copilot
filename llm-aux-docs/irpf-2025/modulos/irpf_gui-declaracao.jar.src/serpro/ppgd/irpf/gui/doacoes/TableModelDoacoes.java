/*    */ package serpro.ppgd.irpf.gui.doacoes;
/*    */ 
/*    */ import serpro.ppgd.irpf.doacoes.Doacao;
/*    */ import serpro.ppgd.irpf.doacoes.Doacoes;
/*    */ import serpro.ppgd.irpf.gui.TableListaModel;
/*    */ import serpro.ppgd.negocio.Informacao;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ 
/*    */ 
/*    */ public class TableModelDoacoes
/*    */   extends TableListaModel
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public TableModelDoacoes(Doacoes pObj) {
/* 16 */     super((ObjetoNegocio)pObj);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getColumnCount() {
/* 21 */     return 6;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Informacao getInformacaoAt(int row, int col) {
/* 27 */     Doacoes doacoes = (Doacoes)getObjetoNegocio();
/* 28 */     Doacao item = doacoes.itens().get(row);
/*    */     
/* 30 */     switch (col) {
/*    */       case 0:
/* 32 */         return super.getInformacaoAt(row, col);
/*    */       case 1:
/* 34 */         return (Informacao)item.getCodigo();
/*    */       case 2:
/* 36 */         return (Informacao)item.getNomeBeneficiario();
/*    */       case 3:
/* 38 */         return (Informacao)item.getNiBeneficiario();
/*    */       case 4:
/* 40 */         return (Informacao)item.getValorPago();
/*    */       case 5:
/* 42 */         return (Informacao)item.getParcelaNaoDedutivel();
/*    */     } 
/* 44 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getColumnName(int column) {
/* 49 */     switch (column) {
/*    */       case 0:
/* 51 */         return super.getColumnName(column);
/*    */       case 1:
/* 53 */         return "<html><center>Cód.</center></html>";
/*    */       case 2:
/* 55 */         return "<html><center>Nome do<br>Beneficiário</center></html>";
/*    */       case 3:
/* 57 */         return "<html><center>CPF/CNPJ do<br>Beneficiário</center></html>";
/*    */       case 4:
/* 59 */         return "<html><center>Valor Pago</center></html>";
/*    */       case 5:
/* 61 */         return "<html><center>Parc. Não<br>Dedutível</center></html>";
/*    */     } 
/* 63 */     return "";
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\doacoes\TableModelDoacoes.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */