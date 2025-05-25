/*    */ package serpro.ppgd.irpf.rendIsentos;
/*    */ 
/*    */ import serpro.ppgd.cacheni.CacheNI;
/*    */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*    */ import serpro.ppgd.irpf.ObservadorEspacosDuplicados;
/*    */ import serpro.ppgd.irpf.ValidadorNaoNuloIRPF;
/*    */ import serpro.ppgd.irpf.util.MensagemUtil;
/*    */ import serpro.ppgd.negocio.Alfa;
/*    */ import serpro.ppgd.negocio.NI;
/*    */ import serpro.ppgd.negocio.Observador;
/*    */ import serpro.ppgd.negocio.RetornoValidacao;
/*    */ import serpro.ppgd.negocio.ValidadorIf;
/*    */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorNI;
/*    */ 
/*    */ public class ItemQuadroRendimentosNI extends ItemQuadroAuxiliar {
/* 16 */   private NI cnpjEmpresa = new NI(this, "CPF/CNPJ da fonte pagadora");
/* 17 */   private Alfa nomeFonte = new Alfa(this, "Nome da fonte pagadora", 60);
/*    */ 
/*    */   
/*    */   public ItemQuadroRendimentosNI(DeclaracaoIRPF dec) {
/* 21 */     super(dec);
/*    */     
/* 23 */     CacheNI.getInstancia().registrarNINome(this.cnpjEmpresa, this.nomeFonte);
/*    */     
/* 25 */     getCnpjEmpresa().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3));
/* 26 */     getCnpjEmpresa().addValidador((ValidadorIf)new ValidadorNI((byte)3)
/*    */         {
/*    */           public RetornoValidacao validarImplementado()
/*    */           {
/* 30 */             setMensagemValidacao(MensagemUtil.getMensagem("campo_invalido", new String[] { getInformacao().getNomeCampo() }));
/*    */             
/* 32 */             return super.validarImplementado();
/*    */           }
/*    */         });
/*    */ 
/*    */     
/* 37 */     getNomeFonte().addObservador((Observador)new ObservadorEspacosDuplicados());
/* 38 */     getNomeFonte().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)2));
/*    */   }
/*    */ 
/*    */   
/*    */   public Alfa getNomeFonte() {
/* 43 */     return this.nomeFonte;
/*    */   }
/*    */   
/*    */   public NI getCnpjEmpresa() {
/* 47 */     return this.cnpjEmpresa;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void adicionaCamposParaPendencia() {
/* 56 */     super.adicionaCamposParaPendencia();
/* 57 */     this.camposPendencia.add(this.cnpjEmpresa);
/* 58 */     this.camposPendencia.add(this.nomeFonte);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public NI getNIFontePagadora() {
/* 64 */     return getCnpjEmpresa();
/*    */   }
/*    */ 
/*    */   
/*    */   public Alfa getNomeFontePagadora() {
/* 69 */     return getNomeFonte();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getTituloFichaDashboard() {
/* 75 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendIsentos\ItemQuadroRendimentosNI.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */