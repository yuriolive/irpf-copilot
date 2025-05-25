/*    */ package serpro.ppgd.irpf.rendIsentos;
/*    */ 
/*    */ import java.lang.ref.WeakReference;
/*    */ import serpro.ppgd.cacheni.CacheNI;
/*    */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*    */ import serpro.ppgd.irpf.ObservadorEspacosDuplicados;
/*    */ import serpro.ppgd.irpf.ValidadorCPFIRPF;
/*    */ import serpro.ppgd.irpf.ValidadorNaoNuloIRPF;
/*    */ import serpro.ppgd.irpf.util.MensagemUtil;
/*    */ import serpro.ppgd.negocio.Alfa;
/*    */ import serpro.ppgd.negocio.CPF;
/*    */ import serpro.ppgd.negocio.NI;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ import serpro.ppgd.negocio.Observador;
/*    */ import serpro.ppgd.negocio.RetornoValidacao;
/*    */ import serpro.ppgd.negocio.ValidadorDefault;
/*    */ import serpro.ppgd.negocio.ValidadorIf;
/*    */ 
/*    */ public class ItemQuadroPensaoAlimenticiaRendIsentos extends ItemQuadroAuxiliar {
/* 20 */   private CPF cpfAlimentante = new CPF(this, "CPF do Alimentante");
/* 21 */   private Alfa nomeAlimentante = new Alfa(this, "Nome do Alimentante", 60);
/*    */   
/*    */   private boolean parcIsentaAposentadoria = false;
/*    */   
/*    */   public ItemQuadroPensaoAlimenticiaRendIsentos(DeclaracaoIRPF dec) {
/* 26 */     super(dec);
/*    */     
/* 28 */     CacheNI.getInstancia().registrarNINome((NI)this.cpfAlimentante, this.nomeAlimentante);
/*    */     
/* 30 */     getCpfAlimentante().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3));
/* 31 */     getCpfAlimentante().addValidador((ValidadorIf)new ValidadorCPFIRPF((byte)3));
/*    */     
/* 33 */     getCpfAlimentante().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*    */         {
/*    */           public RetornoValidacao validarImplementado()
/*    */           {
/* 37 */             if (ItemQuadroPensaoAlimenticiaRendIsentos.this.getCpfBeneficiario().formatado().equals(getInformacao().formatado())) {
/* 38 */               return new RetornoValidacao(MensagemUtil.getMensagem("rendisentos_cpf_alimentante_igual_cpf_alimentando"), 
/* 39 */                   getSeveridade());
/*    */             }
/* 41 */             return null;
/*    */           }
/*    */         });
/*    */ 
/*    */     
/* 46 */     getNomeAlimentante().addObservador((Observador)new ObservadorEspacosDuplicados());
/* 47 */     getNomeAlimentante().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)2));
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemQuadroPensaoAlimenticiaRendIsentos(DeclaracaoIRPF dec, ObjetoNegocio parent) {
/* 52 */     this(dec);
/* 53 */     this.parent = new WeakReference<>(parent);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void adicionaCamposParaPendencia() {
/* 63 */     super.adicionaCamposParaPendencia();
/* 64 */     this.camposPendencia.add(this.cpfAlimentante);
/*    */   }
/*    */   
/*    */   public boolean isParcIsentaAposentadoria() {
/* 68 */     return this.parcIsentaAposentadoria;
/*    */   }
/*    */   
/*    */   public void setParcIsentaAposentadoria(boolean parcIsentaAposentadoria) {
/* 72 */     this.parcIsentaAposentadoria = parcIsentaAposentadoria;
/*    */   }
/*    */   
/*    */   public CPF getCpfAlimentante() {
/* 76 */     return this.cpfAlimentante;
/*    */   }
/*    */   
/*    */   public Alfa getNomeAlimentante() {
/* 80 */     return this.nomeAlimentante;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendIsentos\ItemQuadroPensaoAlimenticiaRendIsentos.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */