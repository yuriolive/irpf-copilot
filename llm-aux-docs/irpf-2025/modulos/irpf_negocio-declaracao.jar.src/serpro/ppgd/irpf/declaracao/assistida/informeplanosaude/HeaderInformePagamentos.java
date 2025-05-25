/*     */ package serpro.ppgd.irpf.declaracao.assistida.informeplanosaude;
/*     */ 
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.CPF;
/*     */ import serpro.ppgd.negocio.NI;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HeaderInformePagamentos
/*     */   extends ObjetoNegocio
/*     */ {
/*  18 */   private NI cnpjOperadoraPlanoSaude = new NI(this, "CNPJ da Operadora do Plano de Saúde");
/*  19 */   private Alfa nomeOperadoraPlanoSaude = new Alfa(this, "Nome da Operadora do Plano de Saúde", 60);
/*  20 */   private CPF cpfContribuinte = new CPF(this, "CPF do Contribuinte");
/*  21 */   private Alfa nomeContribuinte = new Alfa(this, "Nome do Contribuinte", 60);
/*  22 */   private Alfa anoExercicio = new Alfa(this, "Ano de Exercício", 4);
/*  23 */   private Alfa anoCalendario = new Alfa(this, "Ano Calendário", 4);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NI getCnpjOperadoraPlanoSaude() {
/*  32 */     return this.cnpjOperadoraPlanoSaude;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCnpjOperadoraPlanoSaude(NI cnpjOperadoraPlanoSaude) {
/*  39 */     this.cnpjOperadoraPlanoSaude = cnpjOperadoraPlanoSaude;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Alfa getNomeOperadoraPlanoSaude() {
/*  46 */     return this.nomeOperadoraPlanoSaude;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNomeOperadoraPlanoSaude(Alfa nomeOperadoraPlanoSaude) {
/*  53 */     this.nomeOperadoraPlanoSaude = nomeOperadoraPlanoSaude;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CPF getCpfContribuinte() {
/*  60 */     return this.cpfContribuinte;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCpfContribuinte(CPF cpfContribuinte) {
/*  67 */     this.cpfContribuinte = cpfContribuinte;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Alfa getNomeContribuinte() {
/*  74 */     return this.nomeContribuinte;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNomeContribuinte(Alfa nomeContribuinte) {
/*  81 */     this.nomeContribuinte = nomeContribuinte;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Alfa getAnoExercicio() {
/*  88 */     return this.anoExercicio;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAnoExercicio(Alfa anoExercicio) {
/*  95 */     this.anoExercicio = anoExercicio;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Alfa getAnoCalendario() {
/* 102 */     return this.anoCalendario;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAnoCalendario(Alfa anoCalendario) {
/* 109 */     this.anoCalendario = anoCalendario;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\declaracao\assistida\informeplanosaude\HeaderInformePagamentos.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */