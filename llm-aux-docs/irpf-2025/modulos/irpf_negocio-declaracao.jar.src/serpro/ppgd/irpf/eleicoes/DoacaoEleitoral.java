/*     */ package serpro.ppgd.irpf.eleicoes;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import serpro.ppgd.cacheni.CacheNI;
/*     */ import serpro.ppgd.gui.xbeans.JEditObjetoNegocioItemIf;
/*     */ import serpro.ppgd.irpf.ObservadorEspacosDuplicados;
/*     */ import serpro.ppgd.irpf.ValidadorNaoNuloIRPF;
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.irpf.gui.eleicoes.PainelDoacoesCampanhasLista;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.CNPJ;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.NI;
/*     */ import serpro.ppgd.negocio.ObjetoFicha;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ import serpro.ppgd.negocio.ValidadorIf;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorCNPJ;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DoacaoEleitoral
/*     */   extends ObjetoNegocio
/*     */   implements JEditObjetoNegocioItemIf, ObjetoFicha
/*     */ {
/*     */   public static final String PROP_CAMPO_VALOR = "Valor da doação";
/*  32 */   private String chave = "";
/*  33 */   private Alfa nome = new Alfa(this, "Nome do candidato, partido político ou comitê financeiro", 60);
/*  34 */   private CNPJ cnpj = new CNPJ(this, "CNPJ");
/*  35 */   private ValorPositivo valor = new ValorPositivo(this, "Valor da doação");
/*     */ 
/*     */   
/*     */   public DoacaoEleitoral() {
/*  39 */     CacheNI.getInstancia().registrarNINome((NI)this.cnpj, this.nome);
/*     */     
/*  41 */     getNome().addObservador((Observador)new ObservadorEspacosDuplicados());
/*     */     
/*  43 */     ValidadorNaoNuloIRPF vNaoNulo = new ValidadorNaoNuloIRPF((byte)3, MensagemUtil.getMensagem("eleicoes_nome_branco"));
/*  44 */     vNaoNulo.setMensagemValidacao(MensagemUtil.getMensagem("eleicoes_nome_branco"));
/*  45 */     getNome().addValidador((ValidadorIf)vNaoNulo);
/*     */     
/*  47 */     ValidadorNaoNuloIRPF vCNPJnn = new ValidadorNaoNuloIRPF((byte)3);
/*  48 */     vCNPJnn.setMensagemValidacao(MensagemUtil.getMensagem("eleicoes_cnpj_branco"));
/*  49 */     getCNPJ().addValidador((ValidadorIf)vCNPJnn);
/*     */     
/*  51 */     ValidadorCNPJ vCNPJ = new ValidadorCNPJ((byte)3);
/*  52 */     vCNPJ.setMensagemValidacao(MensagemUtil.getMensagem("eleicoes_cnpj_invalido"));
/*  53 */     getCNPJ().addValidador((ValidadorIf)vCNPJ);
/*     */     
/*  55 */     getValor().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, MensagemUtil.getMensagem("eleicoes_valor_branco")));
/*     */   }
/*     */   
/*     */   public Alfa getNome() {
/*  59 */     return this.nome;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  65 */     return this.nome.naoFormatado();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getConteudo(int i) {
/*  70 */     return getNome().formatado();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTotalAtributos() {
/*  77 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getColunaFiltro() {
/*  82 */     return 0;
/*     */   }
/*     */   
/*     */   public String getChave() {
/*  86 */     return this.chave;
/*     */   }
/*     */   
/*     */   public void setChave(String chave) {
/*  90 */     this.chave = chave;
/*     */   }
/*     */   
/*     */   public CNPJ getCNPJ() {
/*  94 */     return this.cnpj;
/*     */   }
/*     */   
/*     */   public void setCnpj(CNPJ cnpj) {
/*  98 */     this.cnpj = cnpj;
/*     */   }
/*     */   
/*     */   public Valor getValor() {
/* 102 */     return (Valor)this.valor;
/*     */   }
/*     */   
/*     */   public void setValor(ValorPositivo valor) {
/* 106 */     this.valor = valor;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isVazio() {
/* 112 */     Iterator<Informacao> iterator = recuperarCamposInformacao().iterator();
/*     */     
/* 114 */     while (iterator.hasNext()) {
/* 115 */       Informacao informacao = iterator.next();
/* 116 */       if (!informacao.isVazio()) {
/* 117 */         return false;
/*     */       }
/*     */     } 
/* 120 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 126 */     List<Informacao> retorno = super.recuperarListaCamposPendencia();
/*     */     
/* 128 */     retorno.add(getNome());
/* 129 */     retorno.add(getCNPJ());
/* 130 */     retorno.add(getValor());
/*     */     
/* 132 */     return retorno;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getClasseFicha() {
/* 138 */     return PainelDoacoesCampanhasLista.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 143 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 148 */     return "Doações a Partidos Políticos e Candidatos a Cargos Eletivos";
/*     */   }
/*     */   
/*     */   public DoacaoEleitoral obterCopia() {
/* 152 */     DoacaoEleitoral copia = new DoacaoEleitoral();
/* 153 */     copia.getNome().setConteudo(getNome());
/* 154 */     copia.getCNPJ().setConteudo(getCNPJ());
/* 155 */     copia.getValor().setConteudo(getValor());
/* 156 */     return copia;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\eleicoes\DoacaoEleitoral.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */