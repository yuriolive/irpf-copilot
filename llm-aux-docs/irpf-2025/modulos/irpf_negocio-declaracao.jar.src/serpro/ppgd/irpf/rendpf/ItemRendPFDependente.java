/*     */ package serpro.ppgd.irpf.rendpf;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.gui.rendpf.PainelDadosEscrituracao;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.CPF;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ import serpro.ppgd.negocio.ValidadorDefault;
/*     */ import serpro.ppgd.negocio.ValidadorIf;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorCPF;
/*     */ 
/*     */ public class ItemRendPFDependente extends ObjetoNegocio implements ObjetoFicha {
/*  18 */   private RendPF rendimentos = new RendPF();
/*  19 */   private CPF cpf = new CPF(this, "CPF dependente");
/*  20 */   private Valor fooValoresPreenchidos = new Valor(this, "");
/*  21 */   private WeakReference<DeclaracaoIRPF> declaracaoRef = null;
/*     */   
/*     */   public void addValidador(DeclaracaoIRPF dec) {
/*  24 */     this.declaracaoRef = new WeakReference<>(dec);
/*     */     
/*  26 */     getCpf().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3));
/*     */     
/*  28 */     ValidadorCPF validadorCPF = new ValidadorCPF((byte)3);
/*  29 */     validadorCPF.setMensagemValidacao(MensagemUtil.getMensagem("rendpjdep_cpf_invalido"));
/*  30 */     getCpf().addValidador((ValidadorIf)validadorCPF);
/*     */     
/*  32 */     getCpf().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/*  35 */             DeclaracaoIRPF declaracaoIRPF = ItemRendPFDependente.this.declaracaoRef.get();
/*  36 */             if (!declaracaoIRPF.getDependentes().isExisteCpf(ItemRendPFDependente.this.getCpf().naoFormatado())) {
/*  37 */               return new RetornoValidacao(MensagemUtil.getMensagem("rendpjdep_cpf_nao_existe"), (byte)3);
/*     */             }
/*  39 */             return null;
/*     */           }
/*     */         });
/*     */ 
/*     */     
/*  44 */     getCpf().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/*  47 */             DeclaracaoIRPF declaracaoIRPF = ItemRendPFDependente.this.declaracaoRef.get();
/*     */             
/*  49 */             int qtdCpfs = 0;
/*  50 */             Iterator<ItemRendPFDependente> it = declaracaoIRPF.getRendPFDependente().itens().iterator();
/*  51 */             while (it.hasNext()) {
/*  52 */               ItemRendPFDependente item = it.next();
/*     */               
/*  54 */               if (item.getCpf().naoFormatado().equals(ItemRendPFDependente.this.getCpf().naoFormatado())) {
/*  55 */                 qtdCpfs++;
/*     */               }
/*     */             } 
/*     */             
/*  59 */             if (qtdCpfs > 1) {
/*  60 */               return new RetornoValidacao(MensagemUtil.getMensagem("rendpf_dependente_duplicado"), (byte)3);
/*     */             }
/*  62 */             return null;
/*     */           }
/*     */         });
/*     */ 
/*     */     
/*  67 */     this.fooValoresPreenchidos.addValidador((ValidadorIf)new ValidadorValoresNaoPreenchidos((byte)3, this));
/*     */     
/*  69 */     getRendimentos().getNITPISPASEP().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/*  72 */             Alfa nitTitular = ((DeclaracaoIRPF)ItemRendPFDependente.this.declaracaoRef.get()).getRendPFTitular().getNITPISPASEP();
/*  73 */             if (!nitTitular.isVazio() && ItemRendPFDependente.this.getRendimentos().getNITPISPASEP().naoFormatado().equals(nitTitular.naoFormatado())) {
/*  74 */               return new RetornoValidacao(MensagemUtil.getMensagem("nit_dependente_igual_titular"), (byte)3);
/*     */             }
/*  76 */             return null;
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public CPF getCpf() {
/*  82 */     return this.cpf;
/*     */   }
/*     */ 
/*     */   
/*     */   public void aplicaNomeFicha() {
/*  87 */     setFicha("Rendimentos Tributáveis Recebidos de PF e do Exterior - Dependentes");
/*  88 */     getRendimentos().setFicha("Rendimentos Tributáveis Recebidos de PF e do Exterior - Dependentes");
/*  89 */     for (int i = 0; i < 12; i++) {
/*  90 */       getRendimentos().getMesRendPFPorIndice(i).setFicha("Rendimentos Tributáveis Recebidos de PF e do Exterior - Dependentes");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/*  96 */     List<Informacao> lista = super.recuperarListaCamposPendencia();
/*     */     
/*  98 */     lista.add(getCpf());
/*  99 */     lista.add(this.fooValoresPreenchidos);
/* 100 */     lista.add(getRendimentos().getNITPISPASEP());
/*     */     
/* 102 */     for (int i = 0; i < 12; i++) {
/* 103 */       lista.add(getRendimentos().getMesRendPFPorIndice(i).getCarneLeao());
/* 104 */       lista.add(getRendimentos().getMesRendPFPorIndice(i).getDarfPago());
/* 105 */       lista.add(getRendimentos().getMesRendPFPorIndice(i).getDependentes());
/* 106 */       lista.add(getRendimentos().getMesRendPFPorIndice(i).getExterior());
/* 107 */       lista.add(getRendimentos().getMesRendPFPorIndice(i).getLivroCaixa());
/* 108 */       lista.add(getRendimentos().getMesRendPFPorIndice(i).getPensao());
/* 109 */       lista.add(getRendimentos().getMesRendPFPorIndice(i).getPessoaFisica());
/* 110 */       lista.add(getRendimentos().getMesRendPFPorIndice(i).getAlugueis());
/* 111 */       lista.add(getRendimentos().getMesRendPFPorIndice(i).getOutros());
/* 112 */       lista.add(getRendimentos().getMesRendPFPorIndice(i).getImpostoPagoCompensarExterior());
/* 113 */       lista.add(getRendimentos().getMesRendPFPorIndice(i).getPrevidencia());
/*     */     } 
/*     */     
/* 116 */     return lista;
/*     */   }
/*     */   
/*     */   public RendPF getRendimentos() {
/* 120 */     return this.rendimentos;
/*     */   }
/*     */   
/*     */   public void setRendimentos(RendPF rendimentos) {
/* 124 */     this.rendimentos = rendimentos;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClasseFicha() {
/* 129 */     return PainelDadosEscrituracao.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 134 */     return "Outras Informações";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 139 */     return "Rendimentos Tributáveis Recebidos de PF e do Exterior - Dependentes";
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendpf\ItemRendPFDependente.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */