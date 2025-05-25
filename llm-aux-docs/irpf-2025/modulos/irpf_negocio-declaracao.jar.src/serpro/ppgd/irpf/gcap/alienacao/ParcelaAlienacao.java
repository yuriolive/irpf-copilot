/*     */ package serpro.ppgd.irpf.gcap.alienacao;
/*     */ 
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.irpf.gcap.ValorBigDecimalGCME;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.Data;
/*     */ import serpro.ppgd.negocio.Logico;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ public class ParcelaAlienacao
/*     */   extends ObjetoNegocio
/*     */ {
/*     */   public static final String CAMPO_VALOR_RECEBIDO = "Valor recebido";
/*     */   public static final String CAMPO_CUSTO_CORRETAGEM = "Custo da Corretagem";
/*     */   public static final String CAMPO_VALOR_LIQUIDO_ALIENACAO = "Valor Líquido de Alienação";
/*     */   public static final String CAMPO_GANHO_1_PROPORCIONAL = "Ganho de Capital Proporcional - Resultado 1";
/*     */   public static final String CAMPO_GANHO_2_PROPORCIONAL = "Ganho de Capital Proporcional - Resultado 2";
/*     */   public static final String CAMPO_GANHO_3_PROPORCIONAL = "Ganho de Capital Proporcional - Resultado 3";
/*     */   public static final String CAMPO_GANHO_4_PROPORCIONAL = "Ganho de Capital Proporcional - Resultado 4";
/*     */   public static final String CAMPO_GANHO_5_PROPORCIONAL = "Ganho de Capital Proporcional - Resultado 5";
/*     */   public static final String CAMPO_GANHO_1_PROPORCIONAL_MN = "Ganho de Capital Proporcional MN - Resultado 1";
/*     */   public static final String CAMPO_GANHO_2_PROPORCIONAL_MN = "Ganho de Capital Proporcional MN - Resultado 2";
/*     */   public static final String CAMPO_GANHO_3_PROPORCIONAL_MN = "Ganho de Capital Proporcional MN - Resultado 3";
/*     */   public static final String CAMPO_GANHO_4_PROPORCIONAL_MN = "Ganho de Capital Proporcional MN - Resultado 4";
/*     */   public static final String CAMPO_GANHO_5_PROPORCIONAL_MN = "Ganho de Capital Proporcional MN - Resultado 5";
/*     */   public static final String CAMPO_GANHO_1_PROPORCIONAL_ME = "Ganho de Capital Proporcional ME - Resultado 1";
/*     */   public static final String CAMPO_GANHO_2_PROPORCIONAL_ME = "Ganho de Capital Proporcional ME - Resultado 2";
/*     */   public static final String CAMPO_GANHO_3_PROPORCIONAL_ME = "Ganho de Capital Proporcional ME - Resultado 3";
/*     */   public static final String CAMPO_GANHO_4_PROPORCIONAL_ME = "Ganho de Capital Proporcional ME - Resultado 4";
/*     */   public static final String CAMPO_GANHO_5_PROPORCIONAL_ME = "Ganho de Capital Proporcional ME - Resultado 5";
/*     */   public static final String CAMPO_IMPOSTO_DEVIDO = "Imposto Devido (R$)";
/*     */   public static final String CAMPO_IMPOSTO_PAGO_EXTERIOR_PASSIVEL_COMPENSACAO = "Imposto Pago no Exterior Passível de Compensação";
/*     */   public static final String CAMPO_IMPOSTO_DEVIDO_APOS_COMPENSACAO = "Imposto Devido Após Compensação(R$)";
/*     */   public static final String CAMPO_IR_FONTE_LEI_11033_2004 = "IR na Fonte (Lei 11.033/2004)(R$)";
/*     */   public static final String CAMPO_IMPOSTO_PAGO = "Imposto Pago";
/*     */   public static final String CAMPO_VALOR_RECEBIDO_DOLAR = "Valor recebido (US$)";
/*     */   public static final String CAMPO_VALOR_RECEBIDO_REAL = "Valor recebido (R$)";
/*     */   public static final String CAMPO_CUSTO_CORRETAGEM_DOLAR = "Custo da Corretagem (US$)";
/*     */   public static final String CAMPO_VALOR_LIQUIDO_ALIENACAO_DOLAR = "Valor Líquido da Alienação - (US$)";
/*     */   public static final String CAMPO_VALOR_LIQUIDO_ALIENACAO_REAL = "Valor Líquido da Alienação - (R$)";
/*     */   public static final String CAMPO_GANHO_CAPITAL_1_PROPORCIONAL_NACIONAL_REAL = "Ganho de Capital Proporcional - Rendimentos em Reais (R$)";
/*     */   public static final String CAMPO_GANHO_CAPITAL_1_PROPORCIONAL_ME_REAL = "Ganho de Capital Proporcional - Rendimentos ME (R$)";
/*     */   public static final String CAMPO_GANHO_CAPITAL_1_PROPORCIONAL_ME_DOLAR = "Ganho de Capital Proporcional - Rendimentos ME (US$)";
/*     */   public static final String CAMPO_GANHO_CAPITAL_1_PROPORCIONAL_TOTAL_REAL = "Ganho de Capital 1 Proporcional Total - (R$)";
/*     */   public static final String CAMPO_GANHO_CAPITAL_5_PROPORCIONAL_TOTAL_REAL = "Ganho de Capital 5 Proporcional Total - (R$)";
/*     */   public static final String CAMPO_CUSTO_AQUISICAO_PROPORCIONAL = "Custo de Aquisição Proporcional";
/*     */   public static final String CAMPO_CUSTO_AQUISICAO_TORNA_PROPORCIONAL = "Custo de aquisição proporcional da torna (R$)";
/*     */   public static final String CAMPO_CUSTO_AQUISICAO_PROPORCIONAL_NACIONAL_REAL = "Custo de Aquisição Proporcional Rendimentos em Reais (R$)";
/*     */   public static final String CAMPO_CUSTO_AQUISICAO_PROPORCIONAL_ME_DOLAR = "Custo de Aquisição Proporcional - Rendimentos ME (US$)";
/*     */   public static final String CAMPO_CUSTO_AQUISICAO_TORNA_PROPORCIONAL_NACIONAL_REAL = "Custo de Aquisição Torna Proporcional Rendimentos em Reais (R$)";
/*     */   public static final String CAMPO_CUSTO_AQUISICAO_TORNA_PROPORCIONAL_ME_DOLAR = "Custo de Aquisição Torna Proporcional - Rendimentos ME (US$)";
/*  53 */   private Logico ultimaParcela = new Logico(this, "Esta é a Última Parcela?");
/*     */   
/*  55 */   private Data dataRecebimento = new Data(this, "Data de Recebimento");
/*     */ 
/*     */   
/*  58 */   private ValorPositivo valorRecebido = new ValorPositivo(this, "Valor recebido");
/*     */   
/*  60 */   private ValorPositivo custoAquisicaoProporcional = new ValorPositivo(this, "Custo de Aquisição Proporcional");
/*     */   
/*  62 */   private ValorPositivo custoAquisicaoTornaProporcional = new ValorPositivo(this, "Custo de aquisição proporcional da torna (R$)");
/*     */ 
/*     */   
/*  65 */   private ValorPositivo custoCorretagem = new ValorPositivo(this, "Custo da Corretagem");
/*     */ 
/*     */   
/*  68 */   private ValorPositivo valorLiquidoAlienacao = new ValorPositivo(this, "Valor Líquido de Alienação");
/*     */   
/*  70 */   private ValorPositivo ganhoCapitalTotal = new ValorPositivo(this, "Ganho de Capital Proporcional Total");
/*     */ 
/*     */   
/*  73 */   private ValorPositivo percentualGanho = new ValorPositivo(this, "% Ganho sobre Valor Alienação", 11, 6);
/*     */ 
/*     */   
/*  76 */   private ValorPositivo ganhoCapital1Proporcional = new ValorPositivo(this, "Ganho de Capital Proporcional - Resultado 1");
/*     */   
/*  78 */   private ValorBigDecimalGCME percentualReducaoLei7713 = new ValorBigDecimalGCME(this, "Percentual de redução (lei 7.713, de 1988)", 3, 6);
/*     */   
/*  80 */   private ValorPositivo valorReducaoLei7713 = new ValorPositivo(this, "Valor de redução (lei 7.713, de 1988) - (R$)");
/*     */   
/*  82 */   private ValorPositivo ganhoCapital2Proporcional = new ValorPositivo(this, "Ganho de Capital Proporcional - Resultado 2");
/*     */   
/*  84 */   private ValorBigDecimalGCME percentualReducaoLei11196FR1 = new ValorBigDecimalGCME(this, "Percentual de redução (lei 11.196, de 2005) - FR1", 3, 6);
/*     */   
/*  86 */   private ValorPositivo valorReducaoLei11196FR1 = new ValorPositivo(this, "Valor de redução (lei 7.713, de 1988) - FR1 - (R$)");
/*     */   
/*  88 */   private ValorPositivo ganhoCapital3Proporcional = new ValorPositivo(this, "Ganho de Capital Proporcional - Resultado 3");
/*     */   
/*  90 */   private ValorBigDecimalGCME percentualReducaoLei11196FR2 = new ValorBigDecimalGCME(this, "Percentual de redução (lei 11.196, de 2005) - FR2", 3, 6);
/*     */   
/*  92 */   private ValorPositivo valorReducaoLei11196FR2 = new ValorPositivo(this, "Valor de redução (lei 7.713, de 1988) - FR2 - (R$)");
/*     */   
/*  94 */   private ValorPositivo ganhoCapital4Proporcional = new ValorPositivo(this, "Ganho de Capital Proporcional - Resultado 4");
/*     */   
/*  96 */   private ValorPositivo valorInformadoReducaoAplicacaoOutroImovel = new ValorPositivo(this, "Valor de redução - Aplicação em outro imóvel - (R$)");
/*     */   
/*  98 */   private ValorBigDecimalGCME percentualReducaoAplicacaoOutroImovel = new ValorBigDecimalGCME(this, "Percentual de redução - Aplicação em outro imóvel", 3, 6);
/*     */   
/* 100 */   private ValorPositivo valorReducaoAplicacaoOutroImovel = new ValorPositivo(this, "Valor calculado de redução - Aplicação em outro imóvel - (R$)");
/*     */   
/* 102 */   private ValorBigDecimalGCME percentualReducaoUnicoImovel = new ValorBigDecimalGCME(this, "Percentual de redução - Bem de pequeno valor", 3, 6);
/*     */   
/* 104 */   private ValorPositivo valorReducaoUnicoImovel = new ValorPositivo(this, "Valor de redução - Bem de pequeno valor - (R$)");
/*     */   
/* 106 */   private ValorBigDecimalGCME percentualReducaoBemPequenoValor = new ValorBigDecimalGCME(this, "Percentual de redução - Bem de pequeno valor", 3, 6);
/*     */   
/* 108 */   private ValorPositivo valorReducaoBemPequenoValor = new ValorPositivo(this, "Valor de redução - Bem de pequeno valor - (R$)");
/*     */   
/* 110 */   private ValorPositivo ganhoCapital5Proporcional = new ValorPositivo(this, "Ganho de Capital Proporcional - Resultado 5");
/*     */   
/* 112 */   private ValorBigDecimalGCME aliquotaMedia = new ValorBigDecimalGCME(this, "Alíquota Média de Imposto Devido", 11, 6);
/*     */   
/* 114 */   private ValorPositivo impostoDevido = new ValorPositivo(this, "Imposto Devido (R$)");
/*     */   
/* 116 */   private ValorPositivo impostoPago = new ValorPositivo(this, "Imposto Pago");
/*     */   
/* 118 */   private ValorPositivo impostoDevido2 = new ValorPositivo(this, "Imposto Devido Após Compensação(R$)");
/*     */   
/* 120 */   private ValorPositivo irrfLei110332004 = new ValorPositivo(this, "IR na Fonte (Lei 11.033/2004)(R$)");
/*     */   
/* 122 */   private transient Alfa mesRecebimentoAux = new Alfa(this, "Mês Recebimento da Parcela", 2);
/*     */   
/*     */   private boolean emReordenacao = false;
/*     */   
/*     */   public ParcelaAlienacao() {
/* 127 */     getUltimaParcela().addOpcao(Logico.SIM, Logico.LABEL_SIM);
/* 128 */     getUltimaParcela().addOpcao(Logico.NAO, Logico.LABEL_NAO);
/* 129 */     getValorRecebido().setReadOnly(true);
/* 130 */     getCustoCorretagem().setReadOnly(true);
/* 131 */     getValorLiquidoAlienacao().setReadOnly(true);
/* 132 */     getPercentualGanho().setReadOnly(true);
/* 133 */     getCustoAquisicaoTornaProporcional().setReadOnly(true);
/* 134 */     getGanhoCapital1Proporcional().setReadOnly(true);
/* 135 */     getPercentualReducaoLei7713().setReadOnly(true);
/* 136 */     getValorReducaoLei7713().setReadOnly(true);
/* 137 */     getGanhoCapital2Proporcional().setReadOnly(true);
/* 138 */     getPercentualReducaoLei11196FR1().setReadOnly(true);
/* 139 */     getValorReducaoLei11196FR1().setReadOnly(true);
/* 140 */     getGanhoCapital3Proporcional().setReadOnly(true);
/* 141 */     getPercentualReducaoLei11196FR2().setReadOnly(true);
/* 142 */     getValorReducaoLei11196FR2().setReadOnly(true);
/* 143 */     getGanhoCapital4Proporcional().setReadOnly(true);
/* 144 */     getPercentualReducaoAplicacaoOutroImovel().setReadOnly(true);
/* 145 */     getValorReducaoAplicacaoOutroImovel().setReadOnly(true);
/* 146 */     getPercentualReducaoUnicoImovel().setReadOnly(true);
/* 147 */     getValorReducaoUnicoImovel().setReadOnly(true);
/* 148 */     getPercentualReducaoBemPequenoValor().setReadOnly(true);
/* 149 */     getValorReducaoBemPequenoValor().setReadOnly(true);
/* 150 */     getGanhoCapital5Proporcional().setReadOnly(true);
/* 151 */     getIrrfLei110332004().setReadOnly(true);
/* 152 */     getAliquotaMedia().setReadOnly(true);
/* 153 */     getImpostoDevido().setReadOnly(true);
/* 154 */     getImpostoDevido2().setReadOnly(true);
/* 155 */     getCustoAquisicaoProporcional().setReadOnly(true);
/* 156 */     getImpostoPago().setReadOnly(true);
/* 157 */     getUltimaParcela().setReadOnly(true);
/* 158 */     getDataRecebimento().setReadOnly(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public ParcelaAlienacao obterCopia() {
/* 163 */     ParcelaAlienacao clone = new ParcelaAlienacao();
/* 164 */     clone.getUltimaParcela().setConteudo(getUltimaParcela());
/* 165 */     clone.getDataRecebimento().setConteudo(getDataRecebimento());
/* 166 */     clone.getValorRecebido().setConteudo((Valor)getValorRecebido());
/* 167 */     clone.getPercentualGanho().setConteudo((Valor)getPercentualGanho());
/* 168 */     clone.getCustoAquisicaoTornaProporcional().setConteudo((Valor)getCustoAquisicaoTornaProporcional());
/* 169 */     clone.getGanhoCapital1Proporcional().setConteudo((Valor)getGanhoCapital1Proporcional());
/* 170 */     clone.getPercentualReducaoLei7713().setConteudo(getPercentualReducaoLei7713());
/* 171 */     clone.getValorReducaoLei7713().setConteudo((Valor)getValorReducaoLei7713());
/* 172 */     clone.getGanhoCapital2Proporcional().setConteudo((Valor)getGanhoCapital2Proporcional());
/* 173 */     clone.getPercentualReducaoLei11196FR1().setConteudo(getPercentualReducaoLei11196FR1());
/* 174 */     clone.getValorReducaoLei11196FR1().setConteudo((Valor)getValorReducaoLei11196FR1());
/* 175 */     clone.getGanhoCapital3Proporcional().setConteudo((Valor)getGanhoCapital3Proporcional());
/* 176 */     clone.getPercentualReducaoLei11196FR2().setConteudo(getPercentualReducaoLei11196FR2());
/* 177 */     clone.getValorReducaoLei11196FR2().setConteudo((Valor)getValorReducaoLei11196FR2());
/* 178 */     clone.getGanhoCapital4Proporcional().setConteudo((Valor)getGanhoCapital4Proporcional());
/* 179 */     clone.getPercentualReducaoAplicacaoOutroImovel().setConteudo(getPercentualReducaoAplicacaoOutroImovel());
/* 180 */     clone.getValorReducaoAplicacaoOutroImovel().setConteudo((Valor)getValorReducaoAplicacaoOutroImovel());
/* 181 */     clone.getPercentualReducaoUnicoImovel().setConteudo(getPercentualReducaoUnicoImovel());
/* 182 */     clone.getValorReducaoUnicoImovel().setConteudo((Valor)getValorReducaoUnicoImovel());
/* 183 */     clone.getPercentualReducaoBemPequenoValor().setConteudo(getPercentualReducaoBemPequenoValor());
/* 184 */     clone.getValorReducaoBemPequenoValor().setConteudo((Valor)getValorReducaoBemPequenoValor());
/* 185 */     clone.getGanhoCapital5Proporcional().setConteudo((Valor)getGanhoCapital5Proporcional());
/* 186 */     clone.getGanhoCapitalTotal().setConteudo((Valor)getGanhoCapitalTotal());
/* 187 */     clone.getAliquotaMedia().setConteudo(getAliquotaMedia());
/* 188 */     clone.getImpostoDevido().setConteudo((Valor)getImpostoDevido());
/* 189 */     clone.getImpostoPago().setConteudo((Valor)getImpostoPago());
/* 190 */     clone.getIrrfLei110332004().setConteudo((Valor)getIrrfLei110332004());
/* 191 */     clone.getImpostoDevido2().setConteudo((Valor)getImpostoDevido2());
/* 192 */     clone.getCustoAquisicaoProporcional().setConteudo((Valor)getCustoAquisicaoProporcional());
/* 193 */     return clone;
/*     */   }
/*     */   
/*     */   public void restaurar(ParcelaAlienacao parcela) {
/* 197 */     getUltimaParcela().setConteudo(parcela.getUltimaParcela());
/* 198 */     getDataRecebimento().setConteudo(parcela.getDataRecebimento());
/* 199 */     getValorRecebido().setConteudo((Valor)parcela.getValorRecebido());
/* 200 */     getPercentualGanho().setConteudo((Valor)parcela.getPercentualGanho());
/* 201 */     getCustoAquisicaoTornaProporcional().setConteudo((Valor)parcela.getCustoAquisicaoTornaProporcional());
/* 202 */     getGanhoCapital1Proporcional().setConteudo((Valor)parcela.getGanhoCapital1Proporcional());
/* 203 */     getPercentualReducaoLei7713().setConteudo(parcela.getPercentualReducaoLei7713());
/* 204 */     getValorReducaoLei7713().setConteudo((Valor)parcela.getValorReducaoLei7713());
/* 205 */     getGanhoCapital2Proporcional().setConteudo((Valor)parcela.getGanhoCapital2Proporcional());
/* 206 */     getPercentualReducaoLei11196FR1().setConteudo(parcela.getPercentualReducaoLei11196FR1());
/* 207 */     getValorReducaoLei11196FR1().setConteudo((Valor)parcela.getValorReducaoLei11196FR1());
/* 208 */     getGanhoCapital3Proporcional().setConteudo((Valor)parcela.getGanhoCapital3Proporcional());
/* 209 */     getPercentualReducaoLei11196FR2().setConteudo(parcela.getPercentualReducaoLei11196FR2());
/* 210 */     getValorReducaoLei11196FR2().setConteudo((Valor)parcela.getValorReducaoLei11196FR2());
/* 211 */     getGanhoCapital4Proporcional().setConteudo((Valor)parcela.getGanhoCapital4Proporcional());
/* 212 */     getPercentualReducaoAplicacaoOutroImovel().setConteudo(parcela.getPercentualReducaoAplicacaoOutroImovel());
/* 213 */     getValorReducaoAplicacaoOutroImovel().setConteudo((Valor)parcela.getValorReducaoAplicacaoOutroImovel());
/* 214 */     getPercentualReducaoUnicoImovel().setConteudo(parcela.getPercentualReducaoUnicoImovel());
/* 215 */     getValorReducaoUnicoImovel().setConteudo((Valor)parcela.getValorReducaoUnicoImovel());
/* 216 */     getPercentualReducaoBemPequenoValor().setConteudo(parcela.getPercentualReducaoBemPequenoValor());
/* 217 */     getValorReducaoBemPequenoValor().setConteudo((Valor)parcela.getValorReducaoBemPequenoValor());
/* 218 */     getGanhoCapital5Proporcional().setConteudo((Valor)parcela.getGanhoCapital5Proporcional());
/* 219 */     getGanhoCapitalTotal().setConteudo((Valor)parcela.getGanhoCapitalTotal());
/* 220 */     getAliquotaMedia().setConteudo(parcela.getAliquotaMedia());
/* 221 */     getImpostoDevido().setConteudo((Valor)parcela.getImpostoDevido());
/* 222 */     getImpostoPago().setConteudo((Valor)parcela.getImpostoPago());
/* 223 */     getIrrfLei110332004().setConteudo((Valor)parcela.getIrrfLei110332004());
/* 224 */     getImpostoDevido2().setConteudo((Valor)parcela.getImpostoDevido2());
/* 225 */     getCustoAquisicaoProporcional().setConteudo((Valor)parcela.getCustoAquisicaoProporcional());
/*     */   }
/*     */   
/*     */   public ValorPositivo getImpostoDevido2() {
/* 229 */     return this.impostoDevido2;
/*     */   }
/*     */   
/*     */   public String getImpostoDevido2Impressao() {
/* 233 */     return this.impostoDevido2.formatado();
/*     */   }
/*     */   
/*     */   public ValorPositivo getIrrfLei110332004() {
/* 237 */     return this.irrfLei110332004;
/*     */   }
/*     */   
/*     */   public String getIrrfLei110332004Impressao() {
/* 241 */     return this.irrfLei110332004.formatado();
/*     */   }
/*     */   
/*     */   public Logico getUltimaParcela() {
/* 245 */     return this.ultimaParcela;
/*     */   }
/*     */   
/*     */   public Data getDataRecebimento() {
/* 249 */     return this.dataRecebimento;
/*     */   }
/*     */   
/*     */   public String getDataRecebimentoImpressao() {
/* 253 */     return this.dataRecebimento.isVazio() ? "" : this.dataRecebimento.formatado();
/*     */   }
/*     */   
/*     */   public ValorPositivo getGanhoCapitalTotal() {
/* 257 */     return this.ganhoCapitalTotal;
/*     */   }
/*     */   
/*     */   public String getGanhoCapitalTotalImpressao() {
/* 261 */     return this.ganhoCapitalTotal.formatado();
/*     */   }
/*     */   
/*     */   public ValorBigDecimalGCME getAliquotaMedia() {
/* 265 */     return this.aliquotaMedia;
/*     */   }
/*     */   
/*     */   public String getAliquotaMediaImpressao() {
/* 269 */     return this.aliquotaMedia.formatado();
/*     */   }
/*     */   
/*     */   public ValorPositivo getImpostoDevido() {
/* 273 */     return this.impostoDevido;
/*     */   }
/*     */   
/*     */   public String getImpostoDevidoImpressao() {
/* 277 */     return this.impostoDevido.formatado();
/*     */   }
/*     */   
/*     */   public ValorPositivo getImpostoPago() {
/* 281 */     return this.impostoPago;
/*     */   }
/*     */   
/*     */   public String getImpostoPagoImpressao() {
/* 285 */     return this.impostoPago.formatado();
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorRecebido() {
/* 289 */     return this.valorRecebido;
/*     */   }
/*     */   
/*     */   public String getValorRecebidoImpressao() {
/* 293 */     return this.valorRecebido.formatado();
/*     */   }
/*     */   
/*     */   public ValorPositivo getPercentualGanho() {
/* 297 */     return this.percentualGanho;
/*     */   }
/*     */   
/*     */   public String getPercentualGanhoImpressao() {
/* 301 */     return this.percentualGanho.formatado();
/*     */   }
/*     */   
/*     */   public ValorPositivo getGanhoCapital1Proporcional() {
/* 305 */     return this.ganhoCapital1Proporcional;
/*     */   }
/*     */   
/*     */   public ValorPositivo getGanhoCapital2Proporcional() {
/* 309 */     return this.ganhoCapital2Proporcional;
/*     */   }
/*     */   
/*     */   public ValorPositivo getGanhoCapital3Proporcional() {
/* 313 */     return this.ganhoCapital3Proporcional;
/*     */   }
/*     */   
/*     */   public ValorPositivo getGanhoCapital4Proporcional() {
/* 317 */     return this.ganhoCapital4Proporcional;
/*     */   }
/*     */   
/*     */   public ValorPositivo getGanhoCapital5Proporcional() {
/* 321 */     return this.ganhoCapital5Proporcional;
/*     */   }
/*     */   
/*     */   public String getGanhoProporcionalImpressao() {
/* 325 */     return this.ganhoCapital1Proporcional.formatado();
/*     */   }
/*     */   
/*     */   public boolean isUltimaParcela() {
/* 329 */     return Logico.SIM.equals(getUltimaParcela().naoFormatado());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVazio() {
/* 334 */     return (getDataRecebimento().isVazio() && getValorRecebido().isVazio());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getCustoCorretagem() {
/* 341 */     return this.custoCorretagem;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getValorLiquidoAlienacao() {
/* 348 */     return this.valorLiquidoAlienacao;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getCustoAquisicaoProporcional() {
/* 355 */     return this.custoAquisicaoProporcional;
/*     */   }
/*     */   
/*     */   public Alfa getMesRecebimentoAux() {
/* 359 */     return this.mesRecebimentoAux;
/*     */   }
/*     */   
/*     */   public boolean getEmReordenacao() {
/* 363 */     return this.emReordenacao;
/*     */   }
/*     */   
/*     */   public void setEmReordenacao(boolean emReordenacao) {
/* 367 */     this.emReordenacao = emReordenacao;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getCustoAquisicaoTornaProporcional() {
/* 374 */     return this.custoAquisicaoTornaProporcional;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorBigDecimalGCME getPercentualReducaoLei7713() {
/* 381 */     return this.percentualReducaoLei7713;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getValorReducaoLei7713() {
/* 388 */     return this.valorReducaoLei7713;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorBigDecimalGCME getPercentualReducaoLei11196FR1() {
/* 395 */     return this.percentualReducaoLei11196FR1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getValorReducaoLei11196FR1() {
/* 402 */     return this.valorReducaoLei11196FR1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorBigDecimalGCME getPercentualReducaoLei11196FR2() {
/* 409 */     return this.percentualReducaoLei11196FR2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getValorReducaoLei11196FR2() {
/* 416 */     return this.valorReducaoLei11196FR2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getValorInformadoReducaoAplicacaoOutroImovel() {
/* 423 */     return this.valorInformadoReducaoAplicacaoOutroImovel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorBigDecimalGCME getPercentualReducaoAplicacaoOutroImovel() {
/* 430 */     return this.percentualReducaoAplicacaoOutroImovel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getValorReducaoAplicacaoOutroImovel() {
/* 437 */     return this.valorReducaoAplicacaoOutroImovel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorBigDecimalGCME getPercentualReducaoUnicoImovel() {
/* 444 */     return this.percentualReducaoUnicoImovel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getValorReducaoUnicoImovel() {
/* 451 */     return this.valorReducaoUnicoImovel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorBigDecimalGCME getPercentualReducaoBemPequenoValor() {
/* 458 */     return this.percentualReducaoBemPequenoValor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getValorReducaoBemPequenoValor() {
/* 465 */     return this.valorReducaoBemPequenoValor;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\alienacao\ParcelaAlienacao.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */