/*     */ package serpro.ppgd.irpf;
/*     */ 
/*     */ import serpro.ppgd.negocio.ConstantesGlobais;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ import serpro.ppgd.negocio.ValidadorDefault;
/*     */ 
/*     */ public abstract class ValidadoresDominioAb {
/*     */   public abstract String getNomeProgramaAuxiliar();
/*     */   
/*     */   public abstract String getNomeFicha();
/*     */   
/*     */   public RetornoValidacao obterNovoRetornoValidacao(String nomeCampo, String nomeFicha, String nomeProgAux) {
/*  13 */     return new RetornoValidacao("Existem dados corrompidos em um dos arquivos importados para as fichas de " + nomeFicha + ". Para corrigir, utilize o programa " + nomeProgAux + " " + ConstantesGlobais.ANO_BASE + " e grave novamente o arquivo de exportação para o IRPF " + ConstantesGlobais.EXERCICIO + ". Erro: Valor inválido para o campo '" + nomeCampo + "'.");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValidadorDefault getValidadorCPF(byte severidade) {
/*  20 */     return new ValidadorDefault(severidade)
/*     */       {
/*     */         public RetornoValidacao validarImplementado()
/*     */         {
/*  24 */           RetornoValidacao ret = null;
/*  25 */           if (!getInformacao().isVazio() && !getInformacao().naoFormatado().matches("^(\\d){11}$")) {
/*  26 */             ret = ValidadoresDominioAb.this.obterNovoRetornoValidacao(getInformacao().getNomeCampo(), ValidadoresDominioAb.this.getNomeFicha(), ValidadoresDominioAb.this.getNomeProgramaAuxiliar());
/*     */           }
/*     */           
/*  29 */           return ret;
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public ValidadorDefault getValidadorCNPJ(byte severidade) {
/*  35 */     return new ValidadorDefault(severidade)
/*     */       {
/*     */         public RetornoValidacao validarImplementado()
/*     */         {
/*  39 */           RetornoValidacao ret = null;
/*  40 */           if (!getInformacao().isVazio() && !getInformacao().naoFormatado().matches("^(\\d){14}$")) {
/*  41 */             ret = ValidadoresDominioAb.this.obterNovoRetornoValidacao(getInformacao().getNomeCampo(), ValidadoresDominioAb.this.getNomeFicha(), ValidadoresDominioAb.this.getNomeProgramaAuxiliar());
/*     */           }
/*     */           
/*  44 */           return ret;
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public ValidadorDefault getValidadorNI(byte severidade) {
/*  50 */     return new ValidadorDefault(severidade)
/*     */       {
/*     */         public RetornoValidacao validarImplementado()
/*     */         {
/*  54 */           RetornoValidacao ret = null;
/*  55 */           if (!getInformacao().isVazio() && !getInformacao().naoFormatado().matches("^(\\d){11}|(\\d){14}$")) {
/*  56 */             ret = ValidadoresDominioAb.this.obterNovoRetornoValidacao(getInformacao().getNomeCampo(), ValidadoresDominioAb.this.getNomeFicha(), ValidadoresDominioAb.this.getNomeProgramaAuxiliar());
/*     */           }
/*     */           
/*  59 */           return ret;
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public ValidadorDefault getValidadorInteiro(byte severidade) {
/*  65 */     return new ValidadorDefault(severidade)
/*     */       {
/*     */         public RetornoValidacao validarImplementado()
/*     */         {
/*  69 */           RetornoValidacao ret = null;
/*  70 */           if (!getInformacao().isVazio() && !getInformacao().naoFormatado().matches("^(\\d)+$")) {
/*  71 */             ret = ValidadoresDominioAb.this.obterNovoRetornoValidacao(getInformacao().getNomeCampo(), ValidadoresDominioAb.this.getNomeFicha(), ValidadoresDominioAb.this.getNomeProgramaAuxiliar());
/*     */           }
/*     */           
/*  74 */           return ret;
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public ValidadorDefault getValidadorTelefone(byte severidade) {
/*  80 */     return new ValidadorDefault(severidade)
/*     */       {
/*     */         public RetornoValidacao validarImplementado()
/*     */         {
/*  84 */           RetornoValidacao ret = null;
/*  85 */           if (!getInformacao().isVazio() && !getInformacao().naoFormatado().matches("^(\\d){8,9}$")) {
/*  86 */             ret = ValidadoresDominioAb.this.obterNovoRetornoValidacao(getInformacao().getNomeCampo(), ValidadoresDominioAb.this.getNomeFicha(), ValidadoresDominioAb.this.getNomeProgramaAuxiliar());
/*     */           }
/*     */           
/*  89 */           return ret;
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public ValidadorDefault getValidadorCEP(byte severidade) {
/*  95 */     return new ValidadorDefault(severidade)
/*     */       {
/*     */         public RetornoValidacao validarImplementado()
/*     */         {
/*  99 */           RetornoValidacao ret = null;
/* 100 */           if (!getInformacao().isVazio() && !getInformacao().naoFormatado().matches("^(\\d){8}")) {
/* 101 */             ret = ValidadoresDominioAb.this.obterNovoRetornoValidacao(getInformacao().getNomeCampo(), ValidadoresDominioAb.this.getNomeFicha(), ValidadoresDominioAb.this.getNomeProgramaAuxiliar());
/*     */           }
/*     */           
/* 104 */           return ret;
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public ValidadorDefault getValidadorData(byte severidade) {
/* 110 */     return new ValidadorDefault(severidade)
/*     */       {
/*     */         public RetornoValidacao validarImplementado()
/*     */         {
/* 114 */           RetornoValidacao ret = null;
/*     */           
/* 116 */           if (!getInformacao().isVazio() && !getInformacao().naoFormatado().matches("^(\\d){8}$")) {
/* 117 */             ret = ValidadoresDominioAb.this.obterNovoRetornoValidacao(getInformacao().getNomeCampo(), ValidadoresDominioAb.this.getNomeFicha(), ValidadoresDominioAb.this.getNomeProgramaAuxiliar());
/*     */           }
/*     */           
/* 120 */           return ret;
/*     */         }
/*     */       };
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\ValidadoresDominioAb.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */