/*    */ package serpro.ppgd.irpf.declaracao.prepreenchida.dirf;
/*    */ 
/*    */ import serpro.ppgd.negocio.Alfa;
/*    */ import serpro.ppgd.negocio.NI;
/*    */ import serpro.ppgd.negocio.Observador;
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ public class ItemDirfPagamentos
/*    */   extends ItemDirf
/*    */ {
/*    */   private static final String TIPO_CPF = "1";
/*    */   private static final String TIPO_JUSTICA_TRABALHISTA = "1";
/*    */   private static final String TIPO_JUSTICA_NAO_TRABALHISTA = "2";
/* 14 */   private NI niAdvogadoEscritorio = new NI(this, "CPF do Advogado / CNPJ do Escritório de Advocacia");
/* 15 */   private Alfa tipoPessoa = new Alfa(this, "Tipo Pessoa", 1);
/* 16 */   private Alfa nomeAdvogadoEscritorio = new Alfa(this, "Nome do Advogado / Nome Empresarial");
/* 17 */   private Alfa tipoJustica = new Alfa(this, "Tipo de Justiça", 1);
/* 18 */   private Valor pagamentoAdvogado = new Valor(this, "Pagamento Advogado");
/*    */ 
/*    */   
/*    */   public ItemDirfPagamentos() {
/* 22 */     this.tipoPessoa.addObservador(new Observador()
/*    */         {
/*    */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*    */           {
/* 26 */             if (valorNovo.toString().equals("1")) {
/* 27 */               String ni = ItemDirfPagamentos.this.niAdvogadoEscritorio.naoFormatado();
/* 28 */               ItemDirfPagamentos.this.niAdvogadoEscritorio.setConteudo(ni.substring(3));
/*    */             } 
/*    */           }
/*    */         });
/*    */   }
/*    */   
/*    */   public Boolean isJusticaTrabalhista() {
/* 35 */     return Boolean.valueOf(this.tipoJustica.naoFormatado().equals("1") ? true : (this.tipoJustica.naoFormatado().equals("2") ? Boolean.valueOf(false) : 
/* 36 */         null).booleanValue());
/*    */   }
/*    */   
/*    */   public NI getNiAdvogadoEscritorio() {
/* 40 */     return this.niAdvogadoEscritorio;
/*    */   }
/*    */   
/*    */   public Alfa getTipoPessoa() {
/* 44 */     return this.tipoPessoa;
/*    */   }
/*    */   
/*    */   public Alfa getNomeAdvogadoEscritorio() {
/* 48 */     return this.nomeAdvogadoEscritorio;
/*    */   }
/*    */   
/*    */   public Alfa getTipoJustica() {
/* 52 */     return this.tipoJustica;
/*    */   }
/*    */   
/*    */   public Valor getPagamentoAdvogado() {
/* 56 */     return this.pagamentoAdvogado;
/*    */   }
/*    */   
/*    */   public void setPagamentoAdvogado(Valor pagamentoAdvogado) {
/* 60 */     this.pagamentoAdvogado = pagamentoAdvogado;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\declaracao\prepreenchida\dirf\ItemDirfPagamentos.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */