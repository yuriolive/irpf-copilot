/*     */ package serpro.ppgd.irpf.gcap.alienacao;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.irpf.gcap.ValorBigDecimalGCME;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.Logico;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ public class ParcelaAlienacaoBem
/*     */   extends ParcelaAlienacao
/*     */ {
/*  14 */   private ValorPositivo valorRecebidoDolar = new ValorPositivo(this, "Valor recebido (US$)");
/*     */ 
/*     */   
/*  17 */   private ValorPositivo valorRecebidoReal = new ValorPositivo(this, "Valor recebido (R$)");
/*     */   
/*  19 */   private ValorPositivo custoCorretagemDolar = new ValorPositivo(this, "Custo da Corretagem (US$)");
/*     */   
/*  21 */   private ValorPositivo valorLiquidoAlienacaoDolar = new ValorPositivo(this, "Valor Líquido da Alienação - (US$)");
/*     */   
/*  23 */   private ValorPositivo valorLiquidoAlienacaoReal = new ValorPositivo(this, "Valor Líquido da Alienação - (R$)");
/*     */   
/*  25 */   private ValorPositivo valorAlienacaoOrigemNacionalDolar = new ValorPositivo(this, "Valor da Alienação Proporcional - Rendimentos em Reais (US$)");
/*     */   
/*  27 */   private ValorPositivo valorAlienacaoOrigemNacionalReal = new ValorPositivo(this, "Valor da Alienação Proporcional - Rendimentos em Reais (R$)");
/*     */   
/*  29 */   private ValorBigDecimalGCME percentualGanhoOrigemNacional = new ValorBigDecimalGCME(this, "% Ganho sobre Valor Alienação - Rendimentos em Reais", 11, 6);
/*     */   
/*  31 */   private ValorPositivo ganhoCapital1ProporcionalOrigemNacionalReal = new ValorPositivo(this, "Ganho de Capital Proporcional - Rendimentos em Reais (R$)");
/*     */   
/*  33 */   private ValorPositivo valorAlienacaoOrigemMEDolar = new ValorPositivo(this, "Valor da Alienação Proporcional - Rendimentos em ME (US$)");
/*     */   
/*  35 */   private ValorBigDecimalGCME percentualGanhoOrigemME = new ValorBigDecimalGCME(this, "% Ganho sobre Valor Alienação - Rendimentos em ME", 11, 6);
/*     */   
/*  37 */   private ValorPositivo ganhoCapital1ProporcionalOrigemMEDolar = new ValorPositivo(this, "Ganho de Capital Proporcional - Rendimentos ME (US$)");
/*     */   
/*  39 */   private ValorPositivo ganhoCapital1ProporcionalOrigemMEReal = new ValorPositivo(this, "Ganho de Capital Proporcional - Rendimentos ME (R$)");
/*     */   
/*  41 */   private ValorPositivo cotacaoDolar = new ValorPositivo(this, "Cotação do Dólar na Data do Recebimento da Parcela", 11, 4);
/*     */   
/*  43 */   private Valor valorAjuste = new Valor(this, "Valor de Ajuste");
/*     */   
/*  45 */   private ValorPositivo impostoPagoExterior = new ValorPositivo(this, "Imposto Pago no Exterior Passível de Compensação");
/*     */   
/*  47 */   private ValorPositivo custoAquisicaoProporcionalOrigemNacionalReal = new ValorPositivo(this, "Custo de Aquisição Proporcional Rendimentos em Reais (R$)");
/*     */   
/*  49 */   private ValorPositivo custoAquisicaoTornaProporcionalOrigemNacionalReal = new ValorPositivo(this, "Custo de Aquisição Torna Proporcional Rendimentos em Reais (R$)");
/*     */   
/*  51 */   private ValorPositivo custoAquisicaoProporcionalOrigemMEDolar = new ValorPositivo(this, "Custo de Aquisição Proporcional - Rendimentos ME (US$)");
/*     */   
/*  53 */   private ValorPositivo custoAquisicaoTornaProporcionalOrigemMEDolar = new ValorPositivo(this, "Custo de Aquisição Torna Proporcional - Rendimentos ME (US$)");
/*     */ 
/*     */   
/*  56 */   private ValorPositivo ganhoCapital1ProporcionalTotalReal = new ValorPositivo(this, "Ganho de Capital 1 Proporcional Total - (R$)");
/*     */   
/*  58 */   private ValorBigDecimalGCME percentualReducaoLei7713MN = new ValorBigDecimalGCME(this, "Percentual de redução (lei 7.713, de 1988)", 3, 6);
/*     */   
/*  60 */   private ValorPositivo valorReducaoLei7713MN = new ValorPositivo(this, "Valor de redução (lei 7.713, de 1988) - (R$)");
/*     */   
/*  62 */   private ValorPositivo ganhoCapital2ProporcionalMN = new ValorPositivo(this, "Ganho de Capital Proporcional MN - Resultado 2");
/*     */   
/*  64 */   private ValorBigDecimalGCME percentualReducaoLei11196FR1MN = new ValorBigDecimalGCME(this, "Percentual de redução (lei 11.196, de 2005) - FR1", 3, 6);
/*     */   
/*  66 */   private ValorPositivo valorReducaoLei11196FR1MN = new ValorPositivo(this, "Valor de redução (lei 7.713, de 1988) - FR1 - (R$)");
/*     */   
/*  68 */   private ValorPositivo ganhoCapital3ProporcionalMN = new ValorPositivo(this, "Ganho de Capital Proporcional MN - Resultado 3");
/*     */   
/*  70 */   private ValorBigDecimalGCME percentualReducaoLei11196FR2MN = new ValorBigDecimalGCME(this, "Percentual de redução (lei 11.196, de 2005) - FR2", 3, 6);
/*     */   
/*  72 */   private ValorPositivo valorReducaoLei11196FR2MN = new ValorPositivo(this, "Valor de redução (lei 7.713, de 1988) - FR2 - (R$)");
/*     */   
/*  74 */   private ValorPositivo ganhoCapital4ProporcionalMN = new ValorPositivo(this, "Ganho de Capital Proporcional MN - Resultado 4");
/*     */   
/*  76 */   private ValorPositivo valorInformadoReducaoAplicacaoOutroImovelMN = new ValorPositivo(this, "Valor de redução - Aplicação em outro imóvel - (R$)");
/*     */   
/*  78 */   private ValorBigDecimalGCME percentualReducaoAplicacaoOutroImovelMN = new ValorBigDecimalGCME(this, "Percentual de redução - Aplicação em outro imóvel", 3, 6);
/*     */   
/*  80 */   private ValorPositivo valorReducaoAplicacaoOutroImovelMN = new ValorPositivo(this, "Valor calculado de redução - Aplicação em outro imóvel - (R$)");
/*     */   
/*  82 */   private ValorBigDecimalGCME percentualReducaoUnicoImovelMN = new ValorBigDecimalGCME(this, "Percentual de redução - Bem de pequeno valor", 3, 6);
/*     */   
/*  84 */   private ValorPositivo valorReducaoUnicoImovelMN = new ValorPositivo(this, "Valor de redução - Bem de pequeno valor - (R$)");
/*     */   
/*  86 */   private ValorBigDecimalGCME percentualReducaoBemPequenoValorMN = new ValorBigDecimalGCME(this, "Percentual de redução - Bem de pequeno valor", 3, 6);
/*     */   
/*  88 */   private ValorPositivo valorReducaoBemPequenoValorMN = new ValorPositivo(this, "Valor de redução - Bem de pequeno valor - (R$)");
/*     */   
/*  90 */   private ValorPositivo ganhoCapital5ProporcionalMN = new ValorPositivo(this, "Ganho de Capital Proporcional MN - Resultado 5");
/*     */   
/*  92 */   private ValorBigDecimalGCME percentualReducaoLei7713ME = new ValorBigDecimalGCME(this, "Percentual de redução (lei 7.713, de 1988)", 3, 6);
/*     */   
/*  94 */   private ValorPositivo valorReducaoLei7713ME = new ValorPositivo(this, "Valor de redução (lei 7.713, de 1988) - (R$)");
/*     */   
/*  96 */   private ValorPositivo ganhoCapital2ProporcionalME = new ValorPositivo(this, "Ganho de Capital Proporcional ME - Resultado 2");
/*     */   
/*  98 */   private ValorBigDecimalGCME percentualReducaoLei11196FR1ME = new ValorBigDecimalGCME(this, "Percentual de redução (lei 11.196, de 2005) - FR1", 3, 6);
/*     */   
/* 100 */   private ValorPositivo valorReducaoLei11196FR1ME = new ValorPositivo(this, "Valor de redução (lei 7.713, de 1988) - FR1 - (R$)");
/*     */   
/* 102 */   private ValorPositivo ganhoCapital3ProporcionalME = new ValorPositivo(this, "Ganho de Capital Proporcional ME - Resultado 3");
/*     */   
/* 104 */   private ValorBigDecimalGCME percentualReducaoLei11196FR2ME = new ValorBigDecimalGCME(this, "Percentual de redução (lei 11.196, de 2005) - FR2", 3, 6);
/*     */   
/* 106 */   private ValorPositivo valorReducaoLei11196FR2ME = new ValorPositivo(this, "Valor de redução (lei 7.713, de 1988) - FR2 - (R$)");
/*     */   
/* 108 */   private ValorPositivo ganhoCapital4ProporcionalME = new ValorPositivo(this, "Ganho de Capital Proporcional ME - Resultado 4");
/*     */   
/* 110 */   private ValorPositivo valorInformadoReducaoAplicacaoOutroImovelME = new ValorPositivo(this, "Valor de redução - Aplicação em outro imóvel - (R$)");
/*     */   
/* 112 */   private ValorBigDecimalGCME percentualReducaoAplicacaoOutroImovelME = new ValorBigDecimalGCME(this, "Percentual de redução - Aplicação em outro imóvel", 3, 6);
/*     */   
/* 114 */   private ValorPositivo valorReducaoAplicacaoOutroImovelME = new ValorPositivo(this, "Valor calculado de redução - Aplicação em outro imóvel - (R$)");
/*     */   
/* 116 */   private ValorBigDecimalGCME percentualReducaoUnicoImovelME = new ValorBigDecimalGCME(this, "Percentual de redução - Bem de pequeno valor", 3, 6);
/*     */   
/* 118 */   private ValorPositivo valorReducaoUnicoImovelME = new ValorPositivo(this, "Valor de redução - Bem de pequeno valor - (R$)");
/*     */   
/* 120 */   private ValorBigDecimalGCME percentualReducaoBemPequenoValorME = new ValorBigDecimalGCME(this, "Percentual de redução - Bem de pequeno valor", 3, 6);
/*     */   
/* 122 */   private ValorPositivo valorReducaoBemPequenoValorME = new ValorPositivo(this, "Valor de redução - Bem de pequeno valor - (R$)");
/*     */   
/* 124 */   private ValorPositivo ganhoCapital5ProporcionalME = new ValorPositivo(this, "Ganho de Capital Proporcional ME - Resultado 5");
/*     */   
/* 126 */   private ValorPositivo valorInformadoReducaoAplicacaoOutroImovelAmbas = new ValorPositivo(this, "Valor de redução - Aplicação em outro imóvel - (R$)");
/*     */   
/* 128 */   private ValorPositivo ganhoCapital5ProporcionalTotalReal = new ValorPositivo(this, "Ganho de Capital 5 Proporcional Total - (R$)");
/*     */   
/*     */   public ParcelaAlienacaoBem() {
/* 131 */     getUltimaParcela().addOpcao(Logico.SIM, Logico.LABEL_SIM);
/* 132 */     getUltimaParcela().addOpcao(Logico.NAO, Logico.LABEL_NAO);
/* 133 */     getValorLiquidoAlienacao().setReadOnly(true);
/* 134 */     getPercentualGanho().setReadOnly(true);
/* 135 */     getGanhoCapital1Proporcional().setReadOnly(true);
/* 136 */     getAliquotaMedia().setReadOnly(true);
/* 137 */     getImpostoDevido().setReadOnly(true);
/* 138 */     this.percentualGanhoOrigemNacional.setReadOnly(true);
/* 139 */     this.percentualGanhoOrigemME.setReadOnly(true);
/* 140 */     this.valorRecebidoDolar.setReadOnly(true);
/* 141 */     this.custoCorretagemDolar.setReadOnly(true);
/* 142 */     this.cotacaoDolar.setReadOnly(true);
/* 143 */     getValorInformadoReducaoAplicacaoOutroImovel().setReadOnly(true);
/* 144 */     this.valorInformadoReducaoAplicacaoOutroImovelMN.setReadOnly(true);
/* 145 */     this.valorInformadoReducaoAplicacaoOutroImovelME.setReadOnly(true);
/* 146 */     this.valorInformadoReducaoAplicacaoOutroImovelAmbas.setReadOnly(true);
/* 147 */     this.impostoPagoExterior.setReadOnly(true);
/* 148 */     this.valorLiquidoAlienacaoDolar.setReadOnly(true);
/* 149 */     this.valorLiquidoAlienacaoReal.setReadOnly(true);
/* 150 */     this.ganhoCapital1ProporcionalOrigemNacionalReal.setReadOnly(true);
/* 151 */     this.ganhoCapital1ProporcionalOrigemMEReal.setReadOnly(true);
/* 152 */     this.ganhoCapital1ProporcionalOrigemMEDolar.setReadOnly(true);
/* 153 */     this.valorAlienacaoOrigemNacionalDolar.setReadOnly(true);
/* 154 */     this.valorAlienacaoOrigemMEDolar.setReadOnly(true);
/* 155 */     this.valorAlienacaoOrigemNacionalReal.setReadOnly(true);
/* 156 */     this.custoAquisicaoProporcionalOrigemNacionalReal.setReadOnly(true);
/* 157 */     this.custoAquisicaoTornaProporcionalOrigemNacionalReal.setReadOnly(true);
/* 158 */     this.custoAquisicaoProporcionalOrigemMEDolar.setReadOnly(true);
/* 159 */     this.custoAquisicaoTornaProporcionalOrigemMEDolar.setReadOnly(true);
/* 160 */     this.ganhoCapital1ProporcionalTotalReal.setReadOnly(true);
/* 161 */     this.percentualReducaoLei7713MN.setReadOnly(true);
/* 162 */     this.valorReducaoLei7713MN.setReadOnly(true);
/* 163 */     this.ganhoCapital2ProporcionalMN.setReadOnly(true);
/* 164 */     this.percentualReducaoLei11196FR1MN.setReadOnly(true);
/* 165 */     this.valorReducaoLei11196FR1MN.setReadOnly(true);
/* 166 */     this.ganhoCapital3ProporcionalMN.setReadOnly(true);
/* 167 */     this.percentualReducaoLei11196FR2MN.setReadOnly(true);
/* 168 */     this.valorReducaoLei11196FR2MN.setReadOnly(true);
/* 169 */     this.ganhoCapital4ProporcionalMN.setReadOnly(true);
/* 170 */     this.percentualReducaoAplicacaoOutroImovelMN.setReadOnly(true);
/* 171 */     this.valorReducaoAplicacaoOutroImovelMN.setReadOnly(true);
/* 172 */     this.percentualReducaoUnicoImovelMN.setReadOnly(true);
/* 173 */     this.valorReducaoUnicoImovelMN.setReadOnly(true);
/* 174 */     this.percentualReducaoBemPequenoValorMN.setReadOnly(true);
/* 175 */     this.valorReducaoBemPequenoValorMN.setReadOnly(true);
/* 176 */     this.ganhoCapital5ProporcionalMN.setReadOnly(true);
/* 177 */     this.percentualReducaoLei7713ME.setReadOnly(true);
/* 178 */     this.valorReducaoLei7713ME.setReadOnly(true);
/* 179 */     this.ganhoCapital2ProporcionalME.setReadOnly(true);
/* 180 */     this.percentualReducaoLei11196FR1ME.setReadOnly(true);
/* 181 */     this.valorReducaoLei11196FR1ME.setReadOnly(true);
/* 182 */     this.ganhoCapital3ProporcionalME.setReadOnly(true);
/* 183 */     this.percentualReducaoLei11196FR2ME.setReadOnly(true);
/* 184 */     this.valorReducaoLei11196FR2ME.setReadOnly(true);
/* 185 */     this.ganhoCapital4ProporcionalME.setReadOnly(true);
/* 186 */     this.percentualReducaoAplicacaoOutroImovelME.setReadOnly(true);
/* 187 */     this.valorReducaoAplicacaoOutroImovelME.setReadOnly(true);
/* 188 */     this.percentualReducaoUnicoImovelME.setReadOnly(true);
/* 189 */     this.valorReducaoUnicoImovelME.setReadOnly(true);
/* 190 */     this.percentualReducaoBemPequenoValorME.setReadOnly(true);
/* 191 */     this.valorReducaoBemPequenoValorME.setReadOnly(true);
/* 192 */     this.ganhoCapital5ProporcionalME.setReadOnly(true);
/* 193 */     this.ganhoCapital5ProporcionalTotalReal.setReadOnly(true);
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorRecebidoDolar() {
/* 197 */     return this.valorRecebidoDolar;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorRecebidoReal() {
/* 201 */     return this.valorRecebidoReal;
/*     */   }
/*     */   
/*     */   public ValorPositivo getCustoCorretagemDolar() {
/* 205 */     return this.custoCorretagemDolar;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorLiquidoAlienacaoDolar() {
/* 209 */     return this.valorLiquidoAlienacaoDolar;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorLiquidoAlienacaoReal() {
/* 213 */     return this.valorLiquidoAlienacaoReal;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorAlienacaoOrigemNacionalDolar() {
/* 217 */     return this.valorAlienacaoOrigemNacionalDolar;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorAlienacaoOrigemNacionalReal() {
/* 221 */     return this.valorAlienacaoOrigemNacionalReal;
/*     */   }
/*     */   
/*     */   public ValorBigDecimalGCME getPercentualGanhoOrigemNacional() {
/* 225 */     return this.percentualGanhoOrigemNacional;
/*     */   }
/*     */   
/*     */   public ValorPositivo getGanhoCapital1ProporcionalOrigemNacionalReal() {
/* 229 */     return this.ganhoCapital1ProporcionalOrigemNacionalReal;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorAlienacaoOrigemMEDolar() {
/* 233 */     return this.valorAlienacaoOrigemMEDolar;
/*     */   }
/*     */   
/*     */   public ValorBigDecimalGCME getPercentualGanhoOrigemME() {
/* 237 */     return this.percentualGanhoOrigemME;
/*     */   }
/*     */   
/*     */   public ValorPositivo getGanhoCapital1ProporcionalOrigemMEDolar() {
/* 241 */     return this.ganhoCapital1ProporcionalOrigemMEDolar;
/*     */   }
/*     */   
/*     */   public ValorPositivo getGanhoCapital1ProporcionalOrigemMEReal() {
/* 245 */     return this.ganhoCapital1ProporcionalOrigemMEReal;
/*     */   }
/*     */   
/*     */   public ValorPositivo getCotacaoDolar() {
/* 249 */     return this.cotacaoDolar;
/*     */   }
/*     */   
/*     */   public Valor getValorAjuste() {
/* 253 */     return this.valorAjuste;
/*     */   }
/*     */   
/*     */   public ValorPositivo getImpostoPagoExterior() {
/* 257 */     return this.impostoPagoExterior;
/*     */   }
/*     */   
/*     */   public boolean isUltimaParcela() {
/* 261 */     return Logico.SIM.equals(getUltimaParcela().naoFormatado());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVazio() {
/* 266 */     return (getDataRecebimento().isVazio() && getValorRecebido().isVazio() && getValorRecebidoDolar().isVazio());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getCustoAquisicaoProporcionalOrigemNacionalReal() {
/* 273 */     return this.custoAquisicaoProporcionalOrigemNacionalReal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getCustoAquisicaoTornaProporcionalOrigemNacionalReal() {
/* 280 */     return this.custoAquisicaoTornaProporcionalOrigemNacionalReal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getCustoAquisicaoProporcionalOrigemMEDolar() {
/* 287 */     return this.custoAquisicaoProporcionalOrigemMEDolar;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getCustoAquisicaoTornaProporcionalOrigemMEDolar() {
/* 294 */     return this.custoAquisicaoTornaProporcionalOrigemMEDolar;
/*     */   }
/*     */   
/*     */   public List<Informacao> recuperarListaCamposPendenciaAbaCalculo() {
/* 298 */     List<Informacao> campos = new ArrayList<>();
/* 299 */     campos.add(getUltimaParcela());
/* 300 */     campos.add(getDataRecebimento());
/* 301 */     campos.add(getValorRecebido());
/* 302 */     campos.add(getValorRecebidoDolar());
/* 303 */     campos.add(getCustoCorretagem());
/* 304 */     campos.add(getCustoCorretagemDolar());
/* 305 */     campos.add(getCotacaoDolar());
/* 306 */     campos.add(getValorInformadoReducaoAplicacaoOutroImovel());
/* 307 */     campos.add(getValorInformadoReducaoAplicacaoOutroImovelMN());
/* 308 */     campos.add(getValorInformadoReducaoAplicacaoOutroImovelME());
/* 309 */     campos.add(getImpostoPagoExterior());
/*     */ 
/*     */     
/* 312 */     return campos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getGanhoCapital1ProporcionalTotalReal() {
/* 319 */     return this.ganhoCapital1ProporcionalTotalReal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorBigDecimalGCME getPercentualReducaoLei7713MN() {
/* 326 */     return this.percentualReducaoLei7713MN;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getValorReducaoLei7713MN() {
/* 333 */     return this.valorReducaoLei7713MN;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getGanhoCapital2ProporcionalMN() {
/* 340 */     return this.ganhoCapital2ProporcionalMN;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorBigDecimalGCME getPercentualReducaoLei11196FR1MN() {
/* 347 */     return this.percentualReducaoLei11196FR1MN;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getValorReducaoLei11196FR1MN() {
/* 354 */     return this.valorReducaoLei11196FR1MN;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getGanhoCapital3ProporcionalMN() {
/* 361 */     return this.ganhoCapital3ProporcionalMN;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorBigDecimalGCME getPercentualReducaoLei11196FR2MN() {
/* 368 */     return this.percentualReducaoLei11196FR2MN;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getValorReducaoLei11196FR2MN() {
/* 375 */     return this.valorReducaoLei11196FR2MN;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getGanhoCapital4ProporcionalMN() {
/* 382 */     return this.ganhoCapital4ProporcionalMN;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getValorInformadoReducaoAplicacaoOutroImovelMN() {
/* 389 */     return this.valorInformadoReducaoAplicacaoOutroImovelMN;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorBigDecimalGCME getPercentualReducaoAplicacaoOutroImovelMN() {
/* 396 */     return this.percentualReducaoAplicacaoOutroImovelMN;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getValorReducaoAplicacaoOutroImovelMN() {
/* 403 */     return this.valorReducaoAplicacaoOutroImovelMN;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorBigDecimalGCME getPercentualReducaoUnicoImovelMN() {
/* 410 */     return this.percentualReducaoUnicoImovelMN;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getValorReducaoUnicoImovelMN() {
/* 417 */     return this.valorReducaoUnicoImovelMN;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorBigDecimalGCME getPercentualReducaoBemPequenoValorMN() {
/* 424 */     return this.percentualReducaoBemPequenoValorMN;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getValorReducaoBemPequenoValorMN() {
/* 431 */     return this.valorReducaoBemPequenoValorMN;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getGanhoCapital5ProporcionalMN() {
/* 438 */     return this.ganhoCapital5ProporcionalMN;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorBigDecimalGCME getPercentualReducaoLei7713ME() {
/* 445 */     return this.percentualReducaoLei7713ME;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getValorReducaoLei7713ME() {
/* 452 */     return this.valorReducaoLei7713ME;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getGanhoCapital2ProporcionalME() {
/* 459 */     return this.ganhoCapital2ProporcionalME;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorBigDecimalGCME getPercentualReducaoLei11196FR1ME() {
/* 466 */     return this.percentualReducaoLei11196FR1ME;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getValorReducaoLei11196FR1ME() {
/* 473 */     return this.valorReducaoLei11196FR1ME;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getGanhoCapital3ProporcionalME() {
/* 480 */     return this.ganhoCapital3ProporcionalME;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorBigDecimalGCME getPercentualReducaoLei11196FR2ME() {
/* 487 */     return this.percentualReducaoLei11196FR2ME;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getValorReducaoLei11196FR2ME() {
/* 494 */     return this.valorReducaoLei11196FR2ME;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getGanhoCapital4ProporcionalME() {
/* 501 */     return this.ganhoCapital4ProporcionalME;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getValorInformadoReducaoAplicacaoOutroImovelME() {
/* 508 */     return this.valorInformadoReducaoAplicacaoOutroImovelME;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorBigDecimalGCME getPercentualReducaoAplicacaoOutroImovelME() {
/* 515 */     return this.percentualReducaoAplicacaoOutroImovelME;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getValorReducaoAplicacaoOutroImovelME() {
/* 522 */     return this.valorReducaoAplicacaoOutroImovelME;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorBigDecimalGCME getPercentualReducaoUnicoImovelME() {
/* 529 */     return this.percentualReducaoUnicoImovelME;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getValorReducaoUnicoImovelME() {
/* 536 */     return this.valorReducaoUnicoImovelME;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorBigDecimalGCME getPercentualReducaoBemPequenoValorME() {
/* 543 */     return this.percentualReducaoBemPequenoValorME;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getValorReducaoBemPequenoValorME() {
/* 550 */     return this.valorReducaoBemPequenoValorME;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getGanhoCapital5ProporcionalME() {
/* 557 */     return this.ganhoCapital5ProporcionalME;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getValorInformadoReducaoAplicacaoOutroImovelAmbas() {
/* 564 */     return this.valorInformadoReducaoAplicacaoOutroImovelAmbas;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getGanhoCapital5ProporcionalTotalReal() {
/* 571 */     return this.ganhoCapital5ProporcionalTotalReal;
/*     */   }
/*     */   
/*     */   public ValorPositivo obterSomatorioReducoes() {
/* 575 */     ValorPositivo somatorio = new ValorPositivo();
/* 576 */     somatorio.clear();
/* 577 */     somatorio.append('+', (Valor)getValorReducaoLei7713());
/* 578 */     somatorio.append('+', (Valor)getValorReducaoLei11196FR1());
/* 579 */     somatorio.append('+', (Valor)getValorReducaoLei11196FR2());
/* 580 */     somatorio.append('+', (Valor)getValorReducaoBemPequenoValor());
/* 581 */     somatorio.append('+', (Valor)getValorReducaoUnicoImovel());
/* 582 */     somatorio.append('+', (Valor)getValorReducaoAplicacaoOutroImovel());
/* 583 */     return somatorio;
/*     */   }
/*     */   
/*     */   public ValorPositivo obterSomatorioReducoesMN() {
/* 587 */     ValorPositivo somatorio = new ValorPositivo();
/* 588 */     somatorio.clear();
/* 589 */     somatorio.append('+', (Valor)getValorReducaoLei7713MN());
/* 590 */     somatorio.append('+', (Valor)getValorReducaoLei11196FR1MN());
/* 591 */     somatorio.append('+', (Valor)getValorReducaoLei11196FR2MN());
/* 592 */     somatorio.append('+', (Valor)getValorReducaoBemPequenoValorMN());
/* 593 */     somatorio.append('+', (Valor)getValorReducaoUnicoImovelMN());
/* 594 */     somatorio.append('+', (Valor)getValorReducaoAplicacaoOutroImovelMN());
/* 595 */     return somatorio;
/*     */   }
/*     */   
/*     */   public ValorPositivo obterSomatorioReducoesME() {
/* 599 */     ValorPositivo somatorio = new ValorPositivo();
/* 600 */     somatorio.clear();
/* 601 */     somatorio.append('+', (Valor)getValorReducaoLei7713ME());
/* 602 */     somatorio.append('+', (Valor)getValorReducaoLei11196FR1ME());
/* 603 */     somatorio.append('+', (Valor)getValorReducaoLei11196FR2ME());
/* 604 */     somatorio.append('+', (Valor)getValorReducaoBemPequenoValorME());
/* 605 */     somatorio.append('+', (Valor)getValorReducaoUnicoImovelME());
/* 606 */     somatorio.append('+', (Valor)getValorReducaoAplicacaoOutroImovelME());
/* 607 */     return somatorio;
/*     */   }
/*     */   
/*     */   public ValorPositivo obterSomatorioReducoesAM() {
/* 611 */     ValorPositivo somatorio = new ValorPositivo();
/* 612 */     somatorio.append('+', (Valor)obterSomatorioReducoesMN());
/* 613 */     somatorio.append('+', (Valor)obterSomatorioReducoesME());
/*     */     
/* 615 */     return somatorio;
/*     */   }
/*     */   
/*     */   public ParcelaAlienacaoBem obterCopia() {
/* 619 */     ParcelaAlienacaoBem clone = new ParcelaAlienacaoBem();
/* 620 */     clone.getUltimaParcela().setConteudo(getUltimaParcela());
/* 621 */     clone.getDataRecebimento().setConteudo(getDataRecebimento());
/* 622 */     clone.getValorRecebido().setConteudo((Valor)getValorRecebido());
/* 623 */     clone.getPercentualGanho().setConteudo((Valor)getPercentualGanho());
/* 624 */     clone.getGanhoCapital1Proporcional().setConteudo((Valor)getGanhoCapital1Proporcional());
/* 625 */     clone.getValorRecebidoDolar().setConteudo((Valor)getValorRecebidoDolar());
/* 626 */     clone.getCustoCorretagemDolar().setConteudo((Valor)getCustoCorretagemDolar());
/* 627 */     clone.getValorLiquidoAlienacaoDolar().setConteudo((Valor)getValorLiquidoAlienacaoDolar());
/* 628 */     clone.getValorAlienacaoOrigemNacionalDolar().setConteudo((Valor)getValorAlienacaoOrigemNacionalDolar());
/* 629 */     clone.getValorAlienacaoOrigemNacionalReal().setConteudo((Valor)getValorAlienacaoOrigemNacionalReal());
/* 630 */     clone.getPercentualGanhoOrigemNacional().setConteudo(getPercentualGanhoOrigemNacional());
/* 631 */     clone.getGanhoCapital1ProporcionalOrigemNacionalReal().setConteudo((Valor)getGanhoCapital1ProporcionalOrigemNacionalReal());
/* 632 */     clone.getValorAlienacaoOrigemMEDolar().setConteudo((Valor)getValorAlienacaoOrigemMEDolar());
/* 633 */     clone.getPercentualGanhoOrigemME().setConteudo(getPercentualGanhoOrigemME());
/* 634 */     clone.getGanhoCapital1ProporcionalOrigemMEDolar().setConteudo((Valor)getGanhoCapital1ProporcionalOrigemMEDolar());
/* 635 */     clone.getGanhoCapital1ProporcionalOrigemMEReal().setConteudo((Valor)getGanhoCapital1ProporcionalOrigemMEReal());
/* 636 */     clone.getGanhoCapitalTotal().setConteudo((Valor)getGanhoCapitalTotal());
/* 637 */     clone.getAliquotaMedia().setConteudo(getAliquotaMedia());
/* 638 */     clone.getCotacaoDolar().setConteudo((Valor)getCotacaoDolar());
/* 639 */     clone.getImpostoDevido().setConteudo((Valor)getImpostoDevido());
/* 640 */     clone.getValorAjuste().setConteudo(getValorAjuste());
/* 641 */     clone.getImpostoPagoExterior().setConteudo((Valor)getImpostoPagoExterior());
/* 642 */     clone.getImpostoDevido2().setConteudo((Valor)getImpostoDevido2());
/* 643 */     clone.getImpostoPago().setConteudo((Valor)getImpostoPago());
/* 644 */     clone.getCustoAquisicaoProporcional().setConteudo((Valor)getCustoAquisicaoProporcional());
/* 645 */     clone.getCustoAquisicaoTornaProporcional().setConteudo((Valor)getCustoAquisicaoTornaProporcional());
/* 646 */     clone.getCustoAquisicaoProporcionalOrigemNacionalReal().setConteudo((Valor)getCustoAquisicaoProporcionalOrigemNacionalReal());
/* 647 */     clone.getCustoAquisicaoTornaProporcionalOrigemNacionalReal().setConteudo((Valor)getCustoAquisicaoTornaProporcionalOrigemNacionalReal());
/* 648 */     clone.getCustoAquisicaoProporcionalOrigemMEDolar().setConteudo((Valor)getCustoAquisicaoProporcionalOrigemMEDolar());
/* 649 */     clone.getCustoAquisicaoTornaProporcionalOrigemMEDolar().setConteudo((Valor)getCustoAquisicaoTornaProporcionalOrigemMEDolar());
/* 650 */     clone.getPercentualReducaoLei7713().setConteudo(getPercentualReducaoLei7713());
/* 651 */     clone.getValorReducaoLei7713().setConteudo((Valor)getValorReducaoLei7713());
/* 652 */     clone.getGanhoCapital2Proporcional().setConteudo((Valor)getGanhoCapital2Proporcional());
/* 653 */     clone.getPercentualReducaoLei11196FR1().setConteudo(getPercentualReducaoLei11196FR1());
/* 654 */     clone.getValorReducaoLei11196FR1().setConteudo((Valor)getValorReducaoLei11196FR1());
/* 655 */     clone.getGanhoCapital3Proporcional().setConteudo((Valor)getGanhoCapital3Proporcional());
/* 656 */     clone.getPercentualReducaoLei11196FR2().setConteudo(getPercentualReducaoLei11196FR2());
/* 657 */     clone.getValorReducaoLei11196FR2().setConteudo((Valor)getValorReducaoLei11196FR2());
/* 658 */     clone.getGanhoCapital4Proporcional().setConteudo((Valor)getGanhoCapital4Proporcional());
/* 659 */     clone.getPercentualReducaoAplicacaoOutroImovel().setConteudo(getPercentualReducaoAplicacaoOutroImovel());
/* 660 */     clone.getValorReducaoAplicacaoOutroImovel().setConteudo((Valor)getValorReducaoAplicacaoOutroImovel());
/* 661 */     clone.getPercentualReducaoUnicoImovel().setConteudo(getPercentualReducaoUnicoImovel());
/* 662 */     clone.getValorReducaoUnicoImovel().setConteudo((Valor)getValorReducaoUnicoImovel());
/* 663 */     clone.getPercentualReducaoBemPequenoValor().setConteudo(getPercentualReducaoBemPequenoValor());
/* 664 */     clone.getValorReducaoBemPequenoValor().setConteudo((Valor)getValorReducaoBemPequenoValor());
/* 665 */     clone.getGanhoCapital5Proporcional().setConteudo((Valor)getGanhoCapital5Proporcional());
/* 666 */     clone.getPercentualReducaoLei7713MN().setConteudo(getPercentualReducaoLei7713MN());
/* 667 */     clone.getValorReducaoLei7713MN().setConteudo((Valor)getValorReducaoLei7713MN());
/* 668 */     clone.getGanhoCapital2ProporcionalMN().setConteudo((Valor)getGanhoCapital2ProporcionalMN());
/* 669 */     clone.getPercentualReducaoLei11196FR1MN().setConteudo(getPercentualReducaoLei11196FR1MN());
/* 670 */     clone.getValorReducaoLei11196FR1MN().setConteudo((Valor)getValorReducaoLei11196FR1MN());
/* 671 */     clone.getGanhoCapital3ProporcionalMN().setConteudo((Valor)getGanhoCapital3ProporcionalMN());
/* 672 */     clone.getPercentualReducaoLei11196FR2MN().setConteudo(getPercentualReducaoLei11196FR2MN());
/* 673 */     clone.getValorReducaoLei11196FR2MN().setConteudo((Valor)getValorReducaoLei11196FR2MN());
/* 674 */     clone.getGanhoCapital4ProporcionalMN().setConteudo((Valor)getGanhoCapital4ProporcionalMN());
/* 675 */     clone.getPercentualReducaoAplicacaoOutroImovelMN().setConteudo(getPercentualReducaoAplicacaoOutroImovelMN());
/* 676 */     clone.getValorReducaoAplicacaoOutroImovelMN().setConteudo((Valor)getValorReducaoAplicacaoOutroImovelMN());
/* 677 */     clone.getPercentualReducaoUnicoImovelMN().setConteudo(getPercentualReducaoUnicoImovelMN());
/* 678 */     clone.getValorReducaoUnicoImovelMN().setConteudo((Valor)getValorReducaoUnicoImovelMN());
/* 679 */     clone.getPercentualReducaoBemPequenoValorMN().setConteudo(getPercentualReducaoBemPequenoValorMN());
/* 680 */     clone.getValorReducaoBemPequenoValorMN().setConteudo((Valor)getValorReducaoBemPequenoValorMN());
/* 681 */     clone.getGanhoCapital5ProporcionalMN().setConteudo((Valor)getGanhoCapital5ProporcionalMN());
/* 682 */     clone.getPercentualReducaoLei7713ME().setConteudo(getPercentualReducaoLei7713ME());
/* 683 */     clone.getValorReducaoLei7713ME().setConteudo((Valor)getValorReducaoLei7713ME());
/* 684 */     clone.getGanhoCapital2ProporcionalME().setConteudo((Valor)getGanhoCapital2ProporcionalME());
/* 685 */     clone.getPercentualReducaoLei11196FR1ME().setConteudo(getPercentualReducaoLei11196FR1ME());
/* 686 */     clone.getValorReducaoLei11196FR1ME().setConteudo((Valor)getValorReducaoLei11196FR1ME());
/* 687 */     clone.getGanhoCapital3ProporcionalME().setConteudo((Valor)getGanhoCapital3ProporcionalME());
/* 688 */     clone.getPercentualReducaoLei11196FR2ME().setConteudo(getPercentualReducaoLei11196FR2ME());
/* 689 */     clone.getValorReducaoLei11196FR2ME().setConteudo((Valor)getValorReducaoLei11196FR2ME());
/* 690 */     clone.getGanhoCapital4ProporcionalME().setConteudo((Valor)getGanhoCapital4ProporcionalME());
/* 691 */     clone.getPercentualReducaoAplicacaoOutroImovelME().setConteudo(getPercentualReducaoAplicacaoOutroImovelME());
/* 692 */     clone.getValorReducaoAplicacaoOutroImovelME().setConteudo((Valor)getValorReducaoAplicacaoOutroImovelME());
/* 693 */     clone.getPercentualReducaoUnicoImovelME().setConteudo(getPercentualReducaoUnicoImovelME());
/* 694 */     clone.getValorReducaoUnicoImovelME().setConteudo((Valor)getValorReducaoUnicoImovelME());
/* 695 */     clone.getPercentualReducaoBemPequenoValorME().setConteudo(getPercentualReducaoBemPequenoValorME());
/* 696 */     clone.getValorReducaoBemPequenoValorME().setConteudo((Valor)getValorReducaoBemPequenoValorME());
/* 697 */     clone.getGanhoCapital5ProporcionalME().setConteudo((Valor)getGanhoCapital5ProporcionalME());
/* 698 */     clone.getGanhoCapital5ProporcionalTotalReal().setConteudo((Valor)getGanhoCapital5ProporcionalTotalReal());
/* 699 */     return clone;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\alienacao\ParcelaAlienacaoBem.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */