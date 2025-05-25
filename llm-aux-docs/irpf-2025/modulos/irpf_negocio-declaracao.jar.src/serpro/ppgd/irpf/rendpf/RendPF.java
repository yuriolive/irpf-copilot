/*     */ package serpro.ppgd.irpf.rendpf;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import serpro.ppgd.irpf.gui.rendpf.PainelDadosEscrituracao;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.Logico;
/*     */ import serpro.ppgd.negocio.ObjetoFicha;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ import serpro.ppgd.negocio.ValidadorIf;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorNIT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RendPF
/*     */   extends ObjetoNegocio
/*     */   implements ObjetoFicha
/*     */ {
/*     */   public static final String FICHA_TIT = "Rendimentos Tributáveis Recebidos de PF e do Exterior - Titular";
/*     */   public static final String FICHA_DEP = "Rendimentos Tributáveis Recebidos de PF e do Exterior - Dependentes";
/*     */   public static final String NOME_TOTAL_PF = "Total Pessoa Física";
/*     */   public static final String NOME_TOTAL_ALUGUEIS = "Total Alugueis";
/*     */   public static final String NOME_TOTAL_OUTROS = "Total Outros";
/*     */   public static final String NOME_TOTAL_EXTERIOR = "Total Exterior";
/*     */   public static final String NOME_TOTAL_PREVIDENCIA = "Total Previdência";
/*     */   public static final String NOME_TOTAL_DEPENDENTES = "Total Dependentes";
/*     */   public static final String NOME_TOTAL_PENSAO = "Total Pensão";
/*     */   public static final String NOME_TOTAL_IMPOSTO_PAGO_COMPENSAR_EXTEIOR = "Total Imposto Pago no Exterior a Compensar";
/*     */   public static final String NOME_TOTAL_LIVROCAIXA = "Total Livro Caixa";
/*     */   public static final String NOME_TOTAL_DARF = "Total DARF";
/*     */   private MesRendPF janeiro;
/*     */   private MesRendPF fevereiro;
/*     */   private MesRendPF marco;
/*     */   private MesRendPF abril;
/*     */   private MesRendPF maio;
/*     */   private MesRendPF junho;
/*     */   private MesRendPF julho;
/*     */   private MesRendPF agosto;
/*     */   private MesRendPF setembro;
/*     */   private MesRendPF outubro;
/*     */   private MesRendPF novembro;
/*     */   private MesRendPF dezembro;
/*     */   protected MesRendPF[] meses;
/*  54 */   private Valor totalPessoaFisica = new Valor(this, "Total Pessoa Física");
/*  55 */   private Valor totalAlugueis = new Valor(this, "Total Alugueis");
/*  56 */   private Valor totalOutros = new Valor(this, "Total Outros");
/*  57 */   private Valor totalExterior = new Valor(this, "Total Exterior");
/*  58 */   private Valor totalPrevidencia = new Valor(this, "Total Previdência");
/*  59 */   private Valor totalDependentes = new Valor(this, "Total Dependentes");
/*  60 */   private Alfa totalNumDependentes = new Alfa(this, "");
/*  61 */   private Valor totalPensao = new Valor(this, "Total Pensão");
/*  62 */   private Valor totalImpostoPagoExteriorCompensar = new Valor(this, "Total Imposto Pago no Exterior a Compensar");
/*  63 */   private Valor totalLivroCaixa = new Valor(this, "Total Livro Caixa");
/*  64 */   private Valor totalDarfPago = new Valor(this, "Total DARF");
/*  65 */   private Alfa NITPISPASEP = new Alfa(this, "NIT/PIS/PASEP do contribuinte");
/*     */   
/*     */   private ContasAno contasAno;
/*     */   
/*  69 */   private Alfa usouImportacaoCarneLeaoWeb = new Alfa(this, "");
/*     */ 
/*     */   
/*     */   public RendPF() {
/*  73 */     getNITPISPASEP().addValidador((ValidadorIf)new ValidadorNIT((byte)3, MensagemUtil.getMensagem("nit_pis_pasep_invalido"))
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/*  76 */             if (!RendPF.this.getNITPISPASEP().isVazio()) {
/*  77 */               return super.validarImplementado();
/*     */             }
/*  79 */             return null;
/*     */           }
/*     */         });
/*  82 */     this.contasAno = new ContasAno();
/*  83 */     this.janeiro = new MesRendPF(0, getContasAno().getArrayMeses()[0]);
/*  84 */     this.fevereiro = new MesRendPF(1, getContasAno().getArrayMeses()[1]);
/*  85 */     this.marco = new MesRendPF(2, getContasAno().getArrayMeses()[2]);
/*  86 */     this.abril = new MesRendPF(3, getContasAno().getArrayMeses()[3]);
/*  87 */     this.maio = new MesRendPF(4, getContasAno().getArrayMeses()[4]);
/*  88 */     this.junho = new MesRendPF(5, getContasAno().getArrayMeses()[5]);
/*  89 */     this.julho = new MesRendPF(6, getContasAno().getArrayMeses()[6]);
/*  90 */     this.agosto = new MesRendPF(7, getContasAno().getArrayMeses()[7]);
/*  91 */     this.setembro = new MesRendPF(8, getContasAno().getArrayMeses()[8]);
/*  92 */     this.outubro = new MesRendPF(9, getContasAno().getArrayMeses()[9]);
/*  93 */     this.novembro = new MesRendPF(10, getContasAno().getArrayMeses()[10]);
/*  94 */     this.dezembro = new MesRendPF(11, getContasAno().getArrayMeses()[11]);
/*  95 */     this.meses = new MesRendPF[] { this.janeiro, this.fevereiro, this.marco, this.abril, this.maio, this.junho, this.julho, this.agosto, this.setembro, this.outubro, this.novembro, this.dezembro };
/*  96 */     aplicaNomeFicha();
/*     */     
/*  98 */     this.usouImportacaoCarneLeaoWeb.setConteudo(Logico.NAO);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void limparTrabalhoNaoAssalariadoSemLancamento() {
/* 106 */     for (int i = 0; i < 12; i++) {
/* 107 */       if (this.contasAno.getArrayMeses()[i].itens().size() == 0) {
/* 108 */         this.meses[i].getPessoaFisica().clear();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public List recuperarListaCamposPendencia() {
/* 115 */     List<Informacao> campos = new ArrayList<>();
/* 116 */     campos.add(getNITPISPASEP());
/* 117 */     return campos;
/*     */   }
/*     */ 
/*     */   
/*     */   public void aplicaNomeFicha() {
/* 122 */     setFicha("Rendimentos Tributáveis Recebidos de PF e do Exterior - Titular");
/*     */     
/* 124 */     for (int i = 0; i < 12; i++) {
/* 125 */       this.meses[i].setFicha("Rendimentos Tributáveis Recebidos de PF e do Exterior - Titular");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public MesRendPF getMesRendPFPorIndice(int mes) {
/* 131 */     if (mes == 0)
/* 132 */       return this.janeiro; 
/* 133 */     if (mes == 1)
/* 134 */       return this.fevereiro; 
/* 135 */     if (mes == 2)
/* 136 */       return this.marco; 
/* 137 */     if (mes == 3)
/* 138 */       return this.abril; 
/* 139 */     if (mes == 4)
/* 140 */       return this.maio; 
/* 141 */     if (mes == 5)
/* 142 */       return this.junho; 
/* 143 */     if (mes == 6)
/* 144 */       return this.julho; 
/* 145 */     if (mes == 7)
/* 146 */       return this.agosto; 
/* 147 */     if (mes == 8)
/* 148 */       return this.setembro; 
/* 149 */     if (mes == 9)
/* 150 */       return this.outubro; 
/* 151 */     if (mes == 10)
/* 152 */       return this.novembro; 
/* 153 */     if (mes == 11) {
/* 154 */       return this.dezembro;
/*     */     }
/*     */     
/* 157 */     return null;
/*     */   }
/*     */   
/*     */   public int obterMesFormatoNumerico(MesRendPF ganhos) {
/* 161 */     if (ganhos.equals(this.janeiro))
/* 162 */       return 0; 
/* 163 */     if (ganhos.equals(this.fevereiro))
/* 164 */       return 1; 
/* 165 */     if (ganhos.equals(this.marco))
/* 166 */       return 2; 
/* 167 */     if (ganhos.equals(this.abril))
/* 168 */       return 3; 
/* 169 */     if (ganhos.equals(this.maio))
/* 170 */       return 4; 
/* 171 */     if (ganhos.equals(this.junho))
/* 172 */       return 5; 
/* 173 */     if (ganhos.equals(this.julho))
/* 174 */       return 6; 
/* 175 */     if (ganhos.equals(this.agosto))
/* 176 */       return 7; 
/* 177 */     if (ganhos.equals(this.setembro))
/* 178 */       return 8; 
/* 179 */     if (ganhos.equals(this.outubro))
/* 180 */       return 9; 
/* 181 */     if (ganhos.equals(this.novembro))
/* 182 */       return 10; 
/* 183 */     if (ganhos.equals(this.dezembro)) {
/* 184 */       return 11;
/*     */     }
/* 186 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean existeContribuicaoPrevidenciaria() {
/* 190 */     boolean retorno = false;
/*     */     
/* 192 */     for (int i = 0; i < 12; i++) {
/* 193 */       if (!getMesRendPFPorIndice(i).getPrevidencia().isVazio()) {
/* 194 */         retorno = true;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 199 */     return retorno;
/*     */   }
/*     */   
/*     */   public void addObservador(Observador obs) {
/* 203 */     this.janeiro.addObservador(obs);
/* 204 */     this.fevereiro.addObservador(obs);
/* 205 */     this.marco.addObservador(obs);
/* 206 */     this.abril.addObservador(obs);
/* 207 */     this.maio.addObservador(obs);
/* 208 */     this.junho.addObservador(obs);
/* 209 */     this.julho.addObservador(obs);
/* 210 */     this.agosto.addObservador(obs);
/* 211 */     this.setembro.addObservador(obs);
/* 212 */     this.outubro.addObservador(obs);
/* 213 */     this.novembro.addObservador(obs);
/* 214 */     this.dezembro.addObservador(obs);
/*     */   }
/*     */   
/*     */   public void removeObservador(Observador obs) {
/* 218 */     this.janeiro.removeObservador(obs);
/* 219 */     this.fevereiro.removeObservador(obs);
/* 220 */     this.marco.removeObservador(obs);
/* 221 */     this.abril.removeObservador(obs);
/* 222 */     this.maio.removeObservador(obs);
/* 223 */     this.junho.removeObservador(obs);
/* 224 */     this.julho.removeObservador(obs);
/* 225 */     this.agosto.removeObservador(obs);
/* 226 */     this.setembro.removeObservador(obs);
/* 227 */     this.outubro.removeObservador(obs);
/* 228 */     this.novembro.removeObservador(obs);
/* 229 */     this.dezembro.removeObservador(obs);
/*     */   }
/*     */   
/*     */   public MesRendPF getAbril() {
/* 233 */     return this.abril;
/*     */   }
/*     */   
/*     */   public MesRendPF getAgosto() {
/* 237 */     return this.agosto;
/*     */   }
/*     */   
/*     */   public MesRendPF getDezembro() {
/* 241 */     return this.dezembro;
/*     */   }
/*     */   
/*     */   public MesRendPF getFevereiro() {
/* 245 */     return this.fevereiro;
/*     */   }
/*     */   
/*     */   public MesRendPF getJaneiro() {
/* 249 */     return this.janeiro;
/*     */   }
/*     */   
/*     */   public MesRendPF getJulho() {
/* 253 */     return this.julho;
/*     */   }
/*     */   
/*     */   public MesRendPF getJunho() {
/* 257 */     return this.junho;
/*     */   }
/*     */   
/*     */   public MesRendPF getMaio() {
/* 261 */     return this.maio;
/*     */   }
/*     */   
/*     */   public MesRendPF getMarco() {
/* 265 */     return this.marco;
/*     */   }
/*     */   
/*     */   public MesRendPF getNovembro() {
/* 269 */     return this.novembro;
/*     */   }
/*     */   
/*     */   public MesRendPF getOutubro() {
/* 273 */     return this.outubro;
/*     */   }
/*     */   
/*     */   public MesRendPF getSetembro() {
/* 277 */     return this.setembro;
/*     */   }
/*     */   
/*     */   public Valor getTotalDarfPago() {
/* 281 */     return this.totalDarfPago;
/*     */   }
/*     */   
/*     */   public Valor getTotalDependentes() {
/* 285 */     return this.totalDependentes;
/*     */   }
/*     */   
/*     */   public Valor getTotalExterior() {
/* 289 */     return this.totalExterior;
/*     */   }
/*     */   
/*     */   public Valor getTotalLivroCaixa() {
/* 293 */     return this.totalLivroCaixa;
/*     */   }
/*     */   
/*     */   public Valor getTotalPensao() {
/* 297 */     return this.totalPensao;
/*     */   }
/*     */   
/*     */   public Valor getTotalPessoaFisica() {
/* 301 */     return this.totalPessoaFisica;
/*     */   }
/*     */   
/*     */   public Valor getTotalPrevidencia() {
/* 305 */     return this.totalPrevidencia;
/*     */   }
/*     */   
/*     */   public Alfa getNITPISPASEP() {
/* 309 */     return this.NITPISPASEP;
/*     */   }
/*     */   
/*     */   public ContasAno getContasAno() {
/* 313 */     return this.contasAno;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVazio() {
/* 318 */     return (this.janeiro.isVazio() && this.fevereiro.isVazio() && this.marco.isVazio() && this.abril.isVazio() && this.maio
/* 319 */       .isVazio() && this.junho.isVazio() && this.julho.isVazio() && this.agosto.isVazio() && this.setembro
/* 320 */       .isVazio() && this.outubro.isVazio() && this.novembro.isVazio() && this.dezembro.isVazio() && this.contasAno
/* 321 */       .isVazio() && this.NITPISPASEP.isVazio());
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/* 326 */     super.clear();
/*     */     
/* 328 */     this.janeiro.clear();
/* 329 */     this.fevereiro.clear();
/* 330 */     this.marco.clear();
/* 331 */     this.abril.clear();
/* 332 */     this.maio.clear();
/* 333 */     this.junho.clear();
/* 334 */     this.julho.clear();
/* 335 */     this.agosto.clear();
/* 336 */     this.setembro.clear();
/* 337 */     this.outubro.clear();
/* 338 */     this.novembro.clear();
/* 339 */     this.dezembro.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClasseFicha() {
/* 344 */     return PainelDadosEscrituracao.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 349 */     if (getFicha().equals("Rendimentos Tributáveis Recebidos de PF e do Exterior - Titular")) {
/* 350 */       return "Outras Informações";
/*     */     }
/* 352 */     return "Outras Informações";
/*     */   }
/*     */ 
/*     */   
/*     */   public Valor getTotalAlugueis() {
/* 357 */     return this.totalAlugueis;
/*     */   }
/*     */   
/*     */   public Valor getTotalOutros() {
/* 361 */     return this.totalOutros;
/*     */   }
/*     */   
/*     */   public Valor getTotalImpostoPagoExteriorCompensar() {
/* 365 */     return this.totalImpostoPagoExteriorCompensar;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 370 */     return "Rendimentos Tributáveis Recebidos de PF e do Exterior";
/*     */   }
/*     */   
/*     */   public Alfa getTotalNumDependentes() {
/* 374 */     return this.totalNumDependentes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Alfa getUsouImportacaoCarneLeaoWeb() {
/* 382 */     return this.usouImportacaoCarneLeaoWeb;
/*     */   }
/*     */   
/*     */   public void limpar() {
/* 386 */     this.totalPessoaFisica.setObservadoresAtivos(false);
/* 387 */     this.totalAlugueis.setObservadoresAtivos(false);
/* 388 */     this.totalOutros.setObservadoresAtivos(false);
/* 389 */     this.totalExterior.setObservadoresAtivos(false);
/* 390 */     this.totalPrevidencia.setObservadoresAtivos(false);
/* 391 */     this.totalDependentes.setObservadoresAtivos(false);
/* 392 */     this.totalNumDependentes.setObservadoresAtivos(false);
/* 393 */     this.totalPensao.setObservadoresAtivos(false);
/* 394 */     this.totalImpostoPagoExteriorCompensar.setObservadoresAtivos(false);
/* 395 */     this.totalLivroCaixa.setObservadoresAtivos(false);
/* 396 */     this.totalDarfPago.setObservadoresAtivos(false);
/*     */     
/* 398 */     this.totalPessoaFisica.clear();
/* 399 */     this.totalAlugueis.clear();
/* 400 */     this.totalOutros.clear();
/* 401 */     this.totalExterior.clear();
/* 402 */     this.totalPrevidencia.clear();
/* 403 */     this.totalDependentes.clear();
/* 404 */     this.totalNumDependentes.clear();
/* 405 */     this.totalPensao.clear();
/* 406 */     this.totalImpostoPagoExteriorCompensar.clear();
/* 407 */     this.totalLivroCaixa.clear();
/* 408 */     this.totalDarfPago.clear();
/*     */     
/* 410 */     this.totalPessoaFisica.setObservadoresAtivos(true);
/* 411 */     this.totalAlugueis.setObservadoresAtivos(true);
/* 412 */     this.totalOutros.setObservadoresAtivos(true);
/* 413 */     this.totalExterior.setObservadoresAtivos(true);
/* 414 */     this.totalPrevidencia.setObservadoresAtivos(true);
/* 415 */     this.totalDependentes.setObservadoresAtivos(true);
/* 416 */     this.totalNumDependentes.setObservadoresAtivos(true);
/* 417 */     this.totalPensao.setObservadoresAtivos(true);
/* 418 */     this.totalImpostoPagoExteriorCompensar.setObservadoresAtivos(true);
/* 419 */     this.totalLivroCaixa.setObservadoresAtivos(true);
/* 420 */     this.totalDarfPago.setObservadoresAtivos(true);
/*     */     
/* 422 */     for (MesRendPF mes : this.meses)
/* 423 */       mes.limpar(); 
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendpf\RendPF.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */