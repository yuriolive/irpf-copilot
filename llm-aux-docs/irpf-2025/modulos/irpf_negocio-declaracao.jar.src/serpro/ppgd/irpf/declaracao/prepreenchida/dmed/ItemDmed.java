/*     */ package serpro.ppgd.irpf.declaracao.prepreenchida.dmed;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.NI;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemDmed
/*     */   extends ObjetoNegocio
/*     */ {
/*     */   public static final String TIPO_DECLARACAO_DIRF = "0";
/*     */   public static final String TIPO_DECLARACAO_PLANO = "2";
/*     */   public static final String TIPO_DECLARACAO_PRESTADOR = "1";
/*     */   public static final String TIPO_DECLARACAO_PLANO_PRESTADOR = "3";
/*     */   private static final String TIPO_CPF = "1";
/*  20 */   private NI cpfCnpjDeclarante = new NI(this, "CPF/CNPJ do declarante");
/*  21 */   private Alfa tipoDeclarante = new Alfa(this, "Tipo do declarante");
/*  22 */   private Alfa nomeDeclarante = new Alfa(this, "Nome do declarante");
/*  23 */   private Alfa tipoDeclaracao = new Alfa(this, "Tipo da declaração");
/*  24 */   private ItemDmedPessoaFisica titularResponsavel = new ItemDmedPessoaFisica();
/*  25 */   private ItemDmedPessoaFisica dependenteBeneficiarioUm = new ItemDmedPessoaFisica();
/*  26 */   private ItemDmedPessoaFisica dependenteBeneficiarioDois = new ItemDmedPessoaFisica();
/*  27 */   private ItemDmedPessoaFisica dependenteBeneficiarioTres = new ItemDmedPessoaFisica();
/*  28 */   private ItemDmedPessoaFisica dependenteBeneficiarioQuatro = new ItemDmedPessoaFisica();
/*  29 */   private ItemDmedPessoaFisica dependenteBeneficiarioCinco = new ItemDmedPessoaFisica();
/*  30 */   private ItemDmedPessoaFisica dependenteBeneficiarioSeis = new ItemDmedPessoaFisica();
/*  31 */   private ItemDmedPessoaFisica dependenteBeneficiarioSete = new ItemDmedPessoaFisica();
/*  32 */   private ItemDmedPessoaFisica dependenteBeneficiarioOito = new ItemDmedPessoaFisica();
/*  33 */   private ItemDmedPessoaFisica dependenteBeneficiarioNove = new ItemDmedPessoaFisica();
/*  34 */   private ItemDmedPessoaFisica dependenteBeneficiarioDez = new ItemDmedPessoaFisica();
/*     */ 
/*     */   
/*     */   public ItemDmed() {
/*  38 */     this.cpfCnpjDeclarante.addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/*  42 */             if (valorNovo.toString().length() == 14 && ItemDmed.this.tipoDeclarante.naoFormatado().equals("1")) {
/*  43 */               ItemDmed.this.cpfCnpjDeclarante.setConteudo(valorNovo.toString().substring(3));
/*     */             }
/*     */           }
/*     */         });
/*  47 */     this.tipoDeclarante.addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/*  51 */             if (valorNovo.toString().equals("1") && ItemDmed.this.cpfCnpjDeclarante.naoFormatado().length() == 14) {
/*  52 */               ItemDmed.this.cpfCnpjDeclarante.setConteudo(ItemDmed.this.cpfCnpjDeclarante.naoFormatado().substring(3));
/*     */             }
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public List<ItemDmedPessoaFisica> obterTodos() {
/*  59 */     List<ItemDmedPessoaFisica> retorno = new ArrayList<>(11);
/*     */     
/*  61 */     retorno.add(this.titularResponsavel);
/*  62 */     retorno.add(this.dependenteBeneficiarioUm);
/*  63 */     retorno.add(this.dependenteBeneficiarioDois);
/*  64 */     retorno.add(this.dependenteBeneficiarioTres);
/*  65 */     retorno.add(this.dependenteBeneficiarioQuatro);
/*  66 */     retorno.add(this.dependenteBeneficiarioCinco);
/*  67 */     retorno.add(this.dependenteBeneficiarioSeis);
/*  68 */     retorno.add(this.dependenteBeneficiarioSete);
/*  69 */     retorno.add(this.dependenteBeneficiarioOito);
/*  70 */     retorno.add(this.dependenteBeneficiarioNove);
/*  71 */     retorno.add(this.dependenteBeneficiarioDez);
/*     */     
/*  73 */     return retorno;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemDmedPessoaFisica obterDependentePorIndice(int indice) {
/*  78 */     ItemDmedPessoaFisica retorno = null;
/*     */     
/*  80 */     switch (indice) {
/*     */       case 1:
/*  82 */         retorno = this.dependenteBeneficiarioUm;
/*     */         break;
/*     */       case 2:
/*  85 */         retorno = this.dependenteBeneficiarioDois;
/*     */         break;
/*     */       case 3:
/*  88 */         retorno = this.dependenteBeneficiarioTres;
/*     */         break;
/*     */       case 4:
/*  91 */         retorno = this.dependenteBeneficiarioQuatro;
/*     */         break;
/*     */       case 5:
/*  94 */         retorno = this.dependenteBeneficiarioCinco;
/*     */         break;
/*     */       case 6:
/*  97 */         retorno = this.dependenteBeneficiarioSeis;
/*     */         break;
/*     */       case 7:
/* 100 */         retorno = this.dependenteBeneficiarioSete;
/*     */         break;
/*     */       case 8:
/* 103 */         retorno = this.dependenteBeneficiarioOito;
/*     */         break;
/*     */       case 9:
/* 106 */         retorno = this.dependenteBeneficiarioNove;
/*     */         break;
/*     */       case 10:
/* 109 */         retorno = this.dependenteBeneficiarioDez;
/*     */         break;
/*     */     } 
/*     */     
/* 113 */     return retorno;
/*     */   }
/*     */   
/*     */   public NI getCpfCnpjDeclarante() {
/* 117 */     return this.cpfCnpjDeclarante;
/*     */   }
/*     */   
/*     */   public Alfa getTipoDeclarante() {
/* 121 */     return this.tipoDeclarante;
/*     */   }
/*     */   
/*     */   public Alfa getNomeDeclarante() {
/* 125 */     return this.nomeDeclarante;
/*     */   }
/*     */   
/*     */   public Alfa getTipoDeclaracao() {
/* 129 */     return this.tipoDeclaracao;
/*     */   }
/*     */   
/*     */   public ItemDmedPessoaFisica getTitularResponsavel() {
/* 133 */     return this.titularResponsavel;
/*     */   }
/*     */   
/*     */   public ItemDmedPessoaFisica getDependenteBeneficiarioUm() {
/* 137 */     return this.dependenteBeneficiarioUm;
/*     */   }
/*     */   
/*     */   public ItemDmedPessoaFisica getDependenteBeneficiarioDois() {
/* 141 */     return this.dependenteBeneficiarioDois;
/*     */   }
/*     */   
/*     */   public ItemDmedPessoaFisica getDependenteBeneficiarioTres() {
/* 145 */     return this.dependenteBeneficiarioTres;
/*     */   }
/*     */   
/*     */   public ItemDmedPessoaFisica getDependenteBeneficiarioQuatro() {
/* 149 */     return this.dependenteBeneficiarioQuatro;
/*     */   }
/*     */   
/*     */   public ItemDmedPessoaFisica getDependenteBeneficiarioCinco() {
/* 153 */     return this.dependenteBeneficiarioCinco;
/*     */   }
/*     */   
/*     */   public ItemDmedPessoaFisica getDependenteBeneficiarioSeis() {
/* 157 */     return this.dependenteBeneficiarioSeis;
/*     */   }
/*     */   
/*     */   public ItemDmedPessoaFisica getDependenteBeneficiarioSete() {
/* 161 */     return this.dependenteBeneficiarioSete;
/*     */   }
/*     */   
/*     */   public ItemDmedPessoaFisica getDependenteBeneficiarioOito() {
/* 165 */     return this.dependenteBeneficiarioOito;
/*     */   }
/*     */   
/*     */   public ItemDmedPessoaFisica getDependenteBeneficiarioNove() {
/* 169 */     return this.dependenteBeneficiarioNove;
/*     */   }
/*     */   
/*     */   public ItemDmedPessoaFisica getDependenteBeneficiarioDez() {
/* 173 */     return this.dependenteBeneficiarioDez;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\declaracao\prepreenchida\dmed\ItemDmed.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */