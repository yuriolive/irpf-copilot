/*     */ package serpro.ppgd.irpf.atividaderural;
/*     */ 
/*     */ import java.util.List;
/*     */ import serpro.ppgd.irpf.ValidadorNaoNuloIRPF;
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.irpf.gui.atividaderural.PainelBensARLista;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.irpf.util.ObjetoComChaveIRPF;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.Codigo;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.ObjetoFicha;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ import serpro.ppgd.negocio.ValidadorIf;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorCodigo;
/*     */ 
/*     */ public class BemAR
/*     */   extends ObjetoNegocio
/*     */   implements ObjetoComChaveIRPF, ObjetoFicha {
/*  24 */   public static String LABEL_VALOR_EXERCICIO_ANTERIOR = "Situação Valor Anterior";
/*  25 */   public static String LABEL_VALOR_EXERCICIO_ATUAL = "Situação Valor Atual";
/*     */   
/*  27 */   protected Codigo codigo = new Codigo(this, "Código", CadastroTabelasIRPF.recuperarTipoBensAR());
/*  28 */   protected Codigo pais = new Codigo(this, "País", CadastroTabelasIRPF.recuperarPaises());
/*  29 */   protected Alfa nomePais = new Alfa(this, "Nome do País");
/*  30 */   protected Alfa discriminacao = new Alfa(this, "Discriminação", 512);
/*  31 */   protected ValorPositivo valorExercicioAnterior = new ValorPositivo(this, LABEL_VALOR_EXERCICIO_ANTERIOR);
/*  32 */   protected ValorPositivo valorExercicioAtual = new ValorPositivo(this, LABEL_VALOR_EXERCICIO_ATUAL);
/*     */   
/*  34 */   private Alfa indice = new Alfa(this, "Indice");
/*     */ 
/*     */   
/*     */   public BemAR() {
/*  38 */     getCodigo().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, MensagemUtil.getMensagem("ar_bem_codigo_branco"))
/*     */         {
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/*  42 */             if (BemAR.this.isVazio()) {
/*  43 */               return null;
/*     */             }
/*  45 */             return super.validarImplementado();
/*     */           }
/*     */         });
/*     */     
/*  49 */     getCodigo().addValidador((ValidadorIf)new ValidadorCodigo((byte)3, MensagemUtil.getMensagem("ar_bem_codigo_branco")));
/*     */     
/*  51 */     getDiscriminacao().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, MensagemUtil.getMensagem("ar_bem_discriminacao"))
/*     */         {
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/*  55 */             if (BemAR.this.getCodigo().isVazio()) {
/*  56 */               return null;
/*     */             }
/*  58 */             return super.validarImplementado();
/*     */           }
/*     */         });
/*     */     
/*  62 */     getPais().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, MensagemUtil.getMensagem("ar_bem_pais_branco"))
/*     */         {
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/*  66 */             if (BemAR.this.isVazio()) {
/*  67 */               return null;
/*     */             }
/*  69 */             return super.validarImplementado();
/*     */           }
/*     */         });
/*     */     
/*  73 */     getPais().addValidador((ValidadorIf)new ValidadorCodigo((byte)3, MensagemUtil.getMensagem("ar_bem_pais_branco")));
/*     */     
/*  75 */     getPais().addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/*  79 */             BemAR.this.nomePais.setConteudo(BemAR.this.getPais().getConteudoAtual(1));
/*     */           }
/*     */         });
/*     */ 
/*     */     
/*  84 */     if (getPais().isVazio()) {
/*  85 */       getPais().setConteudo("105");
/*     */     }
/*     */   }
/*     */   
/*     */   public Codigo getCodigo() {
/*  90 */     return this.codigo;
/*     */   }
/*     */   
/*     */   public Alfa getDiscriminacao() {
/*  94 */     return this.discriminacao;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorExercicioAnterior() {
/*  98 */     return this.valorExercicioAnterior;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorExercicioAtual() {
/* 102 */     return this.valorExercicioAtual;
/*     */   }
/*     */ 
/*     */   
/*     */   public Codigo getPais() {
/* 107 */     return this.pais;
/*     */   }
/*     */   
/*     */   public Alfa getNomePais() {
/* 111 */     return this.nomePais;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVazio() {
/* 116 */     return (this.codigo.isVazio() && this.discriminacao.isVazio() && this.valorExercicioAnterior.isVazio() && this.valorExercicioAtual.isVazio());
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 121 */     List<Informacao> retorno = recuperarCamposInformacao();
/* 122 */     return retorno;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getChave() {
/* 127 */     return getCodigo().naoFormatado();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int compareTo(Object o) {
/* 133 */     BemAR bem = (BemAR)o;
/*     */     
/* 135 */     int cod = bem.getCodigo().asInteger();
/* 136 */     return cod - getCodigo().asInteger();
/*     */   }
/*     */ 
/*     */   
/*     */   public Alfa getIndice() {
/* 141 */     return this.indice;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClasseFicha() {
/* 146 */     return PainelBensARLista.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 151 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 156 */     return "Bens da Atividade Rural";
/*     */   }
/*     */   
/*     */   public BemAR obterCopia() {
/* 160 */     BemAR copia = new BemAR();
/* 161 */     copia.getCodigo().setConteudo(getCodigo());
/* 162 */     copia.getPais().setConteudo(getPais());
/* 163 */     copia.getNomePais().setConteudo(getNomePais());
/* 164 */     copia.getDiscriminacao().setConteudo(getDiscriminacao());
/* 165 */     copia.getValorExercicioAnterior().setConteudo((Valor)getValorExercicioAnterior());
/* 166 */     copia.getValorExercicioAtual().setConteudo((Valor)getValorExercicioAtual());
/* 167 */     return copia;
/*     */   }
/*     */   
/*     */   public void addObservador(Observador obj) {
/* 171 */     this.valorExercicioAnterior.addObservador(obj);
/* 172 */     this.valorExercicioAtual.addObservador(obj);
/*     */   }
/*     */   
/*     */   public void removeObservador(Observador obj) {
/* 176 */     this.valorExercicioAnterior.removeObservador(obj);
/* 177 */     this.valorExercicioAtual.removeObservador(obj);
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\atividaderural\BemAR.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */