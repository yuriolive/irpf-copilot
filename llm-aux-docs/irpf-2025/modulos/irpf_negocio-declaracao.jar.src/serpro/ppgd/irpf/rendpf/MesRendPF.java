/*     */ package serpro.ppgd.irpf.rendpf;
/*     */ 
/*     */ import java.util.List;
/*     */ import serpro.ppgd.irpf.ValidadorNaoNegativo;
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.irpf.gui.NavegacaoIf;
/*     */ import serpro.ppgd.irpf.tabelas.TabelaAliquotasIRPF;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.Inteiro;
/*     */ import serpro.ppgd.negocio.ObjetoFicha;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ import serpro.ppgd.negocio.ValidadorIf;
/*     */ import serpro.ppgd.negocio.Valor;
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
/*     */ public class MesRendPF
/*     */   extends ObjetoNegocio
/*     */   implements ObjetoFicha
/*     */ {
/*     */   public static final String NOME_PESSOA_FISICA = "Pessoa Física";
/*     */   public static final String NOME_ALUGUEIS = "Aluguéis";
/*     */   public static final String NOME_OUTROS = "Outros";
/*     */   public static final String NOME_EXTERIOR = "Exterior";
/*     */   public static final String NOME_PREV_OFICIAL = "Previdência oficial";
/*     */   public static final String NOME_DEPENDENTES = "Dependentes";
/*     */   public static final String NOME_PENSAOALIM = "Pensão Alimentícia";
/*     */   public static final String NOME_IMPOSTO_PAGO_EXTERIR_COMPENSAR = "Imposto Pago no Exterior a Compensar";
/*     */   public static final String NOME_LIVRO_CAIXA = "Livro Caixa";
/*     */   public static final String NOME_CARNE_LEAO = "Carnê-Leão";
/*     */   public static final String NOME_DARF_PAGO = "Darf Pago cód. 0190";
/*  40 */   private Inteiro mes = new Inteiro(this, "");
/*     */   
/*  42 */   private ValorPositivo pessoaFisica = new ValorPositivo(this, "Pessoa Física");
/*  43 */   private ValorPositivo alugueis = new ValorPositivo(this, "Aluguéis");
/*  44 */   private ValorPositivo outros = new ValorPositivo(this, "Outros");
/*  45 */   private ValorPositivo exterior = new ValorPositivo(this, "Exterior");
/*  46 */   private ValorPositivo previdencia = new ValorPositivo(this, "Previdência oficial");
/*  47 */   private ValorPositivo numDependentes = new ValorPositivo(this, "numDependentes", 3, 0);
/*  48 */   private ValorPositivo dependentes = new ValorPositivo(this, "Dependentes");
/*  49 */   private ValorPositivo pensao = new ValorPositivo(this, "Pensão Alimentícia");
/*  50 */   private ValorPositivo livroCaixa = new ValorPositivo(this, "Livro Caixa");
/*  51 */   private ValorPositivo impostoPagoCompensarExterior = new ValorPositivo(this, "Imposto Pago no Exterior a Compensar");
/*  52 */   private ValorPositivo carneLeao = new ValorPositivo(this, "Carnê-Leão");
/*  53 */   private ValorPositivo darfPago = new ValorPositivo(this, "Darf Pago cód. 0190");
/*     */   
/*     */   private transient ContasMes contasMes;
/*     */   
/*     */   public MesRendPF(final int mes, ContasMes contasMes) {
/*  58 */     this.pessoaFisica.addValidador((ValidadorIf)new ValidadorNaoNegativo((byte)3));
/*  59 */     this.alugueis.addValidador((ValidadorIf)new ValidadorNaoNegativo((byte)3));
/*  60 */     this.outros.addValidador((ValidadorIf)new ValidadorNaoNegativo((byte)3));
/*  61 */     this.exterior.addValidador((ValidadorIf)new ValidadorNaoNegativo((byte)3));
/*  62 */     this.previdencia.addValidador((ValidadorIf)new ValidadorNaoNegativo((byte)3));
/*  63 */     this.dependentes.addValidador((ValidadorIf)new ValidadorNaoNegativo((byte)3));
/*  64 */     this.numDependentes.addValidador((ValidadorIf)new ValidadorNaoNegativo((byte)3));
/*  65 */     this.pensao.addValidador((ValidadorIf)new ValidadorNaoNegativo((byte)3));
/*  66 */     this.livroCaixa.addValidador((ValidadorIf)new ValidadorNaoNegativo((byte)3));
/*  67 */     this.impostoPagoCompensarExterior.addValidador((ValidadorIf)new ValidadorNaoNegativo((byte)3));
/*  68 */     this.carneLeao.addValidador((ValidadorIf)new ValidadorNaoNegativo((byte)3));
/*  69 */     this.darfPago.addValidador((ValidadorIf)new ValidadorNaoNegativo((byte)3));
/*     */     
/*  71 */     this.mes.setConteudo(mes);
/*  72 */     this.contasMes = contasMes;
/*  73 */     this.contasMes.getTotalRendTrabNaoAssPF().addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object o, String string, Object o1, Object o2) {
/*  76 */             MesRendPF.this.pessoaFisica.setConteudo((String)o2);
/*     */           }
/*     */         });
/*     */ 
/*     */     
/*  81 */     getNumDependentes().addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object arg0, String arg1, Object arg2, Object arg3) {
/*  84 */             Valor valorDeducao = new Valor();
/*  85 */             valorDeducao.setConteudo(TabelaAliquotasIRPF.obterDeducaoMensalDependente(Integer.valueOf(mes + 1)));
/*  86 */             valorDeducao.append('*', MesRendPF.this.getNumDependentes().naoFormatado());
/*  87 */             MesRendPF.this.getDependentes().setConteudo(valorDeducao);
/*     */           }
/*     */         });
/*     */     
/*  91 */     getDependentes().addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object arg0, String arg1, Object arg2, Object arg3) {
/*  94 */             Valor valorDeducao = new Valor();
/*  95 */             valorDeducao.setConteudo(TabelaAliquotasIRPF.obterDeducaoMensalDependente(Integer.valueOf(mes + 1)));
/*  96 */             if (MesRendPF.this.getDependentes().comparacao(">=", valorDeducao)) {
/*  97 */               MesRendPF.this.getNumDependentes().setConteudo(MesRendPF.this.getDependentes().divide(valorDeducao));
/*     */             } else {
/*  99 */               MesRendPF.this.getNumDependentes().setConteudo("0");
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
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
/*     */   public void addObservador(Observador obs) {
/* 136 */     this.pessoaFisica.addObservador(obs);
/* 137 */     this.alugueis.addObservador(obs);
/* 138 */     this.outros.addObservador(obs);
/* 139 */     this.exterior.addObservador(obs);
/* 140 */     this.previdencia.addObservador(obs);
/* 141 */     this.dependentes.addObservador(obs);
/* 142 */     this.pensao.addObservador(obs);
/* 143 */     this.impostoPagoCompensarExterior.addObservador(obs);
/* 144 */     this.livroCaixa.addObservador(obs);
/* 145 */     this.carneLeao.addObservador(obs);
/* 146 */     this.darfPago.addObservador(obs);
/*     */   }
/*     */   
/*     */   public void removeObservador(Observador obs) {
/* 150 */     this.pessoaFisica.removeObservador(obs);
/* 151 */     this.alugueis.removeObservador(obs);
/* 152 */     this.outros.removeObservador(obs);
/* 153 */     this.exterior.removeObservador(obs);
/* 154 */     this.previdencia.removeObservador(obs);
/* 155 */     this.dependentes.removeObservador(obs);
/* 156 */     this.pensao.removeObservador(obs);
/* 157 */     this.impostoPagoCompensarExterior.removeObservador(obs);
/* 158 */     this.livroCaixa.removeObservador(obs);
/* 159 */     this.carneLeao.removeObservador(obs);
/* 160 */     this.darfPago.removeObservador(obs);
/*     */   }
/*     */   
/*     */   public Valor getCarneLeao() {
/* 164 */     return (Valor)this.carneLeao;
/*     */   }
/*     */   
/*     */   public Valor getDependentes() {
/* 168 */     return (Valor)this.dependentes;
/*     */   }
/*     */   
/*     */   public Valor getExterior() {
/* 172 */     return (Valor)this.exterior;
/*     */   }
/*     */   
/*     */   public Valor getLivroCaixa() {
/* 176 */     return (Valor)this.livroCaixa;
/*     */   }
/*     */   
/*     */   public Inteiro getMes() {
/* 180 */     return this.mes;
/*     */   }
/*     */   
/*     */   public Valor getPensao() {
/* 184 */     return (Valor)this.pensao;
/*     */   }
/*     */   
/*     */   public Valor getPessoaFisica() {
/* 188 */     return (Valor)this.pessoaFisica;
/*     */   }
/*     */   
/*     */   public Valor getAlugueis() {
/* 192 */     return (Valor)this.alugueis;
/*     */   }
/*     */   
/*     */   public Valor getOutros() {
/* 196 */     return (Valor)this.outros;
/*     */   }
/*     */   
/*     */   public Valor getImpostoPagoCompensarExterior() {
/* 200 */     return (Valor)this.impostoPagoCompensarExterior;
/*     */   }
/*     */   
/*     */   public Valor getPrevidencia() {
/* 204 */     return (Valor)this.previdencia;
/*     */   }
/*     */   
/*     */   public Valor getDarfPago() {
/* 208 */     return (Valor)this.darfPago;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVazio() {
/* 213 */     return (this.pessoaFisica.isVazio() && this.exterior.isVazio() && this.previdencia.isVazio() && this.dependentes.isVazio() && this.pensao.isVazio() && this.livroCaixa.isVazio() && this.darfPago
/* 214 */       .isVazio() && this.alugueis.isVazio() && this.outros.isVazio());
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 219 */     List<Informacao> retorno = super.recuperarListaCamposPendencia();
/* 220 */     retorno.add(getDependentes());
/* 221 */     retorno.add(getPessoaFisica());
/* 222 */     retorno.add(getAlugueis());
/* 223 */     retorno.add(getOutros());
/* 224 */     retorno.add(getDarfPago());
/* 225 */     retorno.add(getExterior());
/* 226 */     retorno.add(getImpostoPagoCompensarExterior());
/* 227 */     retorno.add(getLivroCaixa());
/* 228 */     retorno.add(getPensao());
/* 229 */     retorno.add(getPrevidencia());
/*     */     
/* 231 */     return retorno;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClasseFicha() {
/* 236 */     return NavegacaoIf.PAINEL_REND_TRIB_RECEB_PF_EXT;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 241 */     if (getFicha().equals("Rendimentos Tributáveis Recebidos de PF e do Exterior - Titular"))
/* 242 */       return "Titular"; 
/* 243 */     if (getFicha().equals("Rendimentos Tributáveis Recebidos de PF e do Exterior - Dependentes")) {
/* 244 */       return "Dependentes";
/*     */     }
/*     */     
/* 247 */     return null;
/*     */   }
/*     */   
/*     */   public ContasMes getContasMes() {
/* 251 */     return this.contasMes;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 256 */     return "Rendimentos Tributáveis Recebidos de PF e do Exterior";
/*     */   }
/*     */   
/*     */   public Valor getNumDependentes() {
/* 260 */     return (Valor)this.numDependentes;
/*     */   }
/*     */   
/*     */   public void limpar() {
/* 264 */     this.contasMes.setObservadoresAtivos(false);
/* 265 */     this.pessoaFisica.setObservadoresAtivos(false);
/* 266 */     this.alugueis.setObservadoresAtivos(false);
/* 267 */     this.outros.setObservadoresAtivos(false);
/* 268 */     this.exterior.setObservadoresAtivos(false);
/* 269 */     this.previdencia.setObservadoresAtivos(false);
/* 270 */     this.numDependentes.setObservadoresAtivos(false);
/* 271 */     this.dependentes.setObservadoresAtivos(false);
/* 272 */     this.pensao.setObservadoresAtivos(false);
/* 273 */     this.livroCaixa.setObservadoresAtivos(false);
/* 274 */     this.impostoPagoCompensarExterior.setObservadoresAtivos(false);
/* 275 */     this.carneLeao.setObservadoresAtivos(false);
/* 276 */     this.darfPago.setObservadoresAtivos(false);
/*     */     
/* 278 */     this.contasMes.clear();
/* 279 */     this.pessoaFisica.clear();
/* 280 */     this.alugueis.clear();
/* 281 */     this.outros.clear();
/* 282 */     this.exterior.clear();
/* 283 */     this.previdencia.clear();
/* 284 */     this.numDependentes.clear();
/* 285 */     this.dependentes.clear();
/* 286 */     this.pensao.clear();
/* 287 */     this.livroCaixa.clear();
/* 288 */     this.impostoPagoCompensarExterior.clear();
/* 289 */     this.carneLeao.clear();
/* 290 */     this.darfPago.clear();
/*     */     
/* 292 */     this.contasMes.setObservadoresAtivos(true);
/* 293 */     this.pessoaFisica.setObservadoresAtivos(true);
/* 294 */     this.alugueis.setObservadoresAtivos(true);
/* 295 */     this.outros.setObservadoresAtivos(true);
/* 296 */     this.exterior.setObservadoresAtivos(true);
/* 297 */     this.previdencia.setObservadoresAtivos(true);
/* 298 */     this.numDependentes.setObservadoresAtivos(true);
/* 299 */     this.dependentes.setObservadoresAtivos(true);
/* 300 */     this.pensao.setObservadoresAtivos(true);
/* 301 */     this.livroCaixa.setObservadoresAtivos(true);
/* 302 */     this.impostoPagoCompensarExterior.setObservadoresAtivos(true);
/* 303 */     this.carneLeao.setObservadoresAtivos(true);
/* 304 */     this.darfPago.setObservadoresAtivos(true);
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendpf\MesRendPF.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */