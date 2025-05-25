/*     */ package serpro.ppgd.irpf.bens;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import serpro.ppgd.cacheni.CacheNI;
/*     */ import serpro.ppgd.irpf.ValidadorNaoNuloIRPF;
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.NI;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ import serpro.ppgd.negocio.ValidadorIf;
/*     */ import serpro.ppgd.negocio.ValidadorImpeditivoDefault;
/*     */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorNI;
/*     */ 
/*     */ public class ItemPercentualParticipacaoInventario
/*     */   extends ObjetoNegocio {
/*  20 */   private String NOME_CAMPO_NI = "NI do participante no inventário";
/*  21 */   private String NOME_CAMPO_NOME = "Nome do participante no inventário";
/*  22 */   private String NOME_CAMPO_PERCENTUAL = "Percentual de participação no inventário";
/*     */   
/*  24 */   private NI ni = new NI(this, this.NOME_CAMPO_NI);
/*  25 */   private Alfa nome = new Alfa(this, this.NOME_CAMPO_NOME, 60);
/*  26 */   private ValorPositivo percentual = new ValorPositivo(this, this.NOME_CAMPO_PERCENTUAL, 3, 2);
/*     */ 
/*     */   
/*     */   public ItemPercentualParticipacaoInventario() {
/*  30 */     CacheNI.getInstancia().registrarNINome(this.ni, this.nome);
/*     */     
/*  32 */     this.ni.addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, MensagemUtil.getMensagem("cpf_cnpj_branco")));
/*  33 */     this.ni.addValidador((ValidadorIf)new ValidadorNI((byte)3, MensagemUtil.getMensagem("cpf_cnpj_invalido")));
/*     */     
/*  35 */     this.percentual.addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, MensagemUtil.getMensagem("bem_percentual_branco")));
/*     */     
/*  37 */     this.percentual.addValidador((ValidadorIf)new ValidadorImpeditivoDefault(MensagemUtil.getMensagem("bem_percentual_invalido"))
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/*  42 */             setSeveridade((byte)5);
/*  43 */             if (getProximoConteudo() != null && !getProximoConteudo().toString().trim().isEmpty() && Double.valueOf(getProximoConteudo().toString().replace(',', '.')).compareTo(Double.valueOf(100.0D)) > 0) {
/*  44 */               return new RetornoValidacao(getMensagemValidacao(), getSeveridade());
/*     */             }
/*     */             
/*  47 */             return null;
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*     */           public void acaoOk() {}
/*     */ 
/*     */ 
/*     */           
/*     */           public void acaoCancelar() {}
/*     */ 
/*     */           
/*     */           public String getTituloPopup() {
/*  60 */             return "Informação";
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/*  68 */     List<Informacao> lista = super.recuperarListaCamposPendencia();
/*  69 */     lista.add(this.ni);
/*  70 */     lista.add(this.percentual);
/*     */     
/*  72 */     return lista;
/*     */   }
/*     */   
/*     */   public NI getNi() {
/*  76 */     return this.ni;
/*     */   }
/*     */   
/*     */   public Alfa getNome() {
/*  80 */     return this.nome;
/*     */   }
/*     */   
/*     */   public ValorPositivo getPercentual() {
/*  84 */     return this.percentual;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isVazio() {
/*  90 */     Iterator<Informacao> iterator = recuperarCamposInformacao().iterator();
/*     */     
/*  92 */     while (iterator.hasNext()) {
/*  93 */       Informacao informacao = iterator.next();
/*  94 */       if (!informacao.isVazio() && (informacao
/*  95 */         .getNomeCampo().equals(this.NOME_CAMPO_NI) || informacao.getNomeCampo().equals(this.NOME_CAMPO_PERCENTUAL))) {
/*  96 */         return false;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 101 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\bens\ItemPercentualParticipacaoInventario.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */