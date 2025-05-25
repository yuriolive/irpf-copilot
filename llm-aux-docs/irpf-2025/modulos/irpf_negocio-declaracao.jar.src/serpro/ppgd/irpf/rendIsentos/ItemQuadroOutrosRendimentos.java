/*     */ package serpro.ppgd.irpf.rendIsentos;
/*     */ 
/*     */ import java.lang.ref.WeakReference;
/*     */ import serpro.ppgd.cacheni.CacheNI;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.ObservadorEspacosDuplicados;
/*     */ import serpro.ppgd.irpf.ValidadorNaoNuloIRPF;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.NI;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ import serpro.ppgd.negocio.ValidadorIf;
/*     */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorNI;
/*     */ 
/*     */ public class ItemQuadroOutrosRendimentos
/*     */   extends ItemQuadroAuxiliar {
/*  19 */   private NI cnpjEmpresa = new NI(this, "CPF/CNPJ da fonte pagadora");
/*  20 */   private Alfa nomeFonte = new Alfa(this, "Nome da fonte pagadora", 60);
/*  21 */   private Alfa descricaoRendimento = new Alfa(this, "Descrição do rendimento", 60);
/*     */ 
/*     */   
/*     */   public ItemQuadroOutrosRendimentos(DeclaracaoIRPF dec) {
/*  25 */     super(dec);
/*     */     
/*  27 */     CacheNI.getInstancia().registrarNINome(this.cnpjEmpresa, this.nomeFonte);
/*     */     
/*  29 */     getCnpjEmpresa().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)2)
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/*  32 */             if ("00000".equals(ItemQuadroOutrosRendimentos.this.getCodBem().naoFormatado()) || ItemQuadroOutrosRendimentos.this.getCodBem().isVazio()) {
/*  33 */               return super.validarImplementado();
/*     */             }
/*  35 */             return null;
/*     */           }
/*     */         });
/*     */     
/*  39 */     getCnpjEmpresa().addValidador((ValidadorIf)new ValidadorNI((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/*  42 */             if ("00000".equals(ItemQuadroOutrosRendimentos.this.getCodBem().naoFormatado()) || ItemQuadroOutrosRendimentos.this.getCodBem().isVazio()) {
/*  43 */               setMensagemValidacao(MensagemUtil.getMensagem("campo_invalido", new String[] { getInformacao().getNomeCampo() }));
/*  44 */               return super.validarImplementado();
/*     */             } 
/*  46 */             return null;
/*     */           }
/*     */         });
/*     */ 
/*     */     
/*  51 */     getNomeFonte().addObservador((Observador)new ObservadorEspacosDuplicados());
/*  52 */     getNomeFonte().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)2));
/*     */     
/*  54 */     getDescricaoRendimento().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3));
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemQuadroOutrosRendimentos(DeclaracaoIRPF dec, ObjetoNegocio parent) {
/*  59 */     this(dec);
/*  60 */     this.parent = new WeakReference<>(parent);
/*     */   }
/*     */   
/*     */   public Alfa getNomeFonte() {
/*  64 */     return this.nomeFonte;
/*     */   }
/*     */   
/*     */   public NI getCnpjEmpresa() {
/*  68 */     return this.cnpjEmpresa;
/*     */   }
/*     */   
/*     */   public Alfa getDescricaoRendimento() {
/*  72 */     return this.descricaoRendimento;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void adicionaCamposParaPendencia() {
/*  81 */     super.adicionaCamposParaPendencia();
/*  82 */     this.camposPendencia.add(this.cnpjEmpresa);
/*  83 */     this.camposPendencia.add(this.nomeFonte);
/*  84 */     this.camposPendencia.add(this.descricaoRendimento);
/*     */   }
/*     */ 
/*     */   
/*     */   public NI getNIFontePagadora() {
/*  89 */     return getCnpjEmpresa();
/*     */   }
/*     */ 
/*     */   
/*     */   public Alfa getNomeFontePagadora() {
/*  94 */     return getNomeFonte();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 100 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendIsentos\ItemQuadroOutrosRendimentos.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */