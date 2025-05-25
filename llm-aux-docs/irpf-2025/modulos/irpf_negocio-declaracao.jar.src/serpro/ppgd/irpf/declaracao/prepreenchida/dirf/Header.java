/*    */ package serpro.ppgd.irpf.declaracao.prepreenchida.dirf;
/*    */ 
/*    */ import serpro.ppgd.negocio.Alfa;
/*    */ import serpro.ppgd.negocio.NI;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ import serpro.ppgd.negocio.Observador;
/*    */ 
/*    */ public class Header
/*    */   extends ObjetoNegocio
/*    */ {
/*    */   private static final String TIPO_CPF = "1";
/* 12 */   private NI niFontePagadora = new NI(this, "CPF/CNPJ da Fonte Pagadora");
/* 13 */   private Alfa tipoFontePagadora = new Alfa(this, "Tipo da Fonte Pagadora", 1);
/* 14 */   private Alfa nomeFontePagadora = new Alfa(this, "Nome da Fonte Pagadora", 150);
/* 15 */   private Alfa numeroControle = new Alfa(this, "Número de Controle", 10);
/*    */ 
/*    */   
/*    */   public Header() {
/* 19 */     this.tipoFontePagadora.addObservador(new Observador()
/*    */         {
/*    */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*    */           {
/* 23 */             if (valorNovo.toString().equals("1")) {
/* 24 */               String ni = Header.this.niFontePagadora.naoFormatado();
/* 25 */               Header.this.niFontePagadora.setConteudo(ni.substring(3));
/*    */             } 
/*    */           }
/*    */         });
/*    */   }
/*    */   
/*    */   public NI getNiFontePagadora() {
/* 32 */     return this.niFontePagadora;
/*    */   }
/*    */   
/*    */   public Alfa getTipoFontePagadora() {
/* 36 */     return this.tipoFontePagadora;
/*    */   }
/*    */   
/*    */   public Alfa getNomeFontePagadora() {
/* 40 */     return this.nomeFontePagadora;
/*    */   }
/*    */   
/*    */   public Alfa getNumeroControle() {
/* 44 */     return this.numeroControle;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\declaracao\prepreenchida\dirf\Header.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */