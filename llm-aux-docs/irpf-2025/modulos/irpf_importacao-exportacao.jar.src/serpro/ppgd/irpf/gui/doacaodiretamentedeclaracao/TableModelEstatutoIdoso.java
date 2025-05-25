/*    */ package serpro.ppgd.irpf.gui.doacaodiretamentedeclaracao;
/*    */ 
/*    */ import serpro.ppgd.irpf.doacaodeclaracao.ColecaoEstatutoIdoso;
/*    */ import serpro.ppgd.irpf.doacaodeclaracao.EstatutoIdoso;
/*    */ import serpro.ppgd.irpf.gui.TableListaModel;
/*    */ import serpro.ppgd.negocio.Alfa;
/*    */ import serpro.ppgd.negocio.Colecao;
/*    */ import serpro.ppgd.negocio.Informacao;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ 
/*    */ 
/*    */ public class TableModelEstatutoIdoso
/*    */   extends TableListaModel
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public TableModelEstatutoIdoso(ColecaoEstatutoIdoso pObj) {
/* 18 */     super((ObjetoNegocio)pObj);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getColumnCount() {
/* 23 */     return 5;
/*    */   }
/*    */   
/*    */   public Informacao getInformacaoAt(int row, int col) {
/*    */     Alfa a, b;
/* 28 */     Colecao colecaoEstatutoIdoso = (Colecao)getObjetoNegocio();
/* 29 */     EstatutoIdoso item = colecaoEstatutoIdoso.itens().get(row);
/* 30 */     String tipoFundo = item.getTipoFundo().naoFormatado();
/* 31 */     switch (col) {
/*    */       case 0:
/* 33 */         return super.getInformacaoAt(row, col);
/*    */       case 1:
/* 35 */         a = new Alfa("Tipo Fundo");
/* 36 */         a.setConteudo(tipoFundo.equals("N") ? "Nacional" : (
/* 37 */             tipoFundo.equals("E") ? "Estadual" : (
/* 38 */             tipoFundo.equals("M") ? "Municipal" : "")));
/* 39 */         return (Informacao)a;
/*    */       case 2:
/* 41 */         b = new Alfa("Fundo");
/* 42 */         if (tipoFundo.equals("N")) {
/* 43 */           b.setConteudo("Nacional");
/* 44 */         } else if (tipoFundo.equals("E")) {
/* 45 */           b.setConteudo(item.getUf().getDescricaoDefault());
/* 46 */         } else if (tipoFundo.equals("M")) {
/* 47 */           b.setConteudo(item.getUf().getDescricaoDefault() + " - " + item.getUf().getDescricaoDefault());
/*    */         } 
/* 49 */         return (Informacao)b;
/*    */       case 3:
/* 51 */         return (Informacao)item.getCnpjFundo();
/*    */       case 4:
/* 53 */         return (Informacao)item.getValor();
/*    */     } 
/* 55 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getColumnName(int column) {
/* 60 */     switch (column) {
/*    */       case 0:
/* 62 */         return super.getColumnName(0);
/*    */       case 1:
/* 64 */         return "<html><center>Tipo de Fundo</center></html>";
/*    */       case 2:
/* 66 */         return "<html><center>Fundo</center></html>";
/*    */       case 3:
/* 68 */         return "<html><center>CNPJ</center></html>";
/*    */       case 4:
/* 70 */         return "<html><center>Valor</center></html>";
/*    */     } 
/* 72 */     return "";
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\doacaodiretamentedeclaracao\TableModelEstatutoIdoso.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */