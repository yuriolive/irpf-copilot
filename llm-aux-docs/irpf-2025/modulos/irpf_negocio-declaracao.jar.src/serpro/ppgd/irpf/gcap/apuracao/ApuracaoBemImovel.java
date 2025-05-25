/*     */ package serpro.ppgd.irpf.gcap.apuracao;
/*     */ 
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.irpf.gcap.ValorBigDecimalGCME;
/*     */ 
/*     */ public class ApuracaoBemImovel
/*     */   extends ApuracaoBem {
/*   8 */   private ValorPositivo custoAquisicaoTorna = new ValorPositivo(this, "Custo de aquisição da torna (R$)");
/*   9 */   private ValorPositivo custoAquisicaoTornaOrigemMNReal = new ValorPositivo(this, "Custo de aquisição da torna - Origem em Moeda Nacional (R$)");
/*  10 */   private ValorPositivo custoAquisicaoTornaOrigemMEDolar = new ValorPositivo(this, "Custo de aquisição da torna - Origem em Moeda Estrangeira (US$)");
/*     */   
/*  12 */   private ValorBigDecimalGCME percentualReducaoLei7713 = new ValorBigDecimalGCME(this, "Percentual de redução (lei 7.713, de 1988)", 3, 6);
/*  13 */   private ValorBigDecimalGCME percentualReducaoLei7713OrigemMN = new ValorBigDecimalGCME(this, "Percentual de redução (lei 7.713, de 1988) - Origem em Moeda Nacional", 3, 6);
/*  14 */   private ValorBigDecimalGCME percentualReducaoLei7713OrigemME = new ValorBigDecimalGCME(this, "Percentual de redução (lei 7.713, de 1988) - Origem em Moeda Estrangeira", 3, 6);
/*     */   
/*  16 */   private ValorPositivo valorReducaoLei7713 = new ValorPositivo(this, "Valor de redução (lei 7.713, de 1988) - (R$)");
/*  17 */   private ValorPositivo valorReducaoLei7713OrigemMN = new ValorPositivo(this, "Valor de redução (lei 7.713, de 1988) - Origem em Moeda Nacional - (R$)");
/*  18 */   private ValorPositivo valorReducaoLei7713OrigemME = new ValorPositivo(this, "Valor de redução (lei 7.713, de 1988) - Origem em Moeda Estrangeira - (R$)");
/*     */   
/*  20 */   private ValorPositivo ganhoCapital2 = new ValorPositivo(this, "Ganho de capital - Resultado 2 - (R$)");
/*  21 */   private ValorPositivo ganhoCapital2OrigemMNReal = new ValorPositivo(this, "Ganho de capital - Resultado 2 - Origem em Moeda Nacional - (R$)");
/*  22 */   private ValorPositivo ganhoCapital2OrigemMEReal = new ValorPositivo(this, "Ganho de capital - Resultado 2 - Origem em Moeda Estrangeira - (R$)");
/*     */   
/*  24 */   private ValorBigDecimalGCME percentualReducaoLei11196FR1 = new ValorBigDecimalGCME(this, "Percentual de redução (lei 11.196, de 2005) - FR1", 3, 6);
/*  25 */   private ValorBigDecimalGCME percentualReducaoLei11196FR1OrigemMN = new ValorBigDecimalGCME(this, "Percentual de redução (lei 11.196, de 2005) - FR1 - Origem em Moeda Nacional", 3, 6);
/*  26 */   private ValorBigDecimalGCME percentualReducaoLei11196FR1OrigemME = new ValorBigDecimalGCME(this, "Percentual de redução (lei 11.196, de 2005) - FR1 - Origem em Moeda Estrangeira", 3, 6);
/*     */   
/*  28 */   private ValorPositivo valorReducaoLei11196FR1 = new ValorPositivo(this, "Valor de redução (lei 7.713, de 1988) - FR1 - (R$)");
/*  29 */   private ValorPositivo valorReducaoLei11196FR1OrigemMN = new ValorPositivo(this, "Valor de redução (lei 7.713, de 1988) - FR1 - Origem em Moeda Nacional - (R$)");
/*  30 */   private ValorPositivo valorReducaoLei11196FR1OrigemME = new ValorPositivo(this, "Valor de redução (lei 7.713, de 1988) - FR1 - Origem em Moeda Estrangeira - (R$)");
/*     */   
/*  32 */   private ValorPositivo ganhoCapital3 = new ValorPositivo(this, "Ganho de capital - Resultado 3 - (R$)");
/*  33 */   private ValorPositivo ganhoCapital3OrigemMNReal = new ValorPositivo(this, "Ganho de capital - Resultado 3 - Origem em Moeda Nacional - (R$)");
/*  34 */   private ValorPositivo ganhoCapital3OrigemMEReal = new ValorPositivo(this, "Ganho de capital - Resultado 3 - Origem em Moeda Estrangeira - (R$)");
/*     */   
/*  36 */   private ValorBigDecimalGCME percentualReducaoLei11196FR2 = new ValorBigDecimalGCME(this, "Percentual de redução (lei 11.196, de 2005) - FR2", 3, 6);
/*  37 */   private ValorBigDecimalGCME percentualReducaoLei11196FR2OrigemMN = new ValorBigDecimalGCME(this, "Percentual de redução (lei 11.196, de 2005) - FR2 - Origem em Moeda Nacional", 3, 6);
/*  38 */   private ValorBigDecimalGCME percentualReducaoLei11196FR2OrigemME = new ValorBigDecimalGCME(this, "Percentual de redução (lei 11.196, de 2005) - FR2 - Origem em Moeda Estrangeira", 3, 6);
/*     */   
/*  40 */   private ValorPositivo valorReducaoLei11196FR2 = new ValorPositivo(this, "Valor de redução (lei 7.713, de 1988) - FR2 - (R$)");
/*  41 */   private ValorPositivo valorReducaoLei11196FR2OrigemMN = new ValorPositivo(this, "Valor de redução (lei 7.713, de 1988) - FR2 - Origem em Moeda Nacional - (R$)");
/*  42 */   private ValorPositivo valorReducaoLei11196FR2OrigemME = new ValorPositivo(this, "Valor de redução (lei 7.713, de 1988) - FR2 - Origem em Moeda Estrangeira - (R$)");
/*     */   
/*  44 */   private ValorPositivo ganhoCapital4 = new ValorPositivo(this, "Ganho de capital - Resultado 4 - (R$)");
/*  45 */   private ValorPositivo ganhoCapital4OrigemMNReal = new ValorPositivo(this, "Ganho de capital - Resultado 4 - Origem em Moeda Nacional - (R$)");
/*  46 */   private ValorPositivo ganhoCapital4OrigemMEReal = new ValorPositivo(this, "Ganho de capital - Resultado 4 - Origem em Moeda Estrangeira - (R$)");
/*     */   
/*  48 */   private ValorBigDecimalGCME percentualReducaoAplicacaoOutroImovel = new ValorBigDecimalGCME(this, "Percentual de redução - Aplicação em outro imóvel", 3, 6);
/*  49 */   private ValorBigDecimalGCME percentualReducaoAplicacaoOutroImovelOrigemMN = new ValorBigDecimalGCME(this, "Percentual de redução - Aplicação em outro imóvel - Origem em Moeda Nacional", 3, 6);
/*  50 */   private ValorBigDecimalGCME percentualReducaoAplicacaoOutroImovelOrigemME = new ValorBigDecimalGCME(this, "Percentual de redução - Aplicação em outro imóvel - Origem em Moeda Estrangeira", 3, 6);
/*     */   
/*  52 */   private ValorPositivo valorReducaoAplicacaoOutroImovel = new ValorPositivo(this, "Valor de redução - Aplicação em outro imóvel - (R$)");
/*  53 */   private ValorPositivo valorReducaoAplicacaoOutroImovelOrigemMN = new ValorPositivo(this, "Valor de redução - Aplicação em outro imóvel - Origem em Moeda Nacional - (R$)");
/*  54 */   private ValorPositivo valorReducaoAplicacaoOutroImovelOrigemME = new ValorPositivo(this, "Valor de redução - Aplicação em outro imóvel - Origem em Moeda Estrangeira - (R$)");
/*     */   
/*  56 */   private ValorBigDecimalGCME percentualReducaoUnicoImovel = new ValorBigDecimalGCME(this, "Percentual de redução - Bem de pequeno valor", 3, 6);
/*  57 */   private ValorBigDecimalGCME percentualReducaoUnicoImovelOrigemMN = new ValorBigDecimalGCME(this, "Percentual de redução - Bem de pequeno valor - Origem em Moeda Nacional", 3, 6);
/*  58 */   private ValorBigDecimalGCME percentualReducaoUnicoImovelOrigemME = new ValorBigDecimalGCME(this, "Percentual de redução - Bem de pequeno valor - Origem em Moeda Estrangeira", 3, 6);
/*     */   
/*  60 */   private ValorPositivo valorReducaoUnicoImovel = new ValorPositivo(this, "Valor de redução - Bem de pequeno valor - (R$)");
/*  61 */   private ValorPositivo valorReducaoUnicoImovelOrigemMN = new ValorPositivo(this, "Valor de redução - Bem de pequeno valor - Origem em Moeda Nacional - (R$)");
/*  62 */   private ValorPositivo valorReducaoUnicoImovelOrigemME = new ValorPositivo(this, "Valor de redução - Bem de pequeno valor - Origem em Moeda Estrangeira - (R$)");
/*     */   
/*  64 */   private ValorBigDecimalGCME percentualReducaoBemPequenoValor = new ValorBigDecimalGCME(this, "Percentual de redução - Bem de pequeno valor", 3, 6);
/*  65 */   private ValorBigDecimalGCME percentualReducaoBemPequenoValorOrigemMN = new ValorBigDecimalGCME(this, "Percentual de redução - Bem de pequeno valor - Origem em Moeda Nacional", 3, 6);
/*  66 */   private ValorBigDecimalGCME percentualReducaoBemPequenoValorOrigemME = new ValorBigDecimalGCME(this, "Percentual de redução - Bem de pequeno valor - Origem em Moeda Estrangeira", 3, 6);
/*     */   
/*  68 */   private ValorPositivo valorReducaoBemPequenoValor = new ValorPositivo(this, "Valor de redução - Bem de pequeno valor - (R$)");
/*  69 */   private ValorPositivo valorReducaoBemPequenoValorOrigemMN = new ValorPositivo(this, "Valor de redução - Bem de pequeno valor - Origem em Moeda Nacional - (R$)");
/*  70 */   private ValorPositivo valorReducaoBemPequenoValorOrigemME = new ValorPositivo(this, "Valor de redução - Bem de pequeno valor - Origem em Moeda Estrangeira - (R$)");
/*     */   
/*  72 */   private ValorPositivo ganhoCapital5 = new ValorPositivo(this, "Ganho de capital - Resultado 5 - (R$)");
/*  73 */   private ValorPositivo ganhoCapital5OrigemMNReal = new ValorPositivo(this, "Ganho de capital - Resultado 5 - Origem em Moeda Nacional - (R$)");
/*  74 */   private ValorPositivo ganhoCapital5OrigemMEReal = new ValorPositivo(this, "Ganho de capital - Resultado 5 - Origem em Moeda Estrangeira - (R$)");
/*     */   
/*  76 */   private ColecaoParcelaApuracaoCustoAquisicao parcelasCustoAquisicao = new ColecaoParcelaApuracaoCustoAquisicao();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  85 */   private ValorPositivo ganhoCapitalTotalExterior = new ValorPositivo(this, "Ganho de capital total (R$)");
/*     */   
/*     */   public ApuracaoBemImovel() {
/*  88 */     this.custoAquisicaoTorna.setReadOnly(true);
/*  89 */     this.custoAquisicaoTornaOrigemMNReal.setReadOnly(true);
/*  90 */     this.custoAquisicaoTornaOrigemMEDolar.setReadOnly(true);
/*     */     
/*  92 */     this.percentualReducaoLei7713.setReadOnly(true);
/*  93 */     this.percentualReducaoLei7713OrigemMN.setReadOnly(true);
/*  94 */     this.percentualReducaoLei7713OrigemME.setReadOnly(true);
/*     */     
/*  96 */     this.valorReducaoLei7713.setReadOnly(true);
/*  97 */     this.valorReducaoLei7713OrigemMN.setReadOnly(true);
/*  98 */     this.valorReducaoLei7713OrigemME.setReadOnly(true);
/*     */     
/* 100 */     this.ganhoCapital2.setReadOnly(true);
/* 101 */     this.ganhoCapital2OrigemMNReal.setReadOnly(true);
/* 102 */     this.ganhoCapital2OrigemMEReal.setReadOnly(true);
/*     */     
/* 104 */     this.percentualReducaoLei11196FR1.setReadOnly(true);
/* 105 */     this.percentualReducaoLei11196FR1OrigemMN.setReadOnly(true);
/* 106 */     this.percentualReducaoLei11196FR1OrigemME.setReadOnly(true);
/*     */     
/* 108 */     this.valorReducaoLei11196FR1.setReadOnly(true);
/* 109 */     this.valorReducaoLei11196FR1OrigemMN.setReadOnly(true);
/* 110 */     this.valorReducaoLei11196FR1OrigemME.setReadOnly(true);
/*     */     
/* 112 */     this.ganhoCapital3.setReadOnly(true);
/* 113 */     this.ganhoCapital3OrigemMNReal.setReadOnly(true);
/* 114 */     this.ganhoCapital3OrigemMEReal.setReadOnly(true);
/*     */     
/* 116 */     this.percentualReducaoLei11196FR2.setReadOnly(true);
/* 117 */     this.percentualReducaoLei11196FR2OrigemMN.setReadOnly(true);
/* 118 */     this.percentualReducaoLei11196FR2OrigemME.setReadOnly(true);
/*     */     
/* 120 */     this.valorReducaoLei11196FR2.setReadOnly(true);
/* 121 */     this.valorReducaoLei11196FR2OrigemMN.setReadOnly(true);
/* 122 */     this.valorReducaoLei11196FR2OrigemME.setReadOnly(true);
/*     */     
/* 124 */     this.ganhoCapital4.setReadOnly(true);
/* 125 */     this.ganhoCapital4OrigemMNReal.setReadOnly(true);
/* 126 */     this.ganhoCapital4OrigemMEReal.setReadOnly(true);
/*     */     
/* 128 */     this.percentualReducaoAplicacaoOutroImovel.setReadOnly(true);
/* 129 */     this.percentualReducaoAplicacaoOutroImovelOrigemMN.setReadOnly(true);
/* 130 */     this.percentualReducaoAplicacaoOutroImovelOrigemME.setReadOnly(true);
/*     */     
/* 132 */     this.valorReducaoAplicacaoOutroImovel.setReadOnly(true);
/* 133 */     this.valorReducaoAplicacaoOutroImovelOrigemMN.setReadOnly(true);
/* 134 */     this.valorReducaoAplicacaoOutroImovelOrigemME.setReadOnly(true);
/*     */     
/* 136 */     this.percentualReducaoUnicoImovel.setReadOnly(true);
/* 137 */     this.percentualReducaoUnicoImovelOrigemMN.setReadOnly(true);
/* 138 */     this.percentualReducaoUnicoImovelOrigemME.setReadOnly(true);
/*     */     
/* 140 */     this.valorReducaoUnicoImovel.setReadOnly(true);
/* 141 */     this.valorReducaoUnicoImovelOrigemMN.setReadOnly(true);
/* 142 */     this.valorReducaoUnicoImovelOrigemME.setReadOnly(true);
/*     */     
/* 144 */     this.percentualReducaoBemPequenoValor.setReadOnly(true);
/* 145 */     this.percentualReducaoBemPequenoValorOrigemMN.setReadOnly(true);
/* 146 */     this.percentualReducaoBemPequenoValorOrigemME.setReadOnly(true);
/*     */     
/* 148 */     this.valorReducaoBemPequenoValor.setReadOnly(true);
/* 149 */     this.valorReducaoBemPequenoValorOrigemMN.setReadOnly(true);
/* 150 */     this.valorReducaoBemPequenoValorOrigemME.setReadOnly(true);
/*     */     
/* 152 */     this.ganhoCapital5.setReadOnly(true);
/* 153 */     this.ganhoCapital5OrigemMNReal.setReadOnly(true);
/* 154 */     this.ganhoCapital5OrigemMEReal.setReadOnly(true);
/*     */     
/* 156 */     this.ganhoCapitalTotalExterior.setReadOnly(true);
/*     */   }
/*     */   
/*     */   public ValorPositivo getGanhoCapitalTotalExterior() {
/* 160 */     return this.ganhoCapitalTotalExterior;
/*     */   }
/*     */   
/*     */   public ValorBigDecimalGCME getPercentualReducaoLei7713() {
/* 164 */     return this.percentualReducaoLei7713;
/*     */   }
/*     */   
/*     */   public ValorBigDecimalGCME getPercentualReducaoLei7713OrigemMN() {
/* 168 */     return this.percentualReducaoLei7713OrigemMN;
/*     */   }
/*     */   
/*     */   public ValorBigDecimalGCME getPercentualReducaoLei7713OrigemME() {
/* 172 */     return this.percentualReducaoLei7713OrigemME;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorReducaoLei7713() {
/* 176 */     return this.valorReducaoLei7713;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorReducaoLei7713OrigemMN() {
/* 180 */     return this.valorReducaoLei7713OrigemMN;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorReducaoLei7713OrigemME() {
/* 184 */     return this.valorReducaoLei7713OrigemME;
/*     */   }
/*     */   
/*     */   public ValorPositivo getGanhoCapital2() {
/* 188 */     return this.ganhoCapital2;
/*     */   }
/*     */   
/*     */   public ValorPositivo getGanhoCapital2OrigemMNReal() {
/* 192 */     return this.ganhoCapital2OrigemMNReal;
/*     */   }
/*     */   
/*     */   public ValorPositivo getGanhoCapital2OrigemMEReal() {
/* 196 */     return this.ganhoCapital2OrigemMEReal;
/*     */   }
/*     */   
/*     */   public ValorBigDecimalGCME getPercentualReducaoLei11196FR1() {
/* 200 */     return this.percentualReducaoLei11196FR1;
/*     */   }
/*     */   
/*     */   public ValorBigDecimalGCME getPercentualReducaoLei11196FR1OrigemMN() {
/* 204 */     return this.percentualReducaoLei11196FR1OrigemMN;
/*     */   }
/*     */   
/*     */   public ValorBigDecimalGCME getPercentualReducaoLei11196FR1OrigemME() {
/* 208 */     return this.percentualReducaoLei11196FR1OrigemME;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorReducaoLei11196FR1() {
/* 212 */     return this.valorReducaoLei11196FR1;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorReducaoLei11196FR1OrigemMN() {
/* 216 */     return this.valorReducaoLei11196FR1OrigemMN;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorReducaoLei11196FR1OrigemME() {
/* 220 */     return this.valorReducaoLei11196FR1OrigemME;
/*     */   }
/*     */   
/*     */   public ValorPositivo getGanhoCapital3() {
/* 224 */     return this.ganhoCapital3;
/*     */   }
/*     */   
/*     */   public ValorPositivo getGanhoCapital3OrigemMNReal() {
/* 228 */     return this.ganhoCapital3OrigemMNReal;
/*     */   }
/*     */   
/*     */   public ValorPositivo getGanhoCapital3OrigemMEReal() {
/* 232 */     return this.ganhoCapital3OrigemMEReal;
/*     */   }
/*     */   
/*     */   public ValorBigDecimalGCME getPercentualReducaoLei11196FR2() {
/* 236 */     return this.percentualReducaoLei11196FR2;
/*     */   }
/*     */   
/*     */   public ValorBigDecimalGCME getPercentualReducaoLei11196FR2OrigemMN() {
/* 240 */     return this.percentualReducaoLei11196FR2OrigemMN;
/*     */   }
/*     */   
/*     */   public ValorBigDecimalGCME getPercentualReducaoLei11196FR2OrigemME() {
/* 244 */     return this.percentualReducaoLei11196FR2OrigemME;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorReducaoLei11196FR2() {
/* 248 */     return this.valorReducaoLei11196FR2;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorReducaoLei11196FR2OrigemMN() {
/* 252 */     return this.valorReducaoLei11196FR2OrigemMN;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorReducaoLei11196FR2OrigemME() {
/* 256 */     return this.valorReducaoLei11196FR2OrigemME;
/*     */   }
/*     */   
/*     */   public ValorPositivo getGanhoCapital4() {
/* 260 */     return this.ganhoCapital4;
/*     */   }
/*     */   
/*     */   public ValorPositivo getGanhoCapital4OrigemMNReal() {
/* 264 */     return this.ganhoCapital4OrigemMNReal;
/*     */   }
/*     */   
/*     */   public ValorPositivo getGanhoCapital4OrigemMEReal() {
/* 268 */     return this.ganhoCapital4OrigemMEReal;
/*     */   }
/*     */   
/*     */   public ValorBigDecimalGCME getPercentualReducaoAplicacaoOutroImovel() {
/* 272 */     return this.percentualReducaoAplicacaoOutroImovel;
/*     */   }
/*     */   
/*     */   public ValorBigDecimalGCME getPercentualReducaoAplicacaoOutroImovelOrigemMN() {
/* 276 */     return this.percentualReducaoAplicacaoOutroImovelOrigemMN;
/*     */   }
/*     */   
/*     */   public ValorBigDecimalGCME getPercentualReducaoAplicacaoOutroImovelOrigemME() {
/* 280 */     return this.percentualReducaoAplicacaoOutroImovelOrigemME;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorReducaoAplicacaoOutroImovel() {
/* 284 */     return this.valorReducaoAplicacaoOutroImovel;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorReducaoAplicacaoOutroImovelOrigemMN() {
/* 288 */     return this.valorReducaoAplicacaoOutroImovelOrigemMN;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorReducaoAplicacaoOutroImovelOrigemME() {
/* 292 */     return this.valorReducaoAplicacaoOutroImovelOrigemME;
/*     */   }
/*     */   
/*     */   public ValorBigDecimalGCME getPercentualReducaoBemPequenoValor() {
/* 296 */     return this.percentualReducaoBemPequenoValor;
/*     */   }
/*     */   
/*     */   public ValorBigDecimalGCME getPercentualReducaoBemPequenoValorOrigemMN() {
/* 300 */     return this.percentualReducaoBemPequenoValorOrigemMN;
/*     */   }
/*     */   
/*     */   public ValorBigDecimalGCME getPercentualReducaoBemPequenoValorOrigemME() {
/* 304 */     return this.percentualReducaoBemPequenoValorOrigemME;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorReducaoBemPequenoValor() {
/* 308 */     return this.valorReducaoBemPequenoValor;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorReducaoBemPequenoValorOrigemMN() {
/* 312 */     return this.valorReducaoBemPequenoValorOrigemMN;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorReducaoBemPequenoValorOrigemME() {
/* 316 */     return this.valorReducaoBemPequenoValorOrigemME;
/*     */   }
/*     */   
/*     */   public ValorPositivo getGanhoCapital5() {
/* 320 */     return this.ganhoCapital5;
/*     */   }
/*     */   
/*     */   public ValorPositivo getGanhoCapital5OrigemMNReal() {
/* 324 */     return this.ganhoCapital5OrigemMNReal;
/*     */   }
/*     */   
/*     */   public ValorPositivo getGanhoCapital5OrigemMEReal() {
/* 328 */     return this.ganhoCapital5OrigemMEReal;
/*     */   }
/*     */   
/*     */   public ColecaoParcelaApuracaoCustoAquisicao getParcelasCustoAquisicao() {
/* 332 */     return this.parcelasCustoAquisicao;
/*     */   }
/*     */   
/*     */   public ValorBigDecimalGCME getPercentualReducaoUnicoImovel() {
/* 336 */     return this.percentualReducaoUnicoImovel;
/*     */   }
/*     */   
/*     */   public ValorBigDecimalGCME getPercentualReducaoUnicoImovelOrigemMN() {
/* 340 */     return this.percentualReducaoUnicoImovelOrigemMN;
/*     */   }
/*     */   
/*     */   public ValorBigDecimalGCME getPercentualReducaoUnicoImovelOrigemME() {
/* 344 */     return this.percentualReducaoUnicoImovelOrigemME;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorReducaoUnicoImovel() {
/* 348 */     return this.valorReducaoUnicoImovel;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorReducaoUnicoImovelOrigemMN() {
/* 352 */     return this.valorReducaoUnicoImovelOrigemMN;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorReducaoUnicoImovelOrigemME() {
/* 356 */     return this.valorReducaoUnicoImovelOrigemME;
/*     */   }
/*     */   
/*     */   public ValorPositivo getCustoAquisicaoTorna() {
/* 360 */     return this.custoAquisicaoTorna;
/*     */   }
/*     */   
/*     */   public ValorPositivo getCustoAquisicaoTornaOrigemMNReal() {
/* 364 */     return this.custoAquisicaoTornaOrigemMNReal;
/*     */   }
/*     */   
/*     */   public ValorPositivo getCustoAquisicaoTornaOrigemMEDolar() {
/* 368 */     return this.custoAquisicaoTornaOrigemMEDolar;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\apuracao\ApuracaoBemImovel.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */