/*    */ package serpro.ppgd.irpf.declaracao.prepreenchida.dimob;
/*    */ 
/*    */ import serpro.ppgd.negocio.Alfa;
/*    */ import serpro.ppgd.negocio.CNPJ;
/*    */ import serpro.ppgd.negocio.NI;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ import serpro.ppgd.negocio.Observador;
/*    */ 
/*    */ public class ItemDimob
/*    */   extends ObjetoNegocio
/*    */ {
/*    */   private static final String TIPO_CPF = "2";
/* 13 */   private CNPJ cnpjDeclarante = new CNPJ(this, "CNPJ do Declarante");
/* 14 */   private Alfa nomeDeclarante = new Alfa(this, "Nome do Declarante");
/* 15 */   private Alfa tipoLocadorLocatario = new Alfa(this, "Tipo do Locador/Locatário", 1);
/* 16 */   private NI niLocadorLocatario = new NI(this, "CPF/CNPJ do Locador/Locatário");
/* 17 */   private Alfa nomeLocadorLocatario = new Alfa(this, "Nome do Locador/Locatário");
/*    */ 
/*    */   
/*    */   public ItemDimob() {
/* 21 */     this.niLocadorLocatario.addObservador(new Observador()
/*    */         {
/*    */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*    */           {
/* 25 */             if (valorNovo.toString().length() == 14 && ItemDimob.this.tipoLocadorLocatario.naoFormatado().equals("2")) {
/* 26 */               ItemDimob.this.niLocadorLocatario.setConteudo(valorNovo.toString().substring(0, 11));
/*    */             }
/*    */           }
/*    */         });
/*    */   }
/*    */   
/*    */   public CNPJ getCnpjDeclarante() {
/* 33 */     return this.cnpjDeclarante;
/*    */   }
/*    */   
/*    */   public Alfa getNomeDeclarante() {
/* 37 */     return this.nomeDeclarante;
/*    */   }
/*    */   
/*    */   public Alfa getTipoLocadorLocatario() {
/* 41 */     return this.tipoLocadorLocatario;
/*    */   }
/*    */   
/*    */   public NI getNiLocadorLocatario() {
/* 45 */     return this.niLocadorLocatario;
/*    */   }
/*    */   
/*    */   public Alfa getNomeLocadorLocatario() {
/* 49 */     return this.nomeLocadorLocatario;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\declaracao\prepreenchida\dimob\ItemDimob.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */