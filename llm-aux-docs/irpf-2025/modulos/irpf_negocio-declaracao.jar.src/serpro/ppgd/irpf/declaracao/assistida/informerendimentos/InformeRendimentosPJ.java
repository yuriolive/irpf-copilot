/*     */ package serpro.ppgd.irpf.declaracao.assistida.informerendimentos;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import javax.xml.transform.Source;
/*     */ import javax.xml.transform.stream.StreamSource;
/*     */ import javax.xml.validation.Schema;
/*     */ import javax.xml.validation.SchemaFactory;
/*     */ import javax.xml.validation.Validator;
/*     */ import org.xml.sax.SAXException;
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ import serpro.ppgd.negocio.ValidadorIf;
/*     */ import serpro.ppgd.negocio.Valor;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InformeRendimentosPJ
/*     */   extends ObjetoNegocio
/*     */ {
/*     */   private HeaderInformeRendimentos header;
/*     */   private ColecaoItemRendPJ colecaoRendimentosPessoaJuridica;
/*     */   private ColecaoItemPagamento colecaoPagamentosPrevidenciaComplementar;
/*     */   private ColecaoItemPagamento colecaoPagamentosPensaoAlimenticia;
/*     */   private ColecaoItemRendIsentoOuTributacaoExclusiva rendimentosIsentos;
/*     */   private ColecaoItemRendIsentoOuTributacaoExclusiva rendimentosTributacaoExclusiva;
/*     */   private ColecaoItemRendAcm colecaoRendimentosRecebidosAcumuladamente;
/*     */   private ColecaoItemRendPJComExigibilidade colecaoRendimentosExigibilidadeSuspensa;
/*     */   private ColecaoItemPagamento pagamentos;
/*     */   public static final int TIPO_INFORME_TITULAR = 1;
/*     */   public static final int TIPO_INFORME_DEPENDENTE = 2;
/*     */   private int tipoInforme;
/*     */   
/*     */   public InformeRendimentosPJ() {
/*  56 */     this.header = new HeaderInformeRendimentos();
/*  57 */     this.colecaoRendimentosPessoaJuridica = new ColecaoItemRendPJ();
/*  58 */     this.colecaoPagamentosPrevidenciaComplementar = new ColecaoItemPagamento();
/*  59 */     this.colecaoPagamentosPensaoAlimenticia = new ColecaoItemPagamento();
/*  60 */     this.rendimentosIsentos = new ColecaoItemRendIsentoOuTributacaoExclusiva();
/*  61 */     this.rendimentosTributacaoExclusiva = new ColecaoItemRendIsentoOuTributacaoExclusiva();
/*  62 */     this.colecaoRendimentosRecebidosAcumuladamente = new ColecaoItemRendAcm();
/*  63 */     this.colecaoRendimentosExigibilidadeSuspensa = new ColecaoItemRendPJComExigibilidade();
/*  64 */     this.pagamentos = new ColecaoItemPagamento();
/*  65 */     this.tipoInforme = 0;
/*     */     
/*  67 */     adicionaValidadores();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void adicionaValidadores() {
/*  78 */     getColecaoPagamentosPensaoAlimenticia().addObservador("ObjetoInserido", new Observador()
/*     */         {
/*     */           
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/*  83 */             final ItemPagamento item = (ItemPagamento)valorNovo;
/*     */             
/*  85 */             item.getNomeBeneficiarioSelecionado().addValidador((ValidadorIf)new ValidadorNaoNulo((byte)3)
/*     */                 {
/*     */                   public RetornoValidacao validarImplementado()
/*     */                   {
/*  89 */                     if (item.isImportar()) {
/*  90 */                       return super.validarImplementado();
/*     */                     }
/*  92 */                     return null;
/*     */                   }
/*     */                 });
/*     */ 
/*     */             
/*  97 */             item.getTipoPensaoAlimenticia().addValidador((ValidadorIf)new ValidadorNaoNulo((byte)3)
/*     */                 {
/*     */                   public RetornoValidacao validarImplementado()
/*     */                   {
/* 101 */                     if (item.isImportar()) {
/* 102 */                       return super.validarImplementado();
/*     */                     }
/* 104 */                     return null;
/*     */                   }
/*     */                 });
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 111 */     getColecaoRendimentosRecebidosAcumuladamente().addObservador("ObjetoInserido", new Observador()
/*     */         {
/*     */           
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/* 116 */             ItemRendAcm item = (ItemRendAcm)valorNovo;
/*     */             
/* 118 */             item.getPensaoAlimenticiaQuadroAuxiliar().addObservador("ObjetoInserido", new Observador()
/*     */                 {
/*     */                   
/*     */                   public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */                   {
/* 123 */                     final ItemPensaoAlimenticiaRendAcm item = (ItemPensaoAlimenticiaRendAcm)valorNovo;
/*     */                     
/* 125 */                     item.getNomeAlimentandoSelecionado().addValidador((ValidadorIf)new ValidadorNaoNulo((byte)3)
/*     */                         {
/*     */                           public RetornoValidacao validarImplementado()
/*     */                           {
/* 129 */                             if (item.isImportar()) {
/* 130 */                               return super.validarImplementado();
/*     */                             }
/* 132 */                             return null;
/*     */                           }
/*     */                         });
/*     */                   }
/*     */                 });
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 141 */     getPagamentos().addObservador("ObjetoInserido", new Observador()
/*     */         {
/*     */           
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/* 146 */             final ItemPagamento item = (ItemPagamento)valorNovo;
/*     */             
/* 148 */             item.getCodigo().addValidador((ValidadorIf)new ValidadorNaoNulo((byte)3)
/*     */                 {
/*     */                   public RetornoValidacao validarImplementado()
/*     */                   {
/* 152 */                     if (item.isImportar()) {
/* 153 */                       return super.validarImplementado();
/*     */                     }
/* 155 */                     return null;
/*     */                   }
/*     */                 });
/*     */ 
/*     */             
/* 160 */             item.getTipoBeneficiario().addValidador((ValidadorIf)new ValidadorNaoNulo((byte)3)
/*     */                 {
/*     */                   public RetornoValidacao validarImplementado()
/*     */                   {
/* 164 */                     if (item.isImportar()) {
/* 165 */                       return super.validarImplementado();
/*     */                     }
/* 167 */                     return null;
/*     */                   }
/*     */                 });
/*     */ 
/*     */             
/* 172 */             item.getNomeBeneficiarioSelecionado().addValidador((ValidadorIf)new ValidadorNaoNulo((byte)3)
/*     */                 {
/*     */                   public RetornoValidacao validarImplementado()
/*     */                   {
/* 176 */                     if (item.isImportar() && !item.getTipoBeneficiario().naoFormatado().equals("1")) {
/* 177 */                       return super.validarImplementado();
/*     */                     }
/* 179 */                     return null;
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
/*     */   public void ajustar13PensoesAlimenticias() {
/* 193 */     for (ItemPagamento pensao : getColecaoPagamentosPensaoAlimenticia().itens()) {
/* 194 */       ValorPositivo valorRecebido = new ValorPositivo();
/* 195 */       valorRecebido.setConteudo((Valor)pensao.getValorPago());
/* 196 */       valorRecebido.append('+', (Valor)pensao.getValorDecimoTerceiro());
/* 197 */       pensao.getValorPago().setConteudo(valorRecebido.naoFormatado());
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean validate(String xsdFullFilename, String xmlFullFilename) {
/* 202 */     Source xmlFile = null;
/*     */     
/*     */     try {
/* 205 */       xmlFile = new StreamSource(new File(xmlFullFilename));
/* 206 */       Schema schema = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema").newSchema(new File(xsdFullFilename));
/* 207 */       Validator validator = schema.newValidator();
/* 208 */       validator.validate(xmlFile);
/* 209 */     } catch (SAXException e) {
/* 210 */       System.out.println(xmlFile.getSystemId() + " is NOT valid");
/* 211 */       System.out.println("Reason: " + e.getLocalizedMessage());
/* 212 */       return false;
/* 213 */     } catch (IOException e) {
/* 214 */       System.out.println(xmlFile.getSystemId() + " is NOT valid");
/* 215 */       System.out.println("Reason: " + e.getLocalizedMessage());
/* 216 */       return false;
/*     */     } 
/* 218 */     return true;
/*     */   }
/*     */   
/*     */   public HeaderInformeRendimentos getHeader() {
/* 222 */     return this.header;
/*     */   }
/*     */   
/*     */   public ColecaoItemRendPJ getColecaoRendimentosPessoaJuridica() {
/* 226 */     return this.colecaoRendimentosPessoaJuridica;
/*     */   }
/*     */   
/*     */   public ColecaoItemPagamento getColecaoPagamentosPrevidenciaComplementar() {
/* 230 */     return this.colecaoPagamentosPrevidenciaComplementar;
/*     */   }
/*     */   
/*     */   public ColecaoItemPagamento getColecaoPagamentosPensaoAlimenticia() {
/* 234 */     return this.colecaoPagamentosPensaoAlimenticia;
/*     */   }
/*     */   
/*     */   public ColecaoItemRendIsentoOuTributacaoExclusiva getRendimentosIsentos() {
/* 238 */     return this.rendimentosIsentos;
/*     */   }
/*     */   
/*     */   public ColecaoItemRendIsentoOuTributacaoExclusiva getRendimentosTributacaoExclusiva() {
/* 242 */     return this.rendimentosTributacaoExclusiva;
/*     */   }
/*     */   
/*     */   public ColecaoItemRendAcm getColecaoRendimentosRecebidosAcumuladamente() {
/* 246 */     return this.colecaoRendimentosRecebidosAcumuladamente;
/*     */   }
/*     */   
/*     */   public ColecaoItemRendPJComExigibilidade getColecaoRendimentosExigibilidadeSuspensa() {
/* 250 */     return this.colecaoRendimentosExigibilidadeSuspensa;
/*     */   }
/*     */   
/*     */   public ColecaoItemPagamento getPagamentos() {
/* 254 */     return this.pagamentos;
/*     */   }
/*     */   
/*     */   public int getTipoInforme() {
/* 258 */     return this.tipoInforme;
/*     */   }
/*     */   
/*     */   public void setTipoInforme(int tipoInforme) {
/* 262 */     this.tipoInforme = tipoInforme;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\declaracao\assistida\informerendimentos\InformeRendimentosPJ.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */