/*    */ package serpro.ppgd.irpf.pagamentos;
/*    */ 
/*    */ import serpro.ppgd.irpf.gui.TableModelImportacao;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TableModelImportacaoPagamentos
/*    */   extends TableModelImportacao
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public TableModelImportacaoPagamentos(Pagamentos colecao) {
/* 13 */     super(colecao);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getColumnCount() {
/* 18 */     return 3;
/*    */   }
/*    */   
/*    */   public Object getValueAt(int row, int col) {
/*    */     int tipoPagamento;
/* 23 */     Pagamento pagamento = this.colecao.itens().get(row);
/* 24 */     switch (col) {
/*    */       case 0:
/* 26 */         return super.getValueAt(row, col);
/*    */       case 1:
/* 28 */         tipoPagamento = 0;
/*    */         try {
/* 30 */           tipoPagamento = Integer.parseInt(pagamento.getCodigo().getConteudoAtual(0));
/* 31 */         } catch (NumberFormatException numberFormatException) {}
/*    */         
/* 33 */         if (tipoPagamento == 30 || tipoPagamento == 31 || tipoPagamento == 33 || tipoPagamento == 34) {
/* 34 */           return pagamento.getDependenteOuAlimentando().naoFormatado();
/*    */         }
/* 36 */         return pagamento.getNomeBeneficiario().naoFormatado();
/*    */       
/*    */       case 2:
/* 39 */         return pagamento.getNiBeneficiario().formatado();
/*    */     } 
/* 41 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getColumnName(int column) {
/* 47 */     switch (column) {
/*    */       case 0:
/* 49 */         return "";
/*    */       case 1:
/* 51 */         return "Nome do Beneficiário";
/*    */       case 2:
/* 53 */         return "CPF/CNPJ do Beneficiário";
/*    */     } 
/* 55 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\pagamentos\TableModelImportacaoPagamentos.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */