/*     */ package serpro.ppgd.irpf.declaracao.assistida.informerendimentos;
/*     */ 
/*     */ import java.util.List;
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.ElementoTabela;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemRendIsentoOuTributacaoExclusiva
/*     */   extends ObjetoNegocio
/*     */   implements Comparable<ItemRendIsentoOuTributacaoExclusiva>
/*     */ {
/*  22 */   private Alfa codigo = new Alfa(this, "Código de Tipo de Rendimento");
/*  23 */   private Alfa descricao = new Alfa(this, "Descrição de Tipo de Rendimento");
/*  24 */   private Valor valor = new Valor(this, "Valor");
/*  25 */   private Valor decimoTerceiro = new Valor(this, "13º salário");
/*  26 */   private ValorPositivo impostoRendaRetidoFonte = new ValorPositivo(this, "IRRF");
/*  27 */   private ValorPositivo impostoRendaRetidoFonteDecimoTerceiro = new ValorPositivo(this, "IRRF sobre o 13º Salário");
/*  28 */   private ValorPositivo previdenciaOficial = new ValorPositivo(this, "Previdência Oficial");
/*     */   
/*     */   public static final int TIPO_RENDIMENTO_ISENTO = 1;
/*     */   
/*     */   public static final int TIPO_RENDIMENTO_TRIBUTACAO_EXCLUSIVA = 2;
/*     */   
/*     */   public static final int TIPO_REND_ISENTO_PENSAO = 1;
/*     */   public static final int TIPO_REND_ISENTO_MOLESTIA_GRAVE = 2;
/*     */   public static final int TIPO_REND_ISENTO_LUCROS_DIVIDENDOS = 3;
/*     */   public static final int TIPO_REND_ISENTO_MICROEMPRESA = 4;
/*     */   public static final int TIPO_REND_ISENTO_RECISAO = 5;
/*     */   public static final int TIPO_REND_ISENTO_BOLSA_ESTUDO_MEDICO = 7;
/*     */   public static final int TIPO_REND_ISENTO_BOLSA_ESTUDO_DOACAO = 8;
/*     */   public static final int TIPO_REND_ISENTO_IR_ANTERIORES = 9;
/*     */   public static final int TIPO_REND_ISENTO_AJUDA_CUSTO = 10;
/*     */   public static final int TIPO_REND_ISENTO_AJUDA_CUSTO_TRADUZIDO = 110;
/*     */   public static final int TIPO_REND_ISENTO_ABONO_PECUNIARIO = 11;
/*     */   public static final int TIPO_REND_ISENTO_ABONO_PECUNIARIO_TRADUZIDO = 111;
/*     */   public static final int TIPO_REND_OUTROS = 100;
/*     */   public static final int TIPO_REND_EXCL_JUROS_CAPITAL = 1;
/*     */   public static final int TIPO_REND_EXCL_LUCROS = 2;
/*     */   
/*     */   public ItemRendIsentoOuTributacaoExclusiva() {
/*  51 */     this.codigo.setConteudo(String.valueOf(100));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Alfa getCodigo() {
/*  58 */     return this.codigo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Alfa getDescricao() {
/*  65 */     return this.descricao;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getValor() {
/*  72 */     return this.valor;
/*     */   }
/*     */   
/*     */   public Valor getDecimoTerceiro() {
/*  76 */     return this.decimoTerceiro;
/*     */   }
/*     */   
/*     */   public int compareTo(ItemRendIsentoOuTributacaoExclusiva o) {
/*     */     int n1;
/*     */     int n2;
/*     */     try {
/*  83 */       n1 = Integer.valueOf(getCodigo().naoFormatado()).intValue();
/*  84 */       n2 = Integer.valueOf(o.getCodigo().naoFormatado()).intValue();
/*  85 */     } catch (Exception e) {
/*  86 */       n1 = 100;
/*  87 */       n2 = 100;
/*     */     } 
/*     */     
/*  90 */     if (n1 > n2)
/*  91 */       return 1; 
/*  92 */     if (n1 < n2) {
/*  93 */       return -1;
/*     */     }
/*  95 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDescricaoPorCodigo(int tipoRendimento) {
/*     */     int cod;
/*     */     try {
/* 102 */       cod = Integer.valueOf(getCodigo().naoFormatado()).intValue();
/* 103 */     } catch (Exception e) {
/* 104 */       cod = 0;
/*     */     } 
/*     */     
/* 107 */     if (tipoRendimento == 1) {
/* 108 */       return getDescricaoRendimentoIsentoPorCodigo(cod);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 131 */     if (tipoRendimento == 2) {
/* 132 */       return getDescricaoRendimentoTributacaoExclusivaPorCodigo(cod);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 140 */     return getDescricao().formatado();
/*     */   }
/*     */   
/*     */   public ValorPositivo getImpostoRendaRetidoFonte() {
/* 144 */     return this.impostoRendaRetidoFonte;
/*     */   }
/*     */   
/*     */   public ValorPositivo getImpostoRendaRetidoFonteDecimoTerceiro() {
/* 148 */     return this.impostoRendaRetidoFonteDecimoTerceiro;
/*     */   }
/*     */   
/*     */   public ValorPositivo getPrevidenciaOficial() {
/* 152 */     return this.previdenciaOficial;
/*     */   }
/*     */   
/*     */   public int obterCodigoRendIsentoPorCodigoInformeRendimentos(int codigoInformeRendimento) {
/* 156 */     int codigoTraduzido = 0;
/* 157 */     switch (codigoInformeRendimento) {
/*     */       case 1:
/* 159 */         codigoTraduzido = 10;
/*     */         break;
/*     */       case 2:
/* 162 */         codigoTraduzido = 11;
/*     */         break;
/*     */       case 3:
/* 165 */         codigoTraduzido = 9;
/*     */         break;
/*     */       case 4:
/* 168 */         codigoTraduzido = 13;
/*     */         break;
/*     */       case 5:
/* 171 */         codigoTraduzido = 4;
/*     */         break;
/*     */       case 7:
/* 174 */         codigoTraduzido = 2;
/*     */         break;
/*     */       case 8:
/* 177 */         codigoTraduzido = 1;
/*     */         break;
/*     */       case 9:
/* 180 */         codigoTraduzido = 16;
/*     */         break;
/*     */       case 10:
/* 183 */         codigoTraduzido = 110;
/*     */         break;
/*     */       case 11:
/* 186 */         codigoTraduzido = 111;
/*     */         break;
/*     */       case 100:
/* 189 */         codigoTraduzido = 26;
/*     */         break;
/*     */     } 
/* 192 */     return codigoTraduzido;
/*     */   }
/*     */   
/*     */   public String getDescricaoRendimentoIsentoPorCodigo(int codigoInformeRendimentos) {
/* 196 */     int codTraduzido = obterCodigoRendIsentoPorCodigoInformeRendimentos(codigoInformeRendimentos);
/* 197 */     String tipoRendimento = "";
/* 198 */     if (codTraduzido == 110) {
/* 199 */       tipoRendimento = "Diárias e ajuda de custo";
/* 200 */     } else if (codTraduzido == 111) {
/* 201 */       tipoRendimento = "Abono pecuniário";
/* 202 */     } else if (codigoInformeRendimentos == 100) {
/* 203 */       tipoRendimento = getDescricao().naoFormatado();
/*     */     } else {
/* 205 */       List<ElementoTabela> tiposRendimentoIsentos = CadastroTabelasIRPF.recuperarTiposRendimentosIsentos();
/* 206 */       for (ElementoTabela tipo : tiposRendimentoIsentos) {
/* 207 */         if (Integer.valueOf(tipo.getConteudo(0)).intValue() == codTraduzido) {
/* 208 */           tipoRendimento = tipo.getConteudo(2);
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 213 */     return tipoRendimento;
/*     */   }
/*     */   
/*     */   public String getDescricaoRendimentoTributacaoExclusivaPorCodigo(int codigoInformeRendimentos) {
/* 217 */     int codTraduzido = obterCodigoRendimentoTributacaoExclusivaPorCodigoInformeRendimentos(codigoInformeRendimentos);
/* 218 */     String tipoRendimento = "";
/*     */     
/* 220 */     if (codigoInformeRendimentos == 100) {
/* 221 */       tipoRendimento = getDescricao().naoFormatado();
/*     */     } else {
/* 223 */       List<ElementoTabela> tiposRendimentoTributacaoExclusiva = CadastroTabelasIRPF.recuperarTiposRendimentosTributacaoExclusiva();
/* 224 */       for (ElementoTabela tipo : tiposRendimentoTributacaoExclusiva) {
/* 225 */         if (Integer.valueOf(tipo.getConteudo(0)).intValue() == codTraduzido) {
/* 226 */           tipoRendimento = tipo.getConteudo(2);
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 233 */     return tipoRendimento;
/*     */   }
/*     */   
/*     */   public int obterCodigoRendimentoTributacaoExclusivaPorCodigoInformeRendimentos(int codigoInformeRendimento) {
/* 237 */     int codigoTraduzido = 0;
/* 238 */     switch (codigoInformeRendimento) {
/*     */       case 1:
/* 240 */         codigoTraduzido = 10;
/*     */         break;
/*     */       case 2:
/* 243 */         codigoTraduzido = 11;
/*     */         break;
/*     */       case 100:
/* 246 */         codigoTraduzido = 12;
/*     */         break;
/*     */     } 
/* 249 */     return codigoTraduzido;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\declaracao\assistida\informerendimentos\ItemRendIsentoOuTributacaoExclusiva.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */