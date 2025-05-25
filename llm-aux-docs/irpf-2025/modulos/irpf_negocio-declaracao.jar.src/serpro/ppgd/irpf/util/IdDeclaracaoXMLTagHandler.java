/*    */ package serpro.ppgd.irpf.util;
/*    */ 
/*    */ import org.xml.sax.Attributes;
/*    */ import serpro.ppgd.irpf.IdentificadorDeclaracao;
/*    */ import serpro.ppgd.persistenciagenerica.DefaultTagHandler;
/*    */ 
/*    */ public class IdDeclaracaoXMLTagHandler
/*    */   extends DefaultTagHandler {
/*    */   private IdentificadorDeclaracao idDec;
/*    */   
/*    */   public IdDeclaracaoXMLTagHandler() {
/* 12 */     this.idDec = new IdentificadorDeclaracao();
/*    */   }
/*    */   
/*    */   public IdDeclaracaoXMLTagHandler(IdentificadorDeclaracao idDec) {
/* 16 */     this.idDec = idDec;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void characters(String tagPath, String texto) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void endElement(String tagPath, String textoCompleto) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void startElement(String tagPath, Attributes attributes) {
/* 32 */     this.idDec.getCpf().setConteudo(attributes.getValue("cpf"));
/* 33 */     this.idDec.getNome().setConteudo(attributes.getValue("nome"));
/* 34 */     this.idDec.getExercicio().setConteudo(attributes.getValue("exercicio"));
/* 35 */     this.idDec.getTransmitida().setConteudo(attributes.getValue("transmitida"));
/* 36 */     this.idDec.getTipoDeclaracao().setConteudo(attributes.getValue("tipoDeclaracao"));
/* 37 */     this.idDec.getTipoDeclaracaoAES().setConteudo(attributes.getValue("tipoDeclaracaoAES"));
/* 38 */     this.idDec.getDeclaracaoRetificadora().setConteudo(attributes.getValue("declaracaoRetificadora"));
/* 39 */     this.idDec.getNumReciboTransmitido().setConteudo(attributes.getValue("numReciboTransmitido"));
/* 40 */     this.idDec.getNumReciboDecRetif().setConteudo(attributes.getValue("numReciboDecRetif"));
/* 41 */     this.idDec.getNumeroReciboDecAnterior().setConteudo(attributes.getValue("numeroReciboDecAnterior"));
/* 42 */     this.idDec.getEnderecoDiferente().setConteudo(attributes.getValue("enderecoDiferente"));
/* 43 */     this.idDec.getResultadoDeclaracao().setConteudo(attributes.getValue("resultadoDeclaracao"));
/* 44 */     this.idDec.getVersaoBeta().setConteudo(attributes.getValue("versaoBeta"));
/* 45 */     this.idDec.getPrepreenchida().setConteudo(attributes.getValue("prepreenchida"));
/* 46 */     this.idDec.getTpIniciada().setConteudo(attributes.getValue("tpIniciada"));
/* 47 */     this.idDec.getInUtilizouPGD().setConteudo(attributes.getValue("inUtilizouPGD"));
/* 48 */     this.idDec.getInUtilizouAPP().setConteudo(attributes.getValue("inUtilizouAPP"));
/* 49 */     this.idDec.getInUtilizouOnLine().setConteudo(attributes.getValue("inUtilizouOnLine"));
/* 50 */     this.idDec.getInUtilizouRascunho().setConteudo(attributes.getValue("inUtilizouRascunho"));
/* 51 */     this.idDec.getInUtilizouAssistidaFontePagadora().setConteudo(attributes.getValue("inUtilizouAssistidaFontePagadora"));
/* 52 */     this.idDec.getInUtilizouAssistidaPlanoSaude().setConteudo(attributes.getValue("inUtilizouAssistidaPlanoSaude"));
/* 53 */     this.idDec.getInUtilizouSalvarRecuperarOnLine().setConteudo(attributes.getValue("inUtilizouSalvarRecuperarOnLine"));
/*    */   }
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
/*    */   public IdentificadorDeclaracao getIdDec() {
/* 66 */     return this.idDec;
/*    */   }
/*    */   
/*    */   public void setIdDec(IdentificadorDeclaracao idDec) {
/* 70 */     this.idDec = idDec;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irp\\util\IdDeclaracaoXMLTagHandler.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */