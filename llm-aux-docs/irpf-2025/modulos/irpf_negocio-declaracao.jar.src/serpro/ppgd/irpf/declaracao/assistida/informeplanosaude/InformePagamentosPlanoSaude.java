/*     */ package serpro.ppgd.irpf.declaracao.assistida.informeplanosaude;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import javax.xml.transform.Source;
/*     */ import javax.xml.transform.stream.StreamSource;
/*     */ import javax.xml.validation.Schema;
/*     */ import javax.xml.validation.SchemaFactory;
/*     */ import javax.xml.validation.Validator;
/*     */ import org.xml.sax.SAXException;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ import serpro.ppgd.negocio.ValidadorIf;
/*     */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorNaoNulo;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InformePagamentosPlanoSaude
/*     */   extends ObjetoNegocio
/*     */ {
/*     */   private HeaderInformePagamentos header;
/*     */   private ColecaoItemPagamentoPlanoSaude colecaoValorPago;
/*     */   private ColecaoItemReembolsoPlanoSaude colecaoValorReembolsado;
/*     */   public static final int TIPO_INFORME_TITULAR = 1;
/*     */   public static final int TIPO_INFORME_DEPENDENTE = 2;
/*     */   private int tipoInforme;
/*     */   
/*     */   public InformePagamentosPlanoSaude(DeclaracaoIRPF decAberta) {
/*  45 */     setHeader(new HeaderInformePagamentos());
/*  46 */     setColecaoValorPago(new ColecaoItemPagamentoPlanoSaude());
/*  47 */     setColecaoValorReembolsado(new ColecaoItemReembolsoPlanoSaude());
/*  48 */     setTipoInforme(0);
/*     */     
/*  50 */     adicionaValidadores();
/*     */   }
/*     */   
/*     */   public void adicionaValidadores() {
/*  54 */     getColecaoValorPago().addObservador("ObjetoInserido", new Observador()
/*     */         {
/*     */           
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/*  59 */             final ItemPagamentoPlanoSaude item = (ItemPagamentoPlanoSaude)valorNovo;
/*     */             
/*  61 */             item.getTipoBeneficiario().addValidador((ValidadorIf)new ValidadorNaoNulo((byte)3)
/*     */                 {
/*     */ 
/*     */                   
/*     */                   public RetornoValidacao validarImplementado()
/*     */                   {
/*  67 */                     if (item.isImportar()) {
/*  68 */                       RetornoValidacao ret = super.validarImplementado();
/*  69 */                       if (ret != null);
/*     */ 
/*     */                       
/*  72 */                       return ret;
/*     */                     } 
/*  74 */                     return null;
/*     */                   }
/*     */                 });
/*     */ 
/*     */             
/*  79 */             item.getNomeBeneficiarioSelecionado().addValidador((ValidadorIf)new ValidadorNaoNulo((byte)3)
/*     */                 {
/*     */                   
/*     */                   public RetornoValidacao validarImplementado()
/*     */                   {
/*  84 */                     if (item.isImportar() && !item.getTipoBeneficiario().naoFormatado().equals("1")) {
/*  85 */                       RetornoValidacao ret = super.validarImplementado();
/*  86 */                       if (ret != null);
/*     */ 
/*     */                       
/*  89 */                       return ret;
/*     */                     } 
/*  91 */                     return null;
/*     */                   }
/*     */                 });
/*     */           }
/*     */         });
/*     */ 
/*     */     
/*  98 */     getColecaoValorReembolsado().addObservador("ObjetoInserido", new Observador()
/*     */         {
/*     */           
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/* 103 */             final ItemPagamentoPlanoSaude item = (ItemPagamentoPlanoSaude)valorNovo;
/*     */             
/* 105 */             item.getCodigo().addValidador((ValidadorIf)new ValidadorNaoNulo((byte)3)
/*     */                 {
/*     */                   public RetornoValidacao validarImplementado()
/*     */                   {
/* 109 */                     if (item.isImportar()) {
/* 110 */                       return super.validarImplementado();
/*     */                     }
/* 112 */                     return null;
/*     */                   }
/*     */                 });
/*     */ 
/*     */             
/* 117 */             item.getTipoBeneficiario().addValidador((ValidadorIf)new ValidadorNaoNulo((byte)3)
/*     */                 {
/*     */                   public RetornoValidacao validarImplementado()
/*     */                   {
/* 121 */                     if (item.isImportar()) {
/* 122 */                       return super.validarImplementado();
/*     */                     }
/* 124 */                     return null;
/*     */                   }
/*     */                 });
/*     */ 
/*     */             
/* 129 */             item.getNomeBeneficiarioSelecionado().addValidador((ValidadorIf)new ValidadorNaoNulo((byte)3)
/*     */                 {
/*     */                   public RetornoValidacao validarImplementado()
/*     */                   {
/* 133 */                     if (item.isImportar() && !item.getTipoBeneficiario().naoFormatado().equals("1")) {
/* 134 */                       return super.validarImplementado();
/*     */                     }
/* 136 */                     return null;
/*     */                   }
/*     */                 });
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean validate(String xsdFullFilename, String xmlFullFilename) {
/* 153 */     Source xmlFile = null;
/*     */     
/*     */     try {
/* 156 */       xmlFile = new StreamSource(new File(xmlFullFilename));
/* 157 */       Schema schema = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema").newSchema(new File(xsdFullFilename));
/* 158 */       Validator validator = schema.newValidator();
/* 159 */       validator.validate(xmlFile);
/* 160 */     } catch (SAXException e) {
/* 161 */       System.out.println(xmlFile.getSystemId() + " is NOT valid");
/* 162 */       System.out.println("Reason: " + e.getLocalizedMessage());
/* 163 */       return false;
/* 164 */     } catch (IOException e) {
/* 165 */       System.out.println(xmlFile.getSystemId() + " is NOT valid");
/* 166 */       System.out.println("Reason: " + e.getLocalizedMessage());
/* 167 */       return false;
/*     */     } 
/* 169 */     return true;
/*     */   }
/*     */   
/*     */   public HeaderInformePagamentos getHeader() {
/* 173 */     return this.header;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColecaoItemPagamentoPlanoSaude getColecaoValorPago() {
/* 180 */     return this.colecaoValorPago;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColecaoValorPago(ColecaoItemPagamentoPlanoSaude colecaoValorPago) {
/* 187 */     this.colecaoValorPago = colecaoValorPago;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColecaoItemReembolsoPlanoSaude getColecaoValorReembolsado() {
/* 194 */     return this.colecaoValorReembolsado;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColecaoValorReembolsado(ColecaoItemReembolsoPlanoSaude colecaoValorReembolsado) {
/* 201 */     this.colecaoValorReembolsado = colecaoValorReembolsado;
/*     */   }
/*     */   
/*     */   private void setHeader(HeaderInformePagamentos header) {
/* 205 */     this.header = header;
/*     */   }
/*     */   
/*     */   public int getTipoInforme() {
/* 209 */     return this.tipoInforme;
/*     */   }
/*     */   
/*     */   public void setTipoInforme(int tipoInforme) {
/* 213 */     this.tipoInforme = tipoInforme;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\declaracao\assistida\informeplanosaude\InformePagamentosPlanoSaude.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */