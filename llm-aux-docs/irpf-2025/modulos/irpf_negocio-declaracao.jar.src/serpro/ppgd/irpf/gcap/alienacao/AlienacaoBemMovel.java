/*     */ package serpro.ppgd.irpf.gcap.alienacao;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.irpf.gcap.ObjetoGCAP;
/*     */ import serpro.ppgd.irpf.gcap.apuracao.Apuracao;
/*     */ import serpro.ppgd.irpf.gcap.apuracao.ApuracaoBem;
/*     */ import serpro.ppgd.irpf.gcap.apuracao.ApuracaoBemMovel;
/*     */ import serpro.ppgd.irpf.gcap.aquisicao.Aquisicao;
/*     */ import serpro.ppgd.irpf.gcap.bensmoveis.BemMovel;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.irpf.util.AplicacaoPropertiesUtil;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.Codigo;
/*     */ import serpro.ppgd.negocio.Logico;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AlienacaoBemMovel
/*     */   extends AlienacaoBem
/*     */   implements ObjetoGCAP
/*     */ {
/*     */   public static final String NOME_FICHA_BEM_MOVEl = "Bens Móveis";
/*     */   public static final String NOME_ABA_IDENTIFICACAO = "Identificação";
/*     */   public static final String NOME_ABA_ADQUIRENTES = "Adquirentes";
/*     */   public static final String NOME_ABA_OPERACAO = "Operação";
/*     */   public static final String NOME_ABA_CALCULO = "Cálculo do Imposto";
/*     */   public static final int REDUCAO_AUSENTE = 0;
/*     */   public static final int REDUCAO_IMOVEL_PEQUENO_VALOR = 1;
/*  38 */   private BemMovel bemMovel = new BemMovel(this);
/*  39 */   private ApuracaoBemMovel apuracao = new ApuracaoBemMovel();
/*  40 */   private ApuracaoBemMovel apuracaoFinal = new ApuracaoBemMovel();
/*  41 */   private Alfa alienacaoAPrazoMovelAux = new Alfa(this, "Alienação foi a prazo/prestação?", 1);
/*     */ 
/*     */   
/*     */   public AlienacaoBemMovel() {
/*  45 */     this.bemMovel.getAquisicao().getCustoAquisicaoOrigemNacionalReal().setReadOnly(true);
/*  46 */     this.bemMovel.getAquisicao().getCustoAquisicaoTotalDolar().setReadOnly(true);
/*     */     
/*  48 */     getBemMovel().getBemAdquiridoNoBrasil().addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/*  52 */             if (AlienacaoBemMovel.this.getBemMovel().isAdquiridoNoBrasil()) {
/*  53 */               AlienacaoBemMovel.this.getNatureza().setColecaoElementoTabela(CadastroTabelasIRPF.recuperarNaturezaGCAPBemMovelDireitos());
/*  54 */             } else if (AlienacaoBemMovel.this.getBemMovel().isAdquiridoNoExterior()) {
/*  55 */               AlienacaoBemMovel.this.getNatureza().setColecaoElementoTabela(CadastroTabelasIRPF.recuperarNaturezaGCMEBemMovelDireitos());
/*     */             } else {
/*  57 */               AlienacaoBemMovel.this.getNatureza().setColecaoElementoTabela(new ArrayList());
/*     */             } 
/*     */           }
/*     */         });
/*     */     
/*  62 */     if (getBemMovel().isAdquiridoNoBrasil()) {
/*  63 */       getNatureza().setColecaoElementoTabela(CadastroTabelasIRPF.recuperarNaturezaGCAPBemMovelDireitos());
/*  64 */     } else if (getBemMovel().isAdquiridoNoExterior()) {
/*  65 */       getNatureza().setColecaoElementoTabela(CadastroTabelasIRPF.recuperarNaturezaGCMEBemMovelDireitos());
/*     */     } else {
/*  67 */       getNatureza().setColecaoElementoTabela(new ArrayList());
/*     */     } 
/*     */   }
/*     */   
/*     */   public BemMovel getBemMovel() {
/*  72 */     return this.bemMovel;
/*     */   }
/*     */   
/*     */   public Alfa getAlienacaoAPrazoMovelAux() {
/*  76 */     return this.alienacaoAPrazoMovelAux;
/*     */   }
/*     */ 
/*     */   
/*     */   public Aquisicao getAquisicao() {
/*  81 */     return getBemMovel().getAquisicao();
/*     */   }
/*     */ 
/*     */   
/*     */   public ApuracaoBemMovel getApuracao() {
/*  86 */     return this.apuracao;
/*     */   }
/*     */ 
/*     */   
/*     */   public ApuracaoBemMovel getApuracaoFinal() {
/*  91 */     return this.apuracaoFinal;
/*     */   }
/*     */ 
/*     */   
/*     */   public Codigo obterCodigoOrigemRendimentos() {
/*  96 */     return getBemMovel().getAquisicao().getOrigemRendimentos();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAlienacaoBrasil() {
/* 101 */     return getBemMovel().isAdquiridoNoBrasil();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVazio() {
/* 106 */     return getBemMovel().getBemAdquiridoNoBrasil().isVazio();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPrevisaoPrejuizo() {
/* 111 */     boolean retorno = false;
/* 112 */     if (!isAlienacaoBrasil()) {
/* 113 */       String origemRendimentos = obterCodigoOrigemRendimentos().naoFormatado();
/* 114 */       ValorPositivo ganhoCapital = new ValorPositivo();
/* 115 */       if ("1".equals(origemRendimentos)) {
/* 116 */         ganhoCapital.setConteudo((Valor)getApuracao().getGanhoCapital1OrigemNacionalReal());
/* 117 */       } else if ("2".equals(origemRendimentos)) {
/* 118 */         ganhoCapital.setConteudo((Valor)getApuracao().getGanhoCapital1OrigemMEReal());
/* 119 */       } else if ("3".equals(origemRendimentos)) {
/* 120 */         ganhoCapital.setConteudo(getApuracao().getGanhoCapital1OrigemNacionalReal().operacao('+', (Valor)
/* 121 */               getApuracao().getGanhoCapital1OrigemMEReal()));
/*     */       } 
/* 123 */       if (ganhoCapital.isVazio()) {
/* 124 */         retorno = true;
/*     */       }
/*     */     } 
/* 127 */     return retorno;
/*     */   }
/*     */   
/*     */   public int obterEnquadramentoInicial() {
/* 131 */     int retorno = 0;
/* 132 */     if (!isValorOperacaoMaior35K() && (Logico.NAO.equals(getBemGrandeValor().formatado()) || Logico.NAO
/* 133 */       .equals(getBemGrandeValorOperacao().formatado()))) {
/* 134 */       retorno = 1;
/*     */     }
/* 136 */     return retorno;
/*     */   }
/*     */   
/*     */   public int obterEnquadramentoFinal() {
/* 140 */     int retorno = 0;
/* 141 */     if (!isValorParcelasMaior35K() && Logico.NAO.equals(getBemGrandeValor().formatado())) {
/* 142 */       retorno = 1;
/*     */     }
/* 144 */     return retorno;
/*     */   }
/*     */   
/*     */   public String obterMensagemMudancaEnquadramento() {
/* 148 */     String mensagem = null;
/* 149 */     int enquadramentoInicial = obterEnquadramentoInicial();
/* 150 */     int enquadramentoFinal = obterEnquadramentoFinal();
/* 151 */     if (getColecaoParcelaAlienacao().obterUltimaParcela() != null && enquadramentoInicial != enquadramentoFinal) {
/* 152 */       if (enquadramentoInicial == 0) {
/* 153 */         if (enquadramentoFinal == 1) {
/* 154 */           mensagem = "<html>De acordo com o valor recebido (R$), há direito à isenção por alienação de bem de pequeno valor.</html>";
/*     */         }
/* 156 */       } else if (enquadramentoInicial == 1 && 
/* 157 */         enquadramentoFinal == 0) {
/* 158 */         mensagem = "<html>De acordo com o valor recebido (R$), não há direito à isenção por alienação de bem de pequeno valor.<br>O imposto devido será integralmente cobrado na última parcela.</html>";
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 163 */     return mensagem;
/*     */   }
/*     */   
/*     */   public String obterTextoIsencao() {
/* 167 */     String msgIsencao = "";
/* 168 */     if (isAlienacaoAVista()) {
/* 169 */       if (getCalculoImposto().getImpostoDevido().isVazio()) {
/* 170 */         msgIsencao = MensagemUtil.getMensagem("impostoDevidoZero");
/*     */       }
/*     */     }
/* 173 */     else if (getDataRecebimentoUltimaParcela().isVazio()) {
/* 174 */       if (isPrevisaoPrejuizo() || isIsento()) {
/* 175 */         msgIsencao = MensagemUtil.getMensagem("impostoDevidoZero");
/*     */       }
/*     */     } else {
/* 178 */       String msgMudancaEnquadramento = obterMensagemMudancaEnquadramento();
/* 179 */       if (msgMudancaEnquadramento != null) {
/* 180 */         msgIsencao = msgMudancaEnquadramento;
/* 181 */       } else if (getAjuste().getImpostoDevido().isVazio()) {
/* 182 */         msgIsencao = MensagemUtil.getMensagem("impostoDevidoZero");
/*     */       } 
/*     */     } 
/*     */     
/* 186 */     return msgIsencao;
/*     */   }
/*     */   
/*     */   public boolean podeResponderPerguntaPequenoValorNoExteriorPorParcelas() {
/* 190 */     boolean retorno = false;
/* 191 */     boolean residenteNoBrasil = Logico.SIM.equals(getResidenteBrasil().naoFormatado());
/* 192 */     ValorPositivo valorAlienacaoReal = new ValorPositivo();
/* 193 */     if (getBemMovel().isAdquiridoNoExterior()) {
/* 194 */       valorAlienacaoReal.setConteudo(getValorAlienacaoDolar().operacao('*', (Valor)getCotacaoDolarDataAlienacao()));
/* 195 */       if (valorAlienacaoReal.comparacao(">", "35.000,00") && 
/* 196 */         !getDataRecebimentoUltimaParcela().isVazio()) {
/* 197 */         valorAlienacaoReal.clear();
/* 198 */         for (ParcelaAlienacaoBem p : getColecaoParcelaAlienacao().itens()) {
/* 199 */           ValorPositivo somaParcela = new ValorPositivo();
/* 200 */           somaParcela.setConteudo((Valor)p.getValorRecebidoDolar());
/* 201 */           somaParcela.append('*', (Valor)p.getCotacaoDolar());
/* 202 */           valorAlienacaoReal.append('+', (Valor)somaParcela);
/*     */         } 
/* 204 */         if (residenteNoBrasil && valorAlienacaoReal.comparacao("<=", "35.000,00") && 
/* 205 */           getDataAlienacao().naoFormatado().length() == 8 && 
/* 206 */           AplicacaoPropertiesUtil.getExercicio().equals(getDataAlienacao().getAno())) {
/* 207 */           retorno = true;
/*     */         }
/*     */       } 
/*     */     } 
/* 211 */     return retorno;
/*     */   }
/*     */   
/*     */   public boolean podePreencherCalculo() {
/* 215 */     boolean retorno = true;
/* 216 */     if (getAlienacaoAPrazo().isVazio() || getNatureza().isVazio() || (
/* 217 */       !isAlienacaoBrasil() && getAquisicao().getOrigemRendimentos().isVazio())) {
/* 218 */       retorno = false;
/*     */     }
/* 220 */     return retorno;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAdquirenteRequerido() {
/* 225 */     boolean requerido = super.isAdquirenteRequerido();
/* 226 */     if (requerido && 
/* 227 */       !isAlienacaoBrasil() && (
/* 228 */       String.valueOf(AlienacaoBem.CODIGO_NATUREZA_LIQUID_OU_RESGATE_DE_APLIC_FINANCEIRA).equals(getNatureza().naoFormatado()) || 
/* 229 */       String.valueOf(AlienacaoBem.CODIGO_NATUREZA_ALIENACAO_ACOES_BOLSA_VALORES).equals(getNatureza().naoFormatado()) || 
/* 230 */       String.valueOf(AlienacaoBem.CODIGO_NATUREZA_CREDITO_JUROS_APLICACAO_FINANCEIRA).equals(getNatureza().naoFormatado())))
/*     */     {
/* 232 */       requerido = false;
/*     */     }
/*     */     
/* 235 */     return requerido;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\alienacao\AlienacaoBemMovel.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */