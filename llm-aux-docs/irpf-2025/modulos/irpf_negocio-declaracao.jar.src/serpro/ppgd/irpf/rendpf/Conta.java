/*     */ package serpro.ppgd.irpf.rendpf;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.gui.rendpf.PainelDadosEscrituracao;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.CPF;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.Logico;
/*     */ import serpro.ppgd.negocio.ObjetoFicha;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ import serpro.ppgd.negocio.ValidadorDefault;
/*     */ import serpro.ppgd.negocio.ValidadorIf;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ import serpro.ppgd.negocio.util.LogPPGD;
/*     */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorCPF;
/*     */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorNaoNulo;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Conta
/*     */   extends ObjetoNegocio
/*     */   implements ObjetoFicha
/*     */ {
/*     */   public static final String TITULO_FICHA_TITULAR = "Rendimentos Tributáveis Recebidos de PF e do Exterior - Titular";
/*     */   public static final String TITULO_FICHA_DEPENDENTES = "Rendimentos Tributáveis Recebidos de PF e do Exterior - Dependentes";
/*  32 */   private Alfa dataMesAno = new Alfa(this, "dataMesAno", 6);
/*  33 */   private Valor valor = new Valor(this, "ValorR$");
/*  34 */   private Alfa nomeMes = new Alfa(this, "Nome do Mes");
/*  35 */   private CPF cpfTitularPagamento = new CPF(this, "Titular do Pagamento");
/*  36 */   private Alfa indTitularEhBeneficiario = new Alfa(this, "Titular é beneficiário");
/*  37 */   private CPF cpfBeneficiarioServico = new CPF(this, "Beneficiário do Serviço");
/*  38 */   private Alfa indBeneficiarioNaoPossuiCPF = new Alfa(this, "Beneficiário não possui CPF");
/*  39 */   private CPF cpfContribuinte = new CPF(this, "CPF do Contribuinte");
/*  40 */   private transient CPF cpfDeclaranteIRPF = new CPF(this, "CPF do Declarante do IRPF");
/*     */ 
/*     */   
/*     */   public Conta() {
/*  44 */     this.indTitularEhBeneficiario.setConteudo(Logico.NAO);
/*  45 */     this.indBeneficiarioNaoPossuiCPF.setConteudo(Logico.NAO);
/*     */     
/*  47 */     this.valor.addValidador((ValidadorIf)new ValidadorNaoNulo((byte)3) {
/*     */           public RetornoValidacao validarImplementado() {
/*  49 */             if (Conta.this.valor.comparacao("<=", "0")) {
/*  50 */               return new RetornoValidacao(MensagemUtil.getMensagem("valor_nao_positivo"), (byte)3);
/*     */             }
/*  52 */             return null;
/*     */           }
/*     */         });
/*     */     
/*  56 */     this.cpfTitularPagamento.addValidador((ValidadorIf)new ValidadorCPF((byte)3));
/*  57 */     this.cpfTitularPagamento.addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/*  60 */             if (getInformacao().naoFormatado().equals(Conta.this.cpfContribuinte.naoFormatado())) {
/*  61 */               return new RetornoValidacao(MensagemUtil.getMensagem("400010"), (byte)3);
/*     */             }
/*  63 */             return null;
/*     */           }
/*     */         });
/*     */     
/*  67 */     this.cpfTitularPagamento.addValidador((ValidadorIf)new ValidadorNaoNulo((byte)3, MensagemUtil.getMensagem("msg_validador_nao_nulo", new String[] {
/*  68 */               getCpfTitularPagamento().getNomeCampo()
/*     */             }))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/*  79 */             if (IRPFFacade.getInstancia().getIdDeclaracaoAberto() != null) {
/*  80 */               boolean isOcupacaoComRegProfissionalObrigatorio = IRPFFacade.getInstancia().getContribuinte().isOcupacaoComRegistroProfissionalObrigatorio(IRPFFacade.getInstancia().getIdDeclaracaoAberto().getTipoDeclaracaoAES().naoFormatado());
/*  81 */               boolean heTitular = IRPFFacade.getInstancia().getIdDeclaracaoAberto().getCpf().naoFormatado().equals(Conta.this.getCpfContribuinte().naoFormatado());
/*  82 */               if (getInformacao().isVazio() && isOcupacaoComRegProfissionalObrigatorio && heTitular) {
/*  83 */                 return super.validarImplementado();
/*     */               }
/*  85 */             } else if (IRPFFacade.getInstancia().getDeclaracaoEmGravacao() != null) {
/*  86 */               boolean isOcupacaoComRegProfissionalObrigatorio = IRPFFacade.getInstancia().getDeclaracaoEmGravacao().getContribuinte().isOcupacaoComRegistroProfissionalObrigatorio(IRPFFacade.getInstancia().getDeclaracaoEmGravacao().getIdentificadorDeclaracao().getTipoDeclaracaoAES().naoFormatado());
/*  87 */               boolean heTitular = IRPFFacade.getInstancia().getDeclaracaoEmGravacao().getIdentificadorDeclaracao().getCpf().naoFormatado().equals(Conta.this.getCpfContribuinte().naoFormatado());
/*  88 */               if (getInformacao().isVazio() && isOcupacaoComRegProfissionalObrigatorio && heTitular) {
/*  89 */                 Conta.this.setFicha("Rendimentos Tributáveis Recebidos de PF e do Exterior - Titular");
/*  90 */                 return super.validarImplementado();
/*     */               } 
/*     */             } 
/*  93 */             return null;
/*     */           }
/*     */         });
/*     */     
/*  97 */     this.cpfBeneficiarioServico.addValidador((ValidadorIf)new ValidadorCPF((byte)3));
/*  98 */     this.cpfBeneficiarioServico.addValidador((ValidadorIf)new ValidadorNaoNulo((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/* 101 */             if (!getInformacao().isVazio() && 
/* 102 */               getInformacao().naoFormatado().equals(Conta.this.cpfContribuinte.naoFormatado())) {
/* 103 */               return new RetornoValidacao(MensagemUtil.getMensagem("400020"), (byte)3);
/*     */             }
/* 105 */             return null;
/*     */           }
/*     */         });
/*     */     
/* 109 */     this.cpfBeneficiarioServico.addValidador((ValidadorIf)new ValidadorNaoNulo((byte)3, MensagemUtil.getMensagem("msg_validador_nao_nulo", new String[] {
/* 110 */               getCpfBeneficiarioServico().getNomeCampo()
/*     */             }))
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/* 114 */             if (!Conta.this.cpfTitularPagamento.isVazio() && !Conta.this.indBeneficiarioNaoPossuiCPF.naoFormatado().equals(Logico.SIM))
/*     */             {
/* 116 */               return super.validarImplementado();
/*     */             }
/* 118 */             return null;
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 123 */     this.cpfTitularPagamento.addObservador(new Observador()
/*     */         {
/*     */           
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/* 128 */             if (Conta.this.getIndTitularEhBeneficiario().naoFormatado().equals(Logico.SIM))
/*     */             {
/* 130 */               Conta.this.getCpfBeneficiarioServico().setConteudo(Conta.this.getCpfTitularPagamento().naoFormatado());
/*     */             }
/*     */           }
/*     */         });
/*     */     
/* 135 */     this.dataMesAno.addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/*     */             try {
/* 139 */               Conta.this.nomeMes.setConteudo(Conta.this.getNomeMesExtenso(Integer.valueOf(((String)valorNovo).substring(0, 2)).intValue()));
/* 140 */             } catch (Exception ex) {
/*     */               
/* 142 */               LogPPGD.erro(ex.getMessage());
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public List recuperarListaCamposPendencia() {
/* 150 */     List<Informacao> campos = new ArrayList<>();
/* 151 */     campos.add(getCpfTitularPagamento());
/* 152 */     campos.add(getCpfBeneficiarioServico());
/* 153 */     campos.add(getValor());
/* 154 */     return campos;
/*     */   }
/*     */   
/*     */   public Conta obterCopia() {
/* 158 */     Conta conta = new Conta();
/* 159 */     conta.getCpfContribuinte().setConteudo(getCpfContribuinte());
/* 160 */     conta.getCpfBeneficiarioServico().setConteudo(getCpfBeneficiarioServico());
/* 161 */     conta.getCpfTitularPagamento().setConteudo(getCpfTitularPagamento());
/* 162 */     conta.getIndBeneficiarioNaoPossuiCPF().setConteudo(getIndBeneficiarioNaoPossuiCPF());
/* 163 */     conta.getIndTitularEhBeneficiario().setConteudo(getIndTitularEhBeneficiario());
/* 164 */     conta.getNomeMes().setConteudo(getNomeMes());
/* 165 */     conta.getDataMesAno().setConteudo(getDataMesAno());
/* 166 */     conta.getValor().setConteudo(getValor());
/* 167 */     return conta;
/*     */   }
/*     */   
/*     */   public Alfa getDataMesAno() {
/* 171 */     return this.dataMesAno;
/*     */   }
/*     */   
/*     */   public Valor getValor() {
/* 175 */     return this.valor;
/*     */   }
/*     */   
/*     */   public int compareTo(Object o) {
/* 179 */     Conta outro = (Conta)o;
/*     */     
/* 181 */     if (getCpfTitularPagamento().isVazio() && outro.getCpfTitularPagamento().isVazio()) {
/* 182 */       return 0;
/*     */     }
/*     */     
/* 185 */     if (outro.getCpfTitularPagamento().isVazio())
/* 186 */       return -1; 
/* 187 */     if (getCpfTitularPagamento().isVazio()) {
/* 188 */       return 1;
/*     */     }
/*     */     
/* 191 */     return outro.getCpfTitularPagamento().naoFormatado().compareTo(getCpfTitularPagamento().naoFormatado());
/*     */   }
/*     */   
/*     */   public boolean isVazio() {
/* 195 */     return (getCpfTitularPagamento().isVazio() && getValor().isVazio());
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 199 */     return (this.valor.isVazio() && this.cpfTitularPagamento.isVazio() && this.cpfBeneficiarioServico.isVazio());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getClasseFicha() {
/* 205 */     return PainelDadosEscrituracao.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 210 */     return getFicha();
/*     */   }
/*     */   
/*     */   public Alfa getNomeMes() {
/* 214 */     return this.nomeMes;
/*     */   }
/*     */ 
/*     */   
/*     */   public Alfa converteNomeMesABC() {
/* 219 */     String convertido = "";
/* 220 */     Alfa nomeMesABC = new Alfa();
/*     */     
/* 222 */     switch (this.nomeMes.naoFormatado()) {
/*     */       case "1":
/* 224 */         convertido = "A";
/*     */         break;
/*     */       case "2":
/* 227 */         convertido = "B";
/*     */         break;
/*     */       case "3":
/* 230 */         convertido = "C";
/*     */         break;
/*     */       case "4":
/* 233 */         convertido = "D";
/*     */         break;
/*     */       case "5":
/* 236 */         convertido = "E";
/*     */         break;
/*     */       case "6":
/* 239 */         convertido = "F";
/*     */         break;
/*     */       case "7":
/* 242 */         convertido = "G";
/*     */         break;
/*     */       case "8":
/* 245 */         convertido = "H";
/*     */         break;
/*     */       case "9":
/* 248 */         convertido = "I";
/*     */         break;
/*     */       case "10":
/* 251 */         convertido = "J";
/*     */         break;
/*     */       case "11":
/* 254 */         convertido = "K";
/*     */         break;
/*     */       case "12":
/* 257 */         convertido = "L";
/*     */         break;
/*     */     } 
/*     */     
/* 261 */     nomeMesABC.setConteudo(convertido);
/*     */     
/* 263 */     return nomeMesABC;
/*     */   }
/*     */   
/*     */   public CPF getCpfTitularPagamento() {
/* 267 */     return this.cpfTitularPagamento;
/*     */   }
/*     */   
/*     */   public Alfa getIndTitularEhBeneficiario() {
/* 271 */     return this.indTitularEhBeneficiario;
/*     */   }
/*     */   
/*     */   public CPF getCpfBeneficiarioServico() {
/* 275 */     return this.cpfBeneficiarioServico;
/*     */   }
/*     */   
/*     */   public Alfa getIndBeneficiarioNaoPossuiCPF() {
/* 279 */     return this.indBeneficiarioNaoPossuiCPF;
/*     */   }
/*     */   
/*     */   public String getNomeMesExtenso(int mes) {
/* 283 */     String nomeMes = "";
/* 284 */     switch (Integer.valueOf(mes).intValue()) {
/*     */       case 1:
/* 286 */         nomeMes = "Janeiro";
/*     */         break;
/*     */       case 2:
/* 289 */         nomeMes = "Fevereiro";
/*     */         break;
/*     */       case 3:
/* 292 */         nomeMes = "Março";
/*     */         break;
/*     */       case 4:
/* 295 */         nomeMes = "Abril";
/*     */         break;
/*     */       case 5:
/* 298 */         nomeMes = "Maio";
/*     */         break;
/*     */       case 6:
/* 301 */         nomeMes = "Junho";
/*     */         break;
/*     */       case 7:
/* 304 */         nomeMes = "Julho";
/*     */         break;
/*     */       case 8:
/* 307 */         nomeMes = "Agosto";
/*     */         break;
/*     */       case 9:
/* 310 */         nomeMes = "Setembro";
/*     */         break;
/*     */       case 10:
/* 313 */         nomeMes = "Outubro";
/*     */         break;
/*     */       case 11:
/* 316 */         nomeMes = "Novembro";
/*     */         break;
/*     */       case 12:
/* 319 */         nomeMes = "Dezembro";
/*     */         break;
/*     */     } 
/* 322 */     return nomeMes;
/*     */   }
/*     */   
/*     */   public CPF getCpfContribuinte() {
/* 326 */     return this.cpfContribuinte;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 332 */     if (getFicha().indexOf("Titular") > -1) {
/* 333 */       return "Rendimentos Tributáveis Recebidos de PF e do Exterior - Titular";
/*     */     }
/* 335 */     return "Rendimentos Tributáveis Recebidos de PF e do Exterior - Dependente";
/*     */   }
/*     */ 
/*     */   
/*     */   public CPF getCpfDeclaranteIRPF() {
/* 340 */     return this.cpfDeclaranteIRPF;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendpf\Conta.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */