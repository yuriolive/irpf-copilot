/*    */ package serpro.ppgd.irpf.gui.alimentandos;
/*    */ 
/*    */ import serpro.ppgd.irpf.alimentandos.Alimentando;
/*    */ import serpro.ppgd.irpf.alimentandos.Alimentandos;
/*    */ import serpro.ppgd.irpf.gui.TableListaModel;
/*    */ import serpro.ppgd.negocio.Alfa;
/*    */ import serpro.ppgd.negocio.Colecao;
/*    */ import serpro.ppgd.negocio.Informacao;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ 
/*    */ 
/*    */ public class TableModelAlimentandos
/*    */   extends TableListaModel
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 16 */   private Alfa residenteBrasil = new Alfa("No Brasil");
/* 17 */   private Alfa residenteExterior = new Alfa("No Exterior");
/*    */   
/*    */   public TableModelAlimentandos(Alimentandos pObj) {
/* 20 */     super((ObjetoNegocio)pObj);
/* 21 */     this.residenteBrasil.setConteudo("No Brasil");
/* 22 */     this.residenteExterior.setConteudo("No Exterior");
/*    */   }
/*    */ 
/*    */   
/*    */   public int getColumnCount() {
/* 27 */     return 5;
/*    */   }
/*    */ 
/*    */   
/*    */   public Informacao getInformacaoAt(int row, int col) {
/* 32 */     Colecao alimentandos = (Colecao)getObjetoNegocio();
/* 33 */     Alimentando item = alimentandos.itens().get(row);
/* 34 */     switch (col) {
/*    */       case 0:
/* 36 */         return super.getInformacaoAt(row, col);
/*    */       case 1:
/* 38 */         return (Informacao)item.getNome();
/*    */       case 2:
/* 40 */         return item.getResidente().formatado().equals("0") ? (Informacao)this.residenteBrasil : (
/* 41 */           item.getResidente().formatado().equals("1") ? (Informacao)this.residenteExterior : (Informacao)new Alfa());
/*    */       case 3:
/* 43 */         return (Informacao)item.getDtNascimento();
/*    */       case 4:
/* 45 */         return (Informacao)item.getCpf();
/*    */     } 
/* 47 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getColumnName(int column) {
/* 52 */     switch (column) {
/*    */       case 0:
/* 54 */         return super.getColumnName(0);
/*    */       case 1:
/* 56 */         return "<html><center>Nome</center></html>";
/*    */       case 2:
/* 58 */         return "<html><center>Residente</center></html>";
/*    */       case 3:
/* 60 */         return "<html><center>Data de<br>Nascimento</center></html>";
/*    */       case 4:
/* 62 */         return "<html><center>CPF</center></html>";
/*    */     } 
/* 64 */     return "";
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\alimentandos\TableModelAlimentandos.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */