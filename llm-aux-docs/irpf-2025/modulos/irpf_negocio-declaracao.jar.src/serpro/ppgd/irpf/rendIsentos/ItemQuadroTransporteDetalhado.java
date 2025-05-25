/*     */ package serpro.ppgd.irpf.rendIsentos;
/*     */ 
/*     */ import java.lang.ref.WeakReference;
/*     */ import serpro.ppgd.cacheni.CacheNI;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.ObservadorEspacosDuplicados;
/*     */ import serpro.ppgd.irpf.ValidadorCPFIRPF;
/*     */ import serpro.ppgd.irpf.ValidadorNaoNuloIRPF;
/*     */ import serpro.ppgd.irpf.dependentes.Dependente;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.CNPJ;
/*     */ import serpro.ppgd.negocio.CPF;
/*     */ import serpro.ppgd.negocio.ConstantesGlobais;
/*     */ import serpro.ppgd.negocio.Data;
/*     */ import serpro.ppgd.negocio.NI;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ import serpro.ppgd.negocio.ValidadorDefault;
/*     */ import serpro.ppgd.negocio.ValidadorIf;
/*     */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorCNPJ;
/*     */ 
/*     */ public class ItemQuadroTransporteDetalhado
/*     */   extends ItemQuadroAuxiliar
/*     */ {
/*  27 */   private CPF cpfAlimentante = new CPF(this, "CPF do Alimentante");
/*  28 */   private CNPJ cnpjEmpresa = new CNPJ(this, "CNPJ da fonte pagadora");
/*  29 */   private Alfa nomeFonte = new Alfa(this, "Nome da fonte pagadora", 60);
/*     */ 
/*     */   
/*     */   private boolean parcIsentaAposentadoria = false;
/*     */ 
/*     */   
/*     */   public ItemQuadroTransporteDetalhado(DeclaracaoIRPF dec) {
/*  36 */     super(dec);
/*     */     
/*  38 */     CacheNI.getInstancia().registrarNINome((NI)this.cnpjEmpresa, this.nomeFonte);
/*     */     
/*  40 */     getCnpjEmpresa().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/*  43 */             if ("00000".equals(ItemQuadroTransporteDetalhado.this.getCodBem().naoFormatado()) || ItemQuadroTransporteDetalhado.this.getCodBem().isVazio()) {
/*  44 */               return super.validarImplementado();
/*     */             }
/*  46 */             return null;
/*     */           }
/*     */         });
/*  49 */     getCnpjEmpresa().addValidador((ValidadorIf)new ValidadorCNPJ((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/*  52 */             if ("00000".equals(ItemQuadroTransporteDetalhado.this.getCodBem().naoFormatado()) || ItemQuadroTransporteDetalhado.this.getCodBem().isVazio()) {
/*  53 */               setMensagemValidacao(MensagemUtil.getMensagem("campo_invalido", new String[] { getInformacao().getNomeCampo() }));
/*  54 */               return super.validarImplementado();
/*     */             } 
/*  56 */             return null;
/*     */           }
/*     */         });
/*     */     
/*  60 */     getNomeFonte().addObservador((Observador)new ObservadorEspacosDuplicados());
/*  61 */     getNomeFonte().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)2));
/*     */     
/*  63 */     getCpfAlimentante().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3));
/*  64 */     getCpfAlimentante().addValidador((ValidadorIf)new ValidadorCPFIRPF((byte)3));
/*     */   }
/*     */   
/*     */   public ItemQuadroTransporteDetalhado(final DeclaracaoIRPF dec, boolean parcIsentaAposentadoria) {
/*  68 */     this(dec);
/*     */     
/*  70 */     this.parcIsentaAposentadoria = parcIsentaAposentadoria;
/*     */     
/*  72 */     if (parcIsentaAposentadoria) {
/*     */ 
/*     */       
/*  75 */       getTipoBeneficiario().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */           {
/*     */             
/*     */             public RetornoValidacao validarImplementado()
/*     */             {
/*  80 */               Data dataNascimento = null;
/*     */               
/*  82 */               if (ItemQuadroTransporteDetalhado.this.getTipoBeneficiario().naoFormatado().equals("Titular")) {
/*  83 */                 dataNascimento = dec.getContribuinte().getDataNascimento();
/*     */               }
/*     */               
/*  86 */               return ItemQuadroTransporteDetalhado.verificaDataNascimento(dataNascimento);
/*     */             }
/*     */           });
/*     */ 
/*     */ 
/*     */       
/*  92 */       getCpfBeneficiario().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */           {
/*     */             
/*     */             public RetornoValidacao validarImplementado()
/*     */             {
/*  97 */               Data dataNascimento = null;
/*     */               
/*  99 */               if (ItemQuadroTransporteDetalhado.this.getTipoBeneficiario().naoFormatado().equals("Dependente")) {
/* 100 */                 Dependente d = dec.getDependentes().getDependenteByCpf(ItemQuadroTransporteDetalhado.this.getCpfBeneficiario().naoFormatado());
/* 101 */                 if (d != null) {
/* 102 */                   dataNascimento = d.getDataNascimento();
/*     */                 }
/*     */               } 
/*     */               
/* 106 */               return ItemQuadroTransporteDetalhado.verificaDataNascimento(dataNascimento);
/*     */             }
/*     */           });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static RetornoValidacao verificaDataNascimento(Data dataNascimento) {
/* 118 */     if (dataNascimento != null) {
/* 119 */       String DATA_NASCIMENTO_65_ANOS = "31/12/" + Integer.parseInt(ConstantesGlobais.EXERCICIO_ANTERIOR) - 65;
/* 120 */       Data data65Anos = new Data();
/* 121 */       data65Anos.setConteudo(DATA_NASCIMENTO_65_ANOS);
/*     */       
/* 123 */       if (dataNascimento.maisNova(data65Anos)) {
/* 124 */         return new RetornoValidacao(MensagemUtil.getMensagem("rendisentos_quadro_auxiliar_parc_isenta_aposentadoria_idade_beneficiario"), (byte)3);
/*     */       }
/*     */     } 
/*     */     
/* 128 */     return null;
/*     */   }
/*     */   
/*     */   public ItemQuadroTransporteDetalhado(DeclaracaoIRPF dec, ObjetoNegocio parent) {
/* 132 */     this(dec);
/* 133 */     this.parent = new WeakReference<>(parent);
/*     */   }
/*     */   
/*     */   public Alfa getNomeFonte() {
/* 137 */     return this.nomeFonte;
/*     */   }
/*     */   
/*     */   public CNPJ getCnpjEmpresa() {
/* 141 */     return this.cnpjEmpresa;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void adicionaCamposParaPendencia() {
/* 150 */     super.adicionaCamposParaPendencia();
/* 151 */     this.camposPendencia.add(this.cnpjEmpresa);
/* 152 */     this.camposPendencia.add(this.nomeFonte);
/*     */   }
/*     */ 
/*     */   
/*     */   public NI getNIFontePagadora() {
/* 157 */     return (NI)getCnpjEmpresa();
/*     */   }
/*     */ 
/*     */   
/*     */   public Alfa getNomeFontePagadora() {
/* 162 */     return getNomeFonte();
/*     */   }
/*     */   
/*     */   public boolean isParcIsentaAposentadoria() {
/* 166 */     return this.parcIsentaAposentadoria;
/*     */   }
/*     */   
/*     */   public void setParcIsentaAposentadoria(boolean parcIsentaAposentadoria) {
/* 170 */     this.parcIsentaAposentadoria = parcIsentaAposentadoria;
/*     */   }
/*     */   
/*     */   public CPF getCpfAlimentante() {
/* 174 */     return this.cpfAlimentante;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendIsentos\ItemQuadroTransporteDetalhado.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */