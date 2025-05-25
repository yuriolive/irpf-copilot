/*     */ package serpro.ppgd.irpf.rendIsentos;
/*     */ 
/*     */ import serpro.ppgd.cacheni.CacheNI;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.ObservadorEspacosDuplicados;
/*     */ import serpro.ppgd.irpf.ValidadorNaoNuloIRPF;
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.NI;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ import serpro.ppgd.negocio.ValidadorDefault;
/*     */ import serpro.ppgd.negocio.ValidadorIf;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorNI;
/*     */ 
/*     */ 
/*     */ public class ItemQuadroPensaoMolestiaGrave
/*     */   extends ItemQuadroAuxiliar
/*     */ {
/*     */   public static final String NOME_VALOR_TOTAL = "Total do Rendimento";
/*     */   public static final String NOME_VALOR_IRRF = "IRRF";
/*     */   public static final String NOME_VALOR_IRRF_13SALARIO = "IRRF sobre o 13º Salário";
/*     */   public static final String NOME_VALOR_PREVIDENCIA_OFICIAL = "Previdência Oficial";
/*     */   public static final String NOME_VALOR_RENDIMENTO = "Rendimento";
/*  27 */   private NI niEmpresa = new NI(this, "CPF/CNPJ da fonte pagadora");
/*  28 */   private Alfa nomeFonte = new Alfa(this, "Nome da fonte pagadora", 60);
/*  29 */   protected ValorPositivo valorTotal = new ValorPositivo(this, "Total do Rendimento");
/*  30 */   protected ValorPositivo valorIRRF = new ValorPositivo(this, "IRRF");
/*     */   
/*  32 */   protected ValorPositivo valorIRRF13Salario = new ValorPositivo(this, "IRRF sobre o 13º Salário");
/*  33 */   protected ValorPositivo valorPrevidenciaOficial = new ValorPositivo(this, "Previdência Oficial");
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemQuadroPensaoMolestiaGrave(DeclaracaoIRPF dec) {
/*  38 */     super(dec);
/*     */     
/*  40 */     CacheNI.getInstancia().registrarNINome(this.niEmpresa, this.nomeFonte);
/*     */     
/*  42 */     getNiEmpresa().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3));
/*  43 */     getNiEmpresa().addValidador((ValidadorIf)new ValidadorNI((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/*  47 */             setMensagemValidacao(MensagemUtil.getMensagem("campo_invalido", new String[] { getInformacao().getNomeCampo() }));
/*     */             
/*  49 */             return super.validarImplementado();
/*     */           }
/*     */         });
/*     */ 
/*     */     
/*  54 */     getNiEmpresa().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/*  58 */             if (ItemQuadroPensaoMolestiaGrave.this.getCpfBeneficiario().formatado().equals(getInformacao().formatado())) {
/*  59 */               return new RetornoValidacao(MensagemUtil.getMensagem("rendisentos_pensao_cpf_beneficiario_igual_fonte_pagadora"), 
/*  60 */                   getSeveridade());
/*     */             }
/*  62 */             return null;
/*     */           }
/*     */         });
/*     */ 
/*     */     
/*  67 */     getNomeFonte().addObservador((Observador)new ObservadorEspacosDuplicados());
/*  68 */     getNomeFonte().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)2));
/*     */ 
/*     */     
/*  71 */     getValor().getListaValidadores().clear();
/*     */     
/*  73 */     getValor().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/*  77 */             if (ItemQuadroPensaoMolestiaGrave.this.getValor13Salario().isVazio()) {
/*  78 */               return super.validarImplementado();
/*     */             }
/*  80 */             return null;
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  87 */     getValor().setNomeCampo("Rendimento");
/*     */     
/*  89 */     getValor13Salario().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/*  93 */             if (ItemQuadroPensaoMolestiaGrave.this.getValor().isVazio()) {
/*  94 */               return super.validarImplementado();
/*     */             }
/*  96 */             return null;
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */     
/* 102 */     CalculaValorRend calculaValorRend = new CalculaValorRend();
/* 103 */     getValor().addObservador(calculaValorRend);
/* 104 */     getValor13Salario().addObservador(calculaValorRend);
/*     */   }
/*     */   
/*     */   public Alfa getNomeFonte() {
/* 108 */     return this.nomeFonte;
/*     */   }
/*     */   
/*     */   public NI getNiEmpresa() {
/* 112 */     return this.niEmpresa;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorIRRF() {
/* 116 */     return this.valorIRRF;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorTotal() {
/* 120 */     return this.valorTotal;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorIRRF13Salario() {
/* 124 */     return this.valorIRRF13Salario;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorPrevidenciaOficial() {
/* 128 */     return this.valorPrevidenciaOficial;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void adicionaCamposParaPendencia() {
/* 137 */     super.adicionaCamposParaPendencia();
/* 138 */     this.camposPendencia.add(this.niEmpresa);
/* 139 */     this.camposPendencia.add(this.nomeFonte);
/* 140 */     this.camposPendencia.add(this.valorIRRF);
/* 141 */     this.camposPendencia.add(this.valor13Salario);
/* 142 */     this.camposPendencia.add(this.valorIRRF13Salario);
/* 143 */     this.camposPendencia.add(this.valorPrevidenciaOficial);
/*     */   }
/*     */ 
/*     */   
/*     */   private class CalculaValorRend
/*     */     extends Observador
/*     */   {
/*     */     public void notifica(Object arg0, String arg1, Object arg2, Object arg3) {
/* 151 */       Valor total = new Valor();
/* 152 */       total.append('+', ItemQuadroPensaoMolestiaGrave.this.getValor());
/* 153 */       total.append('+', (Valor)ItemQuadroPensaoMolestiaGrave.this.getValor13Salario());
/* 154 */       ItemQuadroPensaoMolestiaGrave.this.getValorTotal().setConteudo(total);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NI getNIFontePagadora() {
/* 161 */     return getNiEmpresa();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getValorRendimento() {
/* 167 */     return (Valor)getValorTotal();
/*     */   }
/*     */ 
/*     */   
/*     */   public Alfa getNomeFontePagadora() {
/* 172 */     return getNomeFonte();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 178 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendIsentos\ItemQuadroPensaoMolestiaGrave.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */