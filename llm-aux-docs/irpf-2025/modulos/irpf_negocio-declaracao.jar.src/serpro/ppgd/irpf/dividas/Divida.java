/*     */ package serpro.ppgd.irpf.dividas;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import serpro.ppgd.irpf.ValidadorNaoNuloIRPF;
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.irpf.gui.dividas.PainelDividasLista;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.irpf.util.ObjetoComChaveIRPF;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.Codigo;
/*     */ import serpro.ppgd.negocio.ConstantesGlobais;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.ObjetoFicha;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Pendencia;
/*     */ import serpro.ppgd.negocio.ValidadorIf;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorCodigo;
/*     */ 
/*     */ 
/*     */ public class Divida
/*     */   extends ObjetoNegocio
/*     */   implements ObjetoComChaveIRPF, ObjetoFicha
/*     */ {
/*  27 */   public static final String NOME_CAMPO_VALOR_ANTERIOR = "Situação em 31/12/" + ConstantesGlobais.EXERCICIO_ANTERIOR;
/*  28 */   public static final String NOME_CAMPO_VALOR_ATUAL = "Situação em 31/12/" + ConstantesGlobais.EXERCICIO;
/*     */   
/*     */   public static final String NOME_CAMPO_VALOR_PGTO_ANUAL = "Valor de pagamento anual";
/*  31 */   private Codigo codigo = new Codigo(this, "Código", CadastroTabelasIRPF.recuperarTipoDividas());
/*  32 */   private Alfa discriminacao = new Alfa(this, "Discriminação", 120);
/*  33 */   private ValorPositivo valorExercicioAnterior = new ValorPositivo(this, "Situação no exercício passado");
/*  34 */   private ValorPositivo valorExercicioAtual = new ValorPositivo(this, "Situação no exercício atual");
/*  35 */   private ValorPositivo valorPgtoAnual = new ValorPositivo(this, "Valor de pagamento anual");
/*     */   
/*  37 */   private Alfa indice = new Alfa(this, "Indice");
/*     */   
/*     */   public Divida() {
/*  40 */     getCodigo().setColunaFiltro(1);
/*     */     
/*  42 */     getCodigo().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3));
/*  43 */     getCodigo().addValidador((ValidadorIf)new ValidadorCodigo((byte)3, MensagemUtil.getMensagem("divida_codigo_branco")));
/*  44 */     getDiscriminacao().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3));
/*     */   }
/*     */   
/*     */   public Codigo getCodigo() {
/*  48 */     return this.codigo;
/*     */   }
/*     */   
/*     */   public Alfa getDiscriminacao() {
/*  52 */     return this.discriminacao;
/*     */   }
/*     */   
/*     */   public Valor getValorExercicioAnterior() {
/*  56 */     return (Valor)this.valorExercicioAnterior;
/*     */   }
/*     */   
/*     */   public Valor getValorExercicioAtual() {
/*  60 */     return (Valor)this.valorExercicioAtual;
/*     */   }
/*     */   
/*     */   public Valor getValorPgtoAnual() {
/*  64 */     return (Valor)this.valorPgtoAnual;
/*     */   }
/*     */ 
/*     */   
/*     */   public Divida obterCopia() {
/*  69 */     Divida copia = new Divida();
/*  70 */     copia.getCodigo().setConteudo(getCodigo());
/*  71 */     copia.getDiscriminacao().setConteudo(getDiscriminacao());
/*  72 */     copia.getValorExercicioAnterior().setConteudo(getValorExercicioAnterior());
/*  73 */     copia.getValorExercicioAtual().setConteudo(getValorExercicioAtual());
/*  74 */     copia.getValorPgtoAnual().setConteudo(getValorPgtoAnual());
/*  75 */     copia.getIndice().setConteudo(getIndice());
/*     */     
/*  77 */     return copia;
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/*  82 */     List<Informacao> retorno = super.recuperarListaCamposPendencia();
/*     */     
/*  84 */     retorno.add(this.codigo);
/*  85 */     retorno.add(this.discriminacao);
/*  86 */     retorno.add(this.valorExercicioAtual);
/*  87 */     retorno.add(this.valorExercicioAnterior);
/*  88 */     retorno.add(this.valorPgtoAnual);
/*     */     
/*  90 */     return retorno;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isVazio() {
/*  96 */     Iterator<Informacao> iterator = recuperarCamposInformacao().iterator();
/*     */     
/*  98 */     while (iterator.hasNext()) {
/*  99 */       Informacao informacao = iterator.next();
/* 100 */       if (!informacao.isVazio() && !informacao.getNomeCampo().equals("Indice")) {
/* 101 */         return false;
/*     */       }
/*     */     } 
/* 104 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Pendencia> verificarPendencias(int numeroItem) {
/* 109 */     List<Pendencia> retorno = super.verificarPendencias(numeroItem);
/* 110 */     if (getValorExercicioAtual().isVazio() && getValorExercicioAnterior().isVazio()) {
/*     */       
/* 112 */       Pendencia p = new Pendencia((byte)2, (Informacao)getValorExercicioAnterior(), getValorExercicioAnterior().getNomeCampo(), MensagemUtil.getMensagem("divida_valor_nao_informado"), numeroItem);
/*     */       
/* 114 */       p.setClassePainel(getClasseFicha());
/*     */       
/* 116 */       retorno.add(p);
/*     */     } 
/* 118 */     return retorno;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getChave() {
/* 123 */     return getCodigo().naoFormatado();
/*     */   }
/*     */ 
/*     */   
/*     */   public int compareTo(Object o) {
/* 128 */     Divida divida = (Divida)o;
/* 129 */     int cod = divida.getCodigo().asInteger();
/*     */     
/* 131 */     return cod - getCodigo().asInteger();
/*     */   }
/*     */ 
/*     */   
/*     */   public Alfa getIndice() {
/* 136 */     return this.indice;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getClasseFicha() {
/* 142 */     return PainelDividasLista.class.getName();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 148 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 153 */     return "Dívidas e Ônus Reais";
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\dividas\Divida.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */