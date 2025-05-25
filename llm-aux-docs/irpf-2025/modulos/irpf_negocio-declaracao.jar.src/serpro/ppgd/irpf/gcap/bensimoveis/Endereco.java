/*     */ package serpro.ppgd.irpf.gcap.bensimoveis;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.CEP;
/*     */ import serpro.ppgd.negocio.Codigo;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ 
/*     */ 
/*     */ public class Endereco
/*     */   extends ObjetoNegocio
/*     */ {
/*  17 */   private Codigo tipoLogradouro = new Codigo(this, "Tipo de logradouro", CadastroTabelasIRPF.recuperarTiposLogradouro());
/*     */   
/*  19 */   private Alfa logradouro = new Alfa(this, "Logradouro", 40);
/*     */   
/*  21 */   private Alfa logradouroEx = new Alfa(this, "Logradouro", 40);
/*     */   
/*  23 */   private Alfa numero = new Alfa(this, "Número", 6);
/*     */   
/*  25 */   private Alfa numeroEx = new Alfa(this, "Número", 6);
/*     */   
/*  27 */   private Alfa complemento = new Alfa(this, "Complemento", 21);
/*     */   
/*  29 */   private Alfa complementoEx = new Alfa(this, "Complemento", 21);
/*     */   
/*  31 */   private Alfa bairro = new Alfa(this, "Bairro", 20);
/*     */   
/*  33 */   private Alfa bairroEx = new Alfa(this, "Bairro", 20);
/*     */   
/*  35 */   private Codigo uf = new Codigo(this, "UF", CadastroTabelasIRPF.recuperarUFs(1));
/*     */   
/*  37 */   private Codigo municipio = new Codigo(this, "Município", new ArrayList());
/*     */   
/*  39 */   private Alfa cidadeEx = new Alfa(this, "Cidade", 40);
/*     */   
/*  41 */   private CEP cep = new CEP(this, "CEP");
/*     */   
/*  43 */   private CEP codigoPostalEx = new CEP(this, "Código postal");
/*     */   
/*  45 */   private Codigo paisEx = new Codigo(this, "País", CadastroTabelasIRPF.recuperarPaisesExterior());
/*     */   
/*     */   public Endereco() {
/*  48 */     getTipoLogradouro().setColunaFiltro(1);
/*  49 */     getUf().setColunaFiltro(1);
/*     */     
/*  51 */     getUf().addObservador(new Observador()
/*     */         {
/*     */           
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/*  56 */             if (Endereco.this.uf.isVazio()) {
/*  57 */               Endereco.this.municipio.setColecaoElementoTabela(new ArrayList());
/*     */             } else {
/*  59 */               String strUf = Endereco.this.uf.getConteudoAtual(0);
/*  60 */               Endereco.this.municipio.setColecaoElementoTabela(CadastroTabelasIRPF.recuperarMunicipios(strUf, 1));
/*     */             } 
/*     */           }
/*     */         });
/*  64 */     setReadOnlyEndereco();
/*     */   }
/*     */   
/*     */   public void setReadOnlyEndereco() {
/*  68 */     this.tipoLogradouro.setReadOnly(true);
/*  69 */     this.logradouro.setReadOnly(true);
/*  70 */     this.logradouroEx.setReadOnly(true);
/*  71 */     this.numero.setReadOnly(true);
/*  72 */     this.numeroEx.setReadOnly(true);
/*  73 */     this.complemento.setReadOnly(true);
/*  74 */     this.complementoEx.setReadOnly(true);
/*  75 */     this.bairro.setReadOnly(true);
/*  76 */     this.bairroEx.setReadOnly(true);
/*  77 */     this.uf.setReadOnly(true);
/*  78 */     this.municipio.setReadOnly(true);
/*  79 */     this.cidadeEx.setReadOnly(true);
/*  80 */     this.cep.setReadOnly(true);
/*  81 */     this.codigoPostalEx.setReadOnly(true);
/*  82 */     this.paisEx.setReadOnly(true);
/*     */   }
/*     */   
/*     */   public List<Informacao> recuperarListaCamposPendenciaAbaIdentificacao() {
/*  86 */     List<Informacao> lista = new ArrayList<>();
/*  87 */     lista.add(getTipoLogradouro());
/*  88 */     lista.add(getLogradouro());
/*  89 */     lista.add(getLogradouroEx());
/*  90 */     lista.add(getNumero());
/*  91 */     lista.add(getNumeroEx());
/*  92 */     lista.add(getComplemento());
/*  93 */     lista.add(getComplementoEx());
/*  94 */     lista.add(getBairro());
/*  95 */     lista.add(getBairroEx());
/*  96 */     lista.add(getUf());
/*  97 */     lista.add(getMunicipio());
/*  98 */     lista.add(getPaisEx());
/*  99 */     lista.add(getCidadeEx());
/* 100 */     lista.add(getCep());
/* 101 */     lista.add(getCodigoPostalEx());
/*     */     
/* 103 */     return lista;
/*     */   }
/*     */   
/*     */   public Codigo getTipoLogradouro() {
/* 107 */     return this.tipoLogradouro;
/*     */   }
/*     */   
/*     */   public Alfa getLogradouro() {
/* 111 */     return this.logradouro;
/*     */   }
/*     */   
/*     */   public Alfa getNumero() {
/* 115 */     return this.numero;
/*     */   }
/*     */   
/*     */   public Alfa getComplemento() {
/* 119 */     return this.complemento;
/*     */   }
/*     */   
/*     */   public Alfa getBairro() {
/* 123 */     return this.bairro;
/*     */   }
/*     */   
/*     */   public Codigo getUf() {
/* 127 */     return this.uf;
/*     */   }
/*     */   
/*     */   public Codigo getMunicipio() {
/* 131 */     return this.municipio;
/*     */   }
/*     */   
/*     */   public CEP getCep() {
/* 135 */     return this.cep;
/*     */   }
/*     */   
/*     */   public CEP getCodigoPostalEx() {
/* 139 */     return this.codigoPostalEx;
/*     */   }
/*     */   
/*     */   public Codigo getPaisEx() {
/* 143 */     return this.paisEx;
/*     */   }
/*     */   
/*     */   public Alfa getLogradouroEx() {
/* 147 */     return this.logradouroEx;
/*     */   }
/*     */   
/*     */   public Alfa getNumeroEx() {
/* 151 */     return this.numeroEx;
/*     */   }
/*     */   
/*     */   public Alfa getComplementoEx() {
/* 155 */     return this.complementoEx;
/*     */   }
/*     */   
/*     */   public Alfa getBairroEx() {
/* 159 */     return this.bairroEx;
/*     */   }
/*     */   
/*     */   public Alfa getCidadeEx() {
/* 163 */     return this.cidadeEx;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\bensimoveis\Endereco.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */