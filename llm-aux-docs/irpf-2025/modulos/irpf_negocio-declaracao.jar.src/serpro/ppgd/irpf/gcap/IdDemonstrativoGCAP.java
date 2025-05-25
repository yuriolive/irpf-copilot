/*     */ package serpro.ppgd.irpf.gcap;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import serpro.ppgd.irpf.gui.gcap.bensimoveis.PainelListaBemImovel;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.CPF;
/*     */ import serpro.ppgd.negocio.Codigo;
/*     */ import serpro.ppgd.negocio.ConstantesGlobais;
/*     */ import serpro.ppgd.negocio.Data;
/*     */ import serpro.ppgd.negocio.DataHora;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.Logico;
/*     */ import serpro.ppgd.negocio.ObjetoFicha;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IdDemonstrativoGCAP
/*     */   extends ObjetoNegocio
/*     */   implements ObjetoGCAP, ObjetoFicha
/*     */ {
/*  25 */   private Alfa nome = new Alfa(this, "Nome", 60);
/*  26 */   private CPF cpf = new CPF(this, "CPF");
/*  27 */   private DataHora dataUltimoAcesso = new DataHora(this, "Data Último Acesso");
/*  28 */   private Alfa exercicio = new Alfa(this, "Exercício", 4);
/*  29 */   private Data dataInicioPermanencia = new Data(this, "Data Inicio Permanência");
/*  30 */   private Data dataFimPermanencia = new Data(this, "Data Fim Permanência");
/*  31 */   private Codigo paisDeclarante = new Codigo(this, "Pais Declarante", CadastroTabelasIRPF.recuperarPaises());
/*  32 */   private Alfa telefoneDeclarante = new Alfa(this, "Telefone Declarante", 10);
/*  33 */   private Alfa dddDeclarante = new Alfa(this, "Telefone Declarante", 2);
/*  34 */   private Logico territorioParaisoFiscal = new Logico(this, "Território Paraso Fiscal");
/*     */   
/*     */   public IdDemonstrativoGCAP() {
/*  37 */     getExercicio().setConteudo(ConstantesGlobais.EXERCICIO);
/*  38 */     getTerritorioParaisoFiscal().addOpcao(Logico.SIM, Logico.LABEL_SIM);
/*  39 */     getTerritorioParaisoFiscal().addOpcao(Logico.NAO, Logico.LABEL_NAO);
/*  40 */     setFicha("Ganhos de Capital");
/*     */   }
/*     */   
/*     */   public Alfa getNome() {
/*  44 */     return this.nome;
/*     */   }
/*     */   
/*     */   public CPF getCpf() {
/*  48 */     return this.cpf;
/*     */   }
/*     */   
/*     */   public Alfa getExercicio() {
/*  52 */     return this.exercicio;
/*     */   }
/*     */   
/*     */   public DataHora getDataUltimoAcesso() {
/*  56 */     return this.dataUltimoAcesso;
/*     */   }
/*     */   
/*     */   public Data getDataInicioPermanencia() {
/*  60 */     return this.dataInicioPermanencia;
/*     */   }
/*     */   
/*     */   public Data getDataFimPermanencia() {
/*  64 */     return this.dataFimPermanencia;
/*     */   }
/*     */   
/*     */   public Codigo getPaisDeclarante() {
/*  68 */     return this.paisDeclarante;
/*     */   }
/*     */   
/*     */   public Alfa getTelefoneDeclarante() {
/*  72 */     return this.telefoneDeclarante;
/*     */   }
/*     */   
/*     */   public Alfa getDddDeclarante() {
/*  76 */     return this.dddDeclarante;
/*     */   }
/*     */   
/*     */   public Logico getTerritorioParaisoFiscal() {
/*  80 */     return this.territorioParaisoFiscal;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  84 */     if (obj instanceof IdDemonstrativoGCAP && (
/*  85 */       (IdDemonstrativoGCAP)obj).getCpf().naoFormatado().equals(getCpf().naoFormatado())) {
/*  86 */       return true;
/*     */     }
/*     */     
/*  89 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  94 */     return Objects.hash(new Object[] { this.cpf.naoFormatado() });
/*     */   }
/*     */   
/*     */   public boolean paisTemTerritorioParaisoFiscal() {
/*  98 */     return (!this.paisDeclarante.isVazio() && !"".equals(this.paisDeclarante.getConteudoAtual(3).trim()));
/*     */   }
/*     */   
/*     */   public boolean isParaisoFiscal() {
/* 102 */     return getPaisDeclarante().getConteudoAtual(2).equals(Logico.SIM);
/*     */   }
/*     */   
/*     */   public boolean isTerritorioParaisoFiscal() {
/* 106 */     return getTerritorioParaisoFiscal().naoFormatado().equals(Logico.SIM);
/*     */   }
/*     */   
/*     */   public boolean isDemonstrativoBrasil() {
/* 110 */     return "105".equals(getPaisDeclarante().getConteudoAtual(0));
/*     */   }
/*     */   
/*     */   public boolean isDemonstrativoExterior() {
/* 114 */     return !"105".equals(getPaisDeclarante().getConteudoAtual(0));
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 119 */     List<Informacao> lista = super.recuperarListaCamposPendencia();
/* 120 */     lista.add(getCpf());
/* 121 */     return lista;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClasseFicha() {
/* 126 */     return PainelListaBemImovel.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 131 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 136 */     return "Ganho de Capital";
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\IdDemonstrativoGCAP.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */