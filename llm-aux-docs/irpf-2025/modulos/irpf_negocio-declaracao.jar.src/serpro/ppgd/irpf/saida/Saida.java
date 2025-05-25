/*     */ package serpro.ppgd.irpf.saida;
/*     */ 
/*     */ import java.util.List;
/*     */ import serpro.ppgd.cacheni.CacheNI;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.IdentificadorDeclaracao;
/*     */ import serpro.ppgd.irpf.ObservadorEspacosDuplicados;
/*     */ import serpro.ppgd.irpf.ValidadorNaoNuloIRPF;
/*     */ import serpro.ppgd.irpf.ValidadorNomeIRPF;
/*     */ import serpro.ppgd.irpf.gui.saida.PainelSaida;
/*     */ import serpro.ppgd.irpf.rendavariavel.ColecaoFundosInvestimentosDependente;
/*     */ import serpro.ppgd.irpf.rendavariavel.ColecaoRendaVariavelDependente;
/*     */ import serpro.ppgd.irpf.rendavariavel.FundosInvestimentos;
/*     */ import serpro.ppgd.irpf.rendavariavel.ItemFundosInvestimentosDependente;
/*     */ import serpro.ppgd.irpf.rendavariavel.ItemRendaVariavelDependente;
/*     */ import serpro.ppgd.irpf.rendavariavel.RendaVariavel;
/*     */ import serpro.ppgd.irpf.rendpf.ColecaoRendPFDependente;
/*     */ import serpro.ppgd.irpf.rendpf.ItemRendPFDependente;
/*     */ import serpro.ppgd.irpf.rendpf.RendPF;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.irpf.util.ValidadorIRPF;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.CPF;
/*     */ import serpro.ppgd.negocio.Codigo;
/*     */ import serpro.ppgd.negocio.ConstantesGlobais;
/*     */ import serpro.ppgd.negocio.Data;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.NI;
/*     */ import serpro.ppgd.negocio.ObjetoFicha;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ import serpro.ppgd.negocio.ValidadorDefault;
/*     */ import serpro.ppgd.negocio.ValidadorIf;
/*     */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorCPF;
/*     */ 
/*     */ public class Saida
/*     */   extends ObjetoNegocio implements ObjetoFicha {
/*  40 */   protected transient IdentificadorDeclaracao identificadorDeclaracao = null;
/*     */   
/*  42 */   private CPF cpfProcurador = new CPF(this, "CPF do Procurador");
/*  43 */   private Alfa nomeProcurador = new Alfa(this, "Nome do Procurador", 60);
/*  44 */   private Alfa endProcurador = new Alfa(this, "Endereço do Procurador", 80);
/*  45 */   private Data dtCondicaoNaoResidente = new Data(this, "Data da Caracterização da Condição de Não Residente");
/*  46 */   private Data dtCondicaoResidente = new Data(this, "Data da Caracterização da Condição de Residente");
/*  47 */   private Codigo paisResidencia = new Codigo(this, "País", CadastroTabelasIRPF.recuperarPaisesExterior());
/*     */   
/*     */   public Saida(final DeclaracaoIRPF dec) {
/*  50 */     this.identificadorDeclaracao = dec.getIdentificadorDeclaracao();
/*     */     
/*  52 */     CacheNI.getInstancia().registrarNINome((NI)this.cpfProcurador, this.nomeProcurador);
/*     */     
/*  54 */     if (this.identificadorDeclaracao.isSaida()) {
/*  55 */       ValidadorCPF vCPF = new ValidadorCPF((byte)3);
/*  56 */       vCPF.setMensagemValidacao(MensagemUtil.getMensagem("saida_cpf_procurador_invalido"));
/*  57 */       this.cpfProcurador.addValidador((ValidadorIf)vCPF);
/*     */       
/*  59 */       this.cpfProcurador.addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */           {
/*     */             public RetornoValidacao validarImplementado()
/*     */             {
/*  63 */               if (Saida.this.getCpfProcurador().naoFormatado().equals(Saida.this.identificadorDeclaracao.getCpf().naoFormatado())) {
/*  64 */                 return new RetornoValidacao(MensagemUtil.getMensagem("saida_cpf_procurador_igual_contribuinte"), getSeveridade());
/*     */               }
/*  66 */               return null;
/*     */             }
/*     */           });
/*     */ 
/*     */       
/*  71 */       this.cpfProcurador.addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*     */           {
/*     */             public RetornoValidacao validarImplementado()
/*     */             {
/*  75 */               if ((!Saida.this.getNomeProcurador().isVazio() || !Saida.this.getEndProcurador().isVazio()) && Saida.this.getCpfProcurador().isVazio()) {
/*  76 */                 return new RetornoValidacao(MensagemUtil.getMensagem("saida_cpf_procurador_branco"), getSeveridade());
/*     */               }
/*  78 */               return null;
/*     */             }
/*     */           });
/*     */ 
/*     */       
/*  83 */       this.nomeProcurador.addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)2));
/*  84 */       this.nomeProcurador.addValidador((ValidadorIf)new ValidadorNomeIRPF());
/*     */       
/*  86 */       this.dtCondicaoNaoResidente.addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3));
/*     */       
/*  88 */       this.dtCondicaoNaoResidente.addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*     */           {
/*     */             
/*     */             public RetornoValidacao validarImplementado()
/*     */             {
/*  93 */               if (!Saida.this.dtCondicaoNaoResidente.isVazio()) {
/*  94 */                 return ValidadorIRPF.validarData(getInformacao(), Integer.valueOf(ConstantesGlobais.ANO_BASE_SEGUINTE).intValue());
/*     */               }
/*     */               
/*  97 */               return null;
/*     */             }
/*     */           });
/*     */       
/* 101 */       this.dtCondicaoNaoResidente.addValidador((ValidadorIf)new ValidadorAno((byte)3));
/*     */       
/* 103 */       this.dtCondicaoResidente.addValidador((ValidadorIf)new ValidadorAno((byte)3)
/*     */           {
/*     */             public RetornoValidacao validarImplementado()
/*     */             {
/* 107 */               setMensagemValidacao(MensagemUtil.getMensagem("saida_dt_condicao_residente_posterior", new String[] { ConstantesGlobais.ANO_BASE }));
/*     */               
/* 109 */               return super.validarImplementado();
/*     */             }
/*     */           });
/*     */ 
/*     */       
/* 114 */       this.dtCondicaoResidente.addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */           {
/*     */             
/*     */             public RetornoValidacao validarImplementado()
/*     */             {
/* 119 */               if (!Saida.this.dtCondicaoNaoResidente.isVazio() && !Saida.this.dtCondicaoResidente.isVazio() && Saida.this.dtCondicaoResidente.maisNova(Saida.this.dtCondicaoNaoResidente)) {
/* 120 */                 return new RetornoValidacao(MensagemUtil.getMensagem("saida_dt_condicao_residente_posterior", new String[] { ConstantesGlobais.ANO_BASE }), (byte)3);
/*     */               }
/*     */ 
/*     */               
/* 124 */               return null;
/*     */             }
/*     */           });
/*     */ 
/*     */ 
/*     */       
/* 130 */       this.dtCondicaoResidente.addObservador(this.dtCondicaoResidente.getNomeCampo(), new Observador()
/*     */           {
/*     */             public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/* 133 */               int mes = 1;
/* 134 */               if (!valorNovo.toString().trim().isEmpty()) {
/* 135 */                 mes = Integer.valueOf(valorNovo.toString().substring(2, 4).trim()).intValue();
/*     */               }
/* 137 */               RendPF rendPFTitular = dec.getRendPFTitular();
/* 138 */               ColecaoRendPFDependente colecaoRendPFDependente = dec.getRendPFDependente();
/* 139 */               for (int i = 0; i < mes - 1; i++) {
/* 140 */                 dec.getAtividadeRural().getBrasil().getReceitasDespesas().getMesReceitaPorIndice(i).clear();
/* 141 */                 rendPFTitular.getContasAno().getArrayMeses()[i].clear();
/* 142 */                 rendPFTitular.getMesRendPFPorIndice(i).clear();
/* 143 */                 for (ItemRendPFDependente itemRendPFDependente : colecaoRendPFDependente.itens()) {
/* 144 */                   itemRendPFDependente.getRendimentos().getContasAno().getArrayMeses()[i].clear();
/* 145 */                   itemRendPFDependente.getRendimentos().getMesRendPFPorIndice(mes).clear();
/*     */                 } 
/*     */               } 
/*     */             }
/*     */           });
/*     */       
/* 151 */       this.dtCondicaoNaoResidente.addObservador(this.dtCondicaoNaoResidente.getNomeCampo(), new Observador()
/*     */           {
/*     */             public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */             {
/* 155 */               int mes = 12;
/* 156 */               if (!valorNovo.toString().trim().isEmpty()) {
/* 157 */                 mes = Integer.valueOf(valorNovo.toString().substring(2, 4).trim()).intValue();
/*     */               }
/* 159 */               RendPF rendPFTitular = dec.getRendPFTitular();
/* 160 */               ColecaoRendPFDependente colecaoRendPFDependente = dec.getRendPFDependente();
/* 161 */               FundosInvestimentos fundosInvestimentosTitular = dec.getFundosInvestimentos();
/* 162 */               ColecaoFundosInvestimentosDependente colecaoFundosInvestimentosDependente = dec.getFundosInvestimentosDependente();
/* 163 */               RendaVariavel rendaVariavelTitular = dec.getRendaVariavel();
/* 164 */               ColecaoRendaVariavelDependente colecaoRendaVariavelDependente = dec.getRendaVariavelDependente();
/* 165 */               for (int i = mes; i < 12; i++) {
/* 166 */                 dec.getAtividadeRural().getBrasil().getReceitasDespesas().getMesReceitaPorIndice(i).clear();
/* 167 */                 rendPFTitular.getContasAno().getArrayMeses()[i].clear();
/* 168 */                 rendPFTitular.getMesRendPFPorIndice(i).clear();
/* 169 */                 fundosInvestimentosTitular.getMeses()[i].clear();
/* 170 */                 rendaVariavelTitular.getGanhosPorIndice(i).clear();
/* 171 */                 for (ItemRendPFDependente itemRendPFDependente : colecaoRendPFDependente.itens()) {
/* 172 */                   itemRendPFDependente.getRendimentos().getContasAno().getArrayMeses()[i].clear();
/* 173 */                   itemRendPFDependente.getRendimentos().getMesRendPFPorIndice(i).clear();
/*     */                 } 
/* 175 */                 for (ItemFundosInvestimentosDependente itemFundosInvestimentosDependente : colecaoFundosInvestimentosDependente.itens()) {
/* 176 */                   itemFundosInvestimentosDependente.getFundosInvestimentos().getMeses()[i].clear();
/*     */                 }
/* 178 */                 for (ItemRendaVariavelDependente itemRendaVariavelDependente : colecaoRendaVariavelDependente.itens()) {
/* 179 */                   itemRendaVariavelDependente.getRendaVariavel().getGanhosPorIndice(i).clear();
/*     */                 }
/*     */               } 
/*     */             }
/*     */           });
/*     */     } 
/*     */     
/* 186 */     this.nomeProcurador.addObservador((Observador)new ObservadorEspacosDuplicados());
/* 187 */     this.endProcurador.addObservador((Observador)new ObservadorEspacosDuplicados());
/*     */     
/* 189 */     setFicha("Saída");
/*     */   }
/*     */   
/*     */   public CPF getCpfProcurador() {
/* 193 */     return this.cpfProcurador;
/*     */   }
/*     */   
/*     */   public Data getDtCondicaoNaoResidente() {
/* 197 */     return this.dtCondicaoNaoResidente;
/*     */   }
/*     */   
/*     */   public Data getDtCondicaoResidente() {
/* 201 */     return this.dtCondicaoResidente;
/*     */   }
/*     */   
/*     */   public Alfa getEndProcurador() {
/* 205 */     return this.endProcurador;
/*     */   }
/*     */   
/*     */   public Alfa getNomeProcurador() {
/* 209 */     return this.nomeProcurador;
/*     */   }
/*     */   
/*     */   public Codigo getPaisResidencia() {
/* 213 */     return this.paisResidencia;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMesInicial() {
/* 218 */     Data dataResidente = getDtCondicaoResidente();
/* 219 */     String mes = dataResidente.formatado().substring(3, 5);
/*     */     
/* 221 */     if (mes.trim().isEmpty()) {
/* 222 */       mes = "01";
/*     */     }
/*     */     
/* 225 */     return Integer.parseInt(mes);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMesFinal() {
/* 230 */     Data dataNaoResidente = getDtCondicaoNaoResidente();
/*     */     
/* 232 */     String mes = dataNaoResidente.formatado().substring(3, 5);
/*     */     
/* 234 */     if (mes.trim().isEmpty()) {
/* 235 */       mes = "12";
/*     */     }
/*     */     
/* 238 */     return Integer.parseInt(mes);
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 243 */     return recuperarCamposInformacao();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClasseFicha() {
/* 248 */     return PainelSaida.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVazio() {
/* 253 */     return (this.cpfProcurador.isVazio() && this.nomeProcurador.isVazio() && this.endProcurador.isVazio() && this.dtCondicaoNaoResidente.isVazio() && this.dtCondicaoResidente
/* 254 */       .isVazio());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 260 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 265 */     return "Saída";
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\saida\Saida.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */