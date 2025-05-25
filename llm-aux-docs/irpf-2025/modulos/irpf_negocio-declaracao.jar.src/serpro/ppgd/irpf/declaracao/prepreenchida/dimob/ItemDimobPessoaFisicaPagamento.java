/*     */ package serpro.ppgd.irpf.declaracao.prepreenchida.dimob;
/*     */ 
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ public class ItemDimobPessoaFisicaPagamento
/*     */   extends ItemDimob {
/*   7 */   private Valor valorRendimentoJaneiro = new Valor(this, "Valor do Rendimento - Janeiro");
/*   8 */   private Valor valorComissaoJaneiro = new Valor(this, "Valor do Comissão - Janeiro");
/*   9 */   private Valor valorImpostoJaneiro = new Valor(this, "Valor do Imposto - Janeiro");
/*  10 */   private Valor valorRendimentoFevereiro = new Valor(this, "Valor do Rendimento - Fevereiro");
/*  11 */   private Valor valorComissaoFevereiro = new Valor(this, "Valor do Comissão - Fevereiro");
/*  12 */   private Valor valorImpostoFevereiro = new Valor(this, "Valor do Imposto - Fevereiro");
/*  13 */   private Valor valorRendimentoMarco = new Valor(this, "Valor do Rendimento - Março");
/*  14 */   private Valor valorComissaoMarco = new Valor(this, "Valor do Comissão - Março");
/*  15 */   private Valor valorImpostoMarco = new Valor(this, "Valor do Imposto - Março");
/*  16 */   private Valor valorRendimentoAbril = new Valor(this, "Valor do Rendimento - Abril");
/*  17 */   private Valor valorComissaoAbril = new Valor(this, "Valor do Comissão - Abril");
/*  18 */   private Valor valorImpostoAbril = new Valor(this, "Valor do Imposto - Abril");
/*  19 */   private Valor valorRendimentoMaio = new Valor(this, "Valor do Rendimento - Maio");
/*  20 */   private Valor valorComissaoMaio = new Valor(this, "Valor do Comissão - Maio");
/*  21 */   private Valor valorImpostoMaio = new Valor(this, "Valor do Imposto - Maio");
/*  22 */   private Valor valorRendimentoJunho = new Valor(this, "Valor do Rendimento - Junho");
/*  23 */   private Valor valorComissaoJunho = new Valor(this, "Valor do Comissão - Junho");
/*  24 */   private Valor valorImpostoJunho = new Valor(this, "Valor do Imposto - Junho");
/*  25 */   private Valor valorRendimentoJulho = new Valor(this, "Valor do Rendimento - Julho");
/*  26 */   private Valor valorComissaoJulho = new Valor(this, "Valor do Comissão - Julho");
/*  27 */   private Valor valorImpostoJulho = new Valor(this, "Valor do Imposto - Julho");
/*  28 */   private Valor valorRendimentoAgosto = new Valor(this, "Valor do Rendimento - Agosto");
/*  29 */   private Valor valorComissaoAgosto = new Valor(this, "Valor do Comissão - Agosto");
/*  30 */   private Valor valorImpostoAgosto = new Valor(this, "Valor do Imposto - Agosto");
/*  31 */   private Valor valorRendimentoSetembro = new Valor(this, "Valor do Rendimento - Setembro");
/*  32 */   private Valor valorComissaoSetembro = new Valor(this, "Valor do Comissão - Setembro");
/*  33 */   private Valor valorImpostoSetembro = new Valor(this, "Valor do Imposto - Setembro");
/*  34 */   private Valor valorRendimentoOutubro = new Valor(this, "Valor do Rendimento - Outubro");
/*  35 */   private Valor valorComissaoOutubro = new Valor(this, "Valor do Comissão - Outubro");
/*  36 */   private Valor valorImpostoOutubro = new Valor(this, "Valor do Imposto - Outubro");
/*  37 */   private Valor valorRendimentoNovembro = new Valor(this, "Valor do Rendimento - Novembro");
/*  38 */   private Valor valorComissaoNovembro = new Valor(this, "Valor do Comissão - Novembro");
/*  39 */   private Valor valorImpostoNovembro = new Valor(this, "Valor do Imposto - Novembro");
/*  40 */   private Valor valorRendimentoDezembro = new Valor(this, "Valor do Rendimento - Dezembro");
/*  41 */   private Valor valorComissaoDezembro = new Valor(this, "Valor do Comissão - Dezembro");
/*  42 */   private Valor valorImpostoDezembro = new Valor(this, "Valor do Imposto - Dezembro");
/*     */ 
/*     */   
/*     */   public Valor getValorRendimentoLiquidoPorMes(int mes) {
/*  46 */     Valor retorno = new Valor();
/*     */     
/*  48 */     switch (mes) {
/*     */       case 0:
/*  50 */         retorno.append('+', this.valorRendimentoJaneiro);
/*  51 */         retorno.append('-', this.valorComissaoJaneiro);
/*     */         break;
/*     */       case 1:
/*  54 */         retorno.append('+', this.valorRendimentoFevereiro);
/*  55 */         retorno.append('-', this.valorComissaoFevereiro);
/*     */         break;
/*     */       case 2:
/*  58 */         retorno.append('+', this.valorRendimentoMarco);
/*  59 */         retorno.append('-', this.valorComissaoMarco);
/*     */         break;
/*     */       case 3:
/*  62 */         retorno.append('+', this.valorRendimentoAbril);
/*  63 */         retorno.append('-', this.valorComissaoAbril);
/*     */         break;
/*     */       case 4:
/*  66 */         retorno.append('+', this.valorRendimentoMaio);
/*  67 */         retorno.append('-', this.valorComissaoMaio);
/*     */         break;
/*     */       case 5:
/*  70 */         retorno.append('+', this.valorRendimentoJunho);
/*  71 */         retorno.append('-', this.valorComissaoJunho);
/*     */         break;
/*     */       case 6:
/*  74 */         retorno.append('+', this.valorRendimentoJulho);
/*  75 */         retorno.append('-', this.valorComissaoJulho);
/*     */         break;
/*     */       case 7:
/*  78 */         retorno.append('+', this.valorRendimentoAgosto);
/*  79 */         retorno.append('-', this.valorComissaoAgosto);
/*     */         break;
/*     */       case 8:
/*  82 */         retorno.append('+', this.valorRendimentoSetembro);
/*  83 */         retorno.append('-', this.valorComissaoSetembro);
/*     */         break;
/*     */       case 9:
/*  86 */         retorno.append('+', this.valorRendimentoOutubro);
/*  87 */         retorno.append('-', this.valorComissaoOutubro);
/*     */         break;
/*     */       case 10:
/*  90 */         retorno.append('+', this.valorRendimentoNovembro);
/*  91 */         retorno.append('-', this.valorComissaoNovembro);
/*     */         break;
/*     */       case 11:
/*  94 */         retorno.append('+', this.valorRendimentoDezembro);
/*  95 */         retorno.append('-', this.valorComissaoDezembro);
/*     */         break;
/*     */     } 
/*     */     
/*  99 */     return retorno;
/*     */   }
/*     */   
/*     */   public Valor getTotalRendimentoLiquidoMeses() {
/* 103 */     Valor retorno = new Valor();
/* 104 */     for (int i = 0; i < 12; i++) {
/* 105 */       retorno.append('+', getValorRendimentoLiquidoPorMes(i));
/*     */     }
/* 107 */     return retorno;
/*     */   }
/*     */   
/*     */   public Valor getTotalRendimento() {
/* 111 */     Valor retorno = new Valor();
/*     */     
/* 113 */     retorno.append('+', this.valorRendimentoJaneiro);
/* 114 */     retorno.append('+', this.valorRendimentoFevereiro);
/* 115 */     retorno.append('+', this.valorRendimentoMarco);
/* 116 */     retorno.append('+', this.valorRendimentoAbril);
/* 117 */     retorno.append('+', this.valorRendimentoMaio);
/* 118 */     retorno.append('+', this.valorRendimentoJunho);
/* 119 */     retorno.append('+', this.valorRendimentoJulho);
/* 120 */     retorno.append('+', this.valorRendimentoAgosto);
/* 121 */     retorno.append('+', this.valorRendimentoSetembro);
/* 122 */     retorno.append('+', this.valorRendimentoOutubro);
/* 123 */     retorno.append('+', this.valorRendimentoNovembro);
/* 124 */     retorno.append('+', this.valorRendimentoDezembro);
/*     */     
/* 126 */     return retorno;
/*     */   }
/*     */   
/*     */   public Valor getTotalComissao() {
/* 130 */     Valor retorno = new Valor();
/*     */     
/* 132 */     retorno.append('+', this.valorComissaoJaneiro);
/* 133 */     retorno.append('+', this.valorComissaoFevereiro);
/* 134 */     retorno.append('+', this.valorComissaoMarco);
/* 135 */     retorno.append('+', this.valorComissaoAbril);
/* 136 */     retorno.append('+', this.valorComissaoMaio);
/* 137 */     retorno.append('+', this.valorComissaoJunho);
/* 138 */     retorno.append('+', this.valorComissaoJulho);
/* 139 */     retorno.append('+', this.valorComissaoAgosto);
/* 140 */     retorno.append('+', this.valorComissaoSetembro);
/* 141 */     retorno.append('+', this.valorComissaoOutubro);
/* 142 */     retorno.append('+', this.valorComissaoNovembro);
/* 143 */     retorno.append('+', this.valorComissaoDezembro);
/*     */     
/* 145 */     return retorno;
/*     */   }
/*     */   
/*     */   public Valor getValorRendimentoJaneiro() {
/* 149 */     return this.valorRendimentoJaneiro;
/*     */   }
/*     */   
/*     */   public Valor getValorComissaoJaneiro() {
/* 153 */     return this.valorComissaoJaneiro;
/*     */   }
/*     */   
/*     */   public Valor getValorImpostoJaneiro() {
/* 157 */     return this.valorImpostoJaneiro;
/*     */   }
/*     */   
/*     */   public Valor getValorRendimentoFevereiro() {
/* 161 */     return this.valorRendimentoFevereiro;
/*     */   }
/*     */   
/*     */   public Valor getValorComissaoFevereiro() {
/* 165 */     return this.valorComissaoFevereiro;
/*     */   }
/*     */   
/*     */   public Valor getValorImpostoFevereiro() {
/* 169 */     return this.valorImpostoFevereiro;
/*     */   }
/*     */   
/*     */   public Valor getValorRendimentoMarco() {
/* 173 */     return this.valorRendimentoMarco;
/*     */   }
/*     */   
/*     */   public Valor getValorComissaoMarco() {
/* 177 */     return this.valorComissaoMarco;
/*     */   }
/*     */   
/*     */   public Valor getValorImpostoMarco() {
/* 181 */     return this.valorImpostoMarco;
/*     */   }
/*     */   
/*     */   public Valor getValorRendimentoAbril() {
/* 185 */     return this.valorRendimentoAbril;
/*     */   }
/*     */   
/*     */   public Valor getValorComissaoAbril() {
/* 189 */     return this.valorComissaoAbril;
/*     */   }
/*     */   
/*     */   public Valor getValorImpostoAbril() {
/* 193 */     return this.valorImpostoAbril;
/*     */   }
/*     */   
/*     */   public Valor getValorRendimentoMaio() {
/* 197 */     return this.valorRendimentoMaio;
/*     */   }
/*     */   
/*     */   public Valor getValorComissaoMaio() {
/* 201 */     return this.valorComissaoMaio;
/*     */   }
/*     */   
/*     */   public Valor getValorImpostoMaio() {
/* 205 */     return this.valorImpostoMaio;
/*     */   }
/*     */   
/*     */   public Valor getValorRendimentoJunho() {
/* 209 */     return this.valorRendimentoJunho;
/*     */   }
/*     */   
/*     */   public Valor getValorComissaoJunho() {
/* 213 */     return this.valorComissaoJunho;
/*     */   }
/*     */   
/*     */   public Valor getValorImpostoJunho() {
/* 217 */     return this.valorImpostoJunho;
/*     */   }
/*     */   
/*     */   public Valor getValorRendimentoJulho() {
/* 221 */     return this.valorRendimentoJulho;
/*     */   }
/*     */   
/*     */   public Valor getValorComissaoJulho() {
/* 225 */     return this.valorComissaoJulho;
/*     */   }
/*     */   
/*     */   public Valor getValorImpostoJulho() {
/* 229 */     return this.valorImpostoJulho;
/*     */   }
/*     */   
/*     */   public Valor getValorRendimentoAgosto() {
/* 233 */     return this.valorRendimentoAgosto;
/*     */   }
/*     */   
/*     */   public Valor getValorComissaoAgosto() {
/* 237 */     return this.valorComissaoAgosto;
/*     */   }
/*     */   
/*     */   public Valor getValorImpostoAgosto() {
/* 241 */     return this.valorImpostoAgosto;
/*     */   }
/*     */   
/*     */   public Valor getValorRendimentoSetembro() {
/* 245 */     return this.valorRendimentoSetembro;
/*     */   }
/*     */   
/*     */   public Valor getValorComissaoSetembro() {
/* 249 */     return this.valorComissaoSetembro;
/*     */   }
/*     */   
/*     */   public Valor getValorImpostoSetembro() {
/* 253 */     return this.valorImpostoSetembro;
/*     */   }
/*     */   
/*     */   public Valor getValorRendimentoOutubro() {
/* 257 */     return this.valorRendimentoOutubro;
/*     */   }
/*     */   
/*     */   public Valor getValorComissaoOutubro() {
/* 261 */     return this.valorComissaoOutubro;
/*     */   }
/*     */   
/*     */   public Valor getValorImpostoOutubro() {
/* 265 */     return this.valorImpostoOutubro;
/*     */   }
/*     */   
/*     */   public Valor getValorRendimentoNovembro() {
/* 269 */     return this.valorRendimentoNovembro;
/*     */   }
/*     */   
/*     */   public Valor getValorComissaoNovembro() {
/* 273 */     return this.valorComissaoNovembro;
/*     */   }
/*     */   
/*     */   public Valor getValorImpostoNovembro() {
/* 277 */     return this.valorImpostoNovembro;
/*     */   }
/*     */   
/*     */   public Valor getValorRendimentoDezembro() {
/* 281 */     return this.valorRendimentoDezembro;
/*     */   }
/*     */   
/*     */   public Valor getValorComissaoDezembro() {
/* 285 */     return this.valorComissaoDezembro;
/*     */   }
/*     */   
/*     */   public Valor getValorImpostoDezembro() {
/* 289 */     return this.valorImpostoDezembro;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\declaracao\prepreenchida\dimob\ItemDimobPessoaFisicaPagamento.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */