/*     */ package serpro.ppgd.irpf.resumo;
/*     */ 
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ public class RendimentosTributaveisDeducoes
/*     */   extends ObjetoNegocio {
/*   8 */   private Valor rendRecebidoPJTitular = new Valor(this, "Rendimentos Tributáveis Recebidos de PJ pelo Titular");
/*   9 */   private Valor rendRecebidoPJDependentes = new Valor(this, "Rendimentos Tributáveis Recebidos de PJ pelos Dependentes");
/*     */ 
/*     */   
/*  12 */   private Valor rendRecebidoPFEXTTitular = new Valor(this, "Rendimentos Tributáveis Recebidos de PF/Exterior pelo Titular");
/*  13 */   private Valor rendRecebidoPFEXTDependentes = new Valor(this, "Rendimentos Tributáveis Recebidos de PF/Exterior pelos Dependentes");
/*  14 */   private Valor rendRecebidoAcmTitular = new Valor(this, "Rendimentos Recebidos Acumuladamente pelo Titular");
/*  15 */   private Valor rendRecebidoAcmDependentes = new Valor(this, "Rendimentos Recebidos Acumuladamente pelos Dependentes");
/*     */ 
/*     */   
/*  18 */   private Valor rendTributavelAR = new Valor(this, "Resultado Tributável da Atividade Rural");
/*  19 */   private Valor totalRendimentos = new Valor(this, "Total Rendimentos");
/*     */   
/*  21 */   private Valor previdenciaOficial = new Valor(this, "Contribuição Previdência Oficial");
/*  22 */   private Valor previdenciaOficialRRA = new Valor(this, "Contribuição Previdência Oficial - RRA");
/*  23 */   private Valor previdencia = new Valor(this, "Contribuição Previdência Complementar");
/*  24 */   private Valor dependentes = new Valor(this, "Dependentes");
/*  25 */   private Valor despesasInstrucao = new Valor(this, "Despesas com Instrução");
/*  26 */   private Valor despesasMedicas = new Valor(this, "Despesas Médicas");
/*  27 */   private Valor pensaoAlimenticia = new Valor(this, "Pensão Alimentícia Judicial");
/*  28 */   private Valor pensaoAlimenticiaRRA = new Valor(this, "Pensão Alimentícia Judicial - RRA");
/*  29 */   private Valor pensaoCartoral = new Valor(this, "Pensão Alimentícia Escritura Pública");
/*  30 */   private Valor livroCaixa = new Valor(this, "Livro Caixa");
/*  31 */   private Valor totalDeducoes = new Valor(this, "Total Deduções");
/*     */ 
/*     */ 
/*     */   
/*     */   public RendimentosTributaveisDeducoes() {
/*  36 */     this.rendRecebidoPJTitular.setReadOnly(true);
/*  37 */     this.rendRecebidoPJDependentes.setReadOnly(true);
/*  38 */     this.rendRecebidoPFEXTTitular.setReadOnly(true);
/*  39 */     this.rendRecebidoPFEXTDependentes.setReadOnly(true);
/*  40 */     this.rendRecebidoAcmTitular.setReadOnly(true);
/*  41 */     this.rendRecebidoAcmDependentes.setReadOnly(true);
/*     */     
/*  43 */     this.rendTributavelAR.setReadOnly(true);
/*  44 */     this.totalRendimentos.setReadOnly(true);
/*     */     
/*  46 */     this.previdenciaOficial.setReadOnly(true);
/*  47 */     this.previdenciaOficialRRA.setReadOnly(true);
/*  48 */     this.previdencia.setReadOnly(true);
/*  49 */     this.dependentes.setReadOnly(true);
/*  50 */     this.despesasInstrucao.setReadOnly(true);
/*  51 */     this.despesasMedicas.setReadOnly(true);
/*  52 */     this.pensaoAlimenticia.setReadOnly(true);
/*  53 */     this.pensaoAlimenticiaRRA.setReadOnly(true);
/*  54 */     this.pensaoCartoral.setReadOnly(true);
/*  55 */     this.livroCaixa.setReadOnly(true);
/*  56 */     this.totalDeducoes.setReadOnly(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public Valor getDependentes() {
/*  61 */     return this.dependentes;
/*     */   }
/*     */   
/*     */   public Valor getDespesasInstrucao() {
/*  65 */     return this.despesasInstrucao;
/*     */   }
/*     */   
/*     */   public Valor getDespesasMedicas() {
/*  69 */     return this.despesasMedicas;
/*     */   }
/*     */   
/*     */   public Valor getLivroCaixa() {
/*  73 */     return this.livroCaixa;
/*     */   }
/*     */   
/*     */   public Valor getPensaoAlimenticia() {
/*  77 */     return this.pensaoAlimenticia;
/*     */   }
/*     */   
/*     */   public Valor getPensaoCartoral() {
/*  81 */     return this.pensaoCartoral;
/*     */   }
/*     */   
/*     */   public Valor getPrevidencia() {
/*  85 */     return this.previdencia;
/*     */   }
/*     */   
/*     */   public Valor getPrevidenciaOficial() {
/*  89 */     return this.previdenciaOficial;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getRendRecebidoPFEXTDependentes() {
/*  97 */     return this.rendRecebidoPFEXTDependentes;
/*     */   }
/*     */   
/*     */   public Valor getRendRecebidoPFEXTTitular() {
/* 101 */     return this.rendRecebidoPFEXTTitular;
/*     */   }
/*     */   
/*     */   public Valor getRendRecebidoPJDependentes() {
/* 105 */     return this.rendRecebidoPJDependentes;
/*     */   }
/*     */   
/*     */   public Valor getRendRecebidoPJTitular() {
/* 109 */     return this.rendRecebidoPJTitular;
/*     */   }
/*     */   
/*     */   public Valor getRendTributavelAR() {
/* 113 */     return this.rendTributavelAR;
/*     */   }
/*     */   
/*     */   public Valor getTotalDeducoes() {
/* 117 */     return this.totalDeducoes;
/*     */   }
/*     */   
/*     */   public Valor getTotalRendimentos() {
/* 121 */     return this.totalRendimentos;
/*     */   }
/*     */   
/*     */   public Valor getRendRecebidoAcmTitular() {
/* 125 */     return this.rendRecebidoAcmTitular;
/*     */   }
/*     */   
/*     */   public Valor getRendRecebidoAcmDependentes() {
/* 129 */     return this.rendRecebidoAcmDependentes;
/*     */   }
/*     */   
/*     */   public Valor getPrevidenciaOficialRRA() {
/* 133 */     return this.previdenciaOficialRRA;
/*     */   }
/*     */   
/*     */   public Valor getPensaoAlimenticiaRRA() {
/* 137 */     return this.pensaoAlimenticiaRRA;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\resumo\RendimentosTributaveisDeducoes.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */