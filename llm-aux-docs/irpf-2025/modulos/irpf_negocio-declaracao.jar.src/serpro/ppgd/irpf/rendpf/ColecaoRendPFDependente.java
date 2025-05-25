/*     */ package serpro.ppgd.irpf.rendpf;
/*     */ 
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.CPF;
/*     */ import serpro.ppgd.negocio.Colecao;
/*     */ import serpro.ppgd.negocio.Logico;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Pendencia;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ import serpro.ppgd.negocio.ValidadorIf;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ import serpro.ppgd.negocio.util.FabricaUtilitarios;
/*     */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorNaoNulo;
/*     */ 
/*     */ public class ColecaoRendPFDependente
/*     */   extends Colecao<ItemRendPFDependente>
/*     */ {
/*     */   private WeakReference<DeclaracaoIRPF> refDeclaracao;
/*  25 */   private Valor totalPessoaFisica = new Valor((ObjetoNegocio)this, "Total Pessoa Física");
/*  26 */   private Valor totalAlugueis = new Valor((ObjetoNegocio)this, "Total Alugueis");
/*  27 */   private Valor totalOutros = new Valor((ObjetoNegocio)this, "Total Outros");
/*  28 */   private Valor totalExterior = new Valor((ObjetoNegocio)this, "Total Exterior");
/*  29 */   private Valor totalPrevidencia = new Valor((ObjetoNegocio)this, "Total Previdência");
/*  30 */   private Valor totalDependentes = new Valor((ObjetoNegocio)this, "Total Dependentes");
/*  31 */   private Valor totalPensao = new Valor((ObjetoNegocio)this, "Total Pensão");
/*  32 */   private Valor totalLivroCaixa = new Valor((ObjetoNegocio)this, "Total Livro Caixa");
/*  33 */   private Valor totalImpostoPagoExteriorCompensar = new Valor((ObjetoNegocio)this, "Total Imposto Pago no Exterior a Compensar");
/*  34 */   private Valor totalDarfPago = new Valor((ObjetoNegocio)this, "Total DARF");
/*     */   
/*  36 */   private Alfa usouImportacaoCarneLeaoWeb = new Alfa((ObjetoNegocio)this, "");
/*     */   
/*     */   public ColecaoRendPFDependente(DeclaracaoIRPF dec) {
/*  39 */     this.refDeclaracao = new WeakReference<>(dec);
/*  40 */     setFicha("Rendimentos Tributáveis Recebidos de PF e do Exterior - Dependentes");
/*     */     
/*  42 */     this.usouImportacaoCarneLeaoWeb.setConteudo(Logico.NAO);
/*     */   }
/*     */ 
/*     */   
/*     */   public void objetoInserido(final ItemRendPFDependente item) {
/*  47 */     item.addValidador(this.refDeclaracao.get());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  52 */     item.getRendimentos().getNITPISPASEP().addValidador((ValidadorIf)new ValidadorNaoNulo((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/*  55 */             if (item.getRendimentos().getNITPISPASEP().isVazio() && item.getRendimentos().getTotalPrevidencia().comparacao(">", "0,00")) {
/*  56 */               return new RetornoValidacao(MensagemUtil.getMensagem("contribuinte_previdencia_pf_sem_nit_pis_pasep"), getSeveridade());
/*     */             }
/*  58 */             return null;
/*     */           }
/*     */         });
/*  61 */     item.aplicaNomeFicha();
/*     */   }
/*     */   
/*     */   public Boolean existeContribuicaoPrevidenciariaPorDependente(CPF cpf) {
/*  65 */     Boolean retorno = Boolean.valueOf(false);
/*     */     
/*  67 */     Iterator<ItemRendPFDependente> it = itens().iterator();
/*  68 */     while (it.hasNext()) {
/*  69 */       ItemRendPFDependente rend = it.next();
/*  70 */       if (rend.getCpf().naoFormatado().equals(cpf.naoFormatado()) && rend.getRendimentos().existeContribuicaoPrevidenciaria()) {
/*  71 */         retorno = Boolean.valueOf(true);
/*     */         break;
/*     */       } 
/*     */     } 
/*  75 */     return retorno;
/*     */   }
/*     */   
/*     */   public Valor getTotalDarfPago() {
/*  79 */     return this.totalDarfPago;
/*     */   }
/*     */   
/*     */   public Valor getTotalDependentes() {
/*  83 */     return this.totalDependentes;
/*     */   }
/*     */   
/*     */   public Valor getTotalExterior() {
/*  87 */     return this.totalExterior;
/*     */   }
/*     */   
/*     */   public Valor getTotalLivroCaixa() {
/*  91 */     return this.totalLivroCaixa;
/*     */   }
/*     */   
/*     */   public Valor getTotalPensao() {
/*  95 */     return this.totalPensao;
/*     */   }
/*     */   
/*     */   public Valor getTotalPrevidencia() {
/*  99 */     return this.totalPrevidencia;
/*     */   }
/*     */   
/*     */   public Valor getTotalPessoaFisica() {
/* 103 */     return this.totalPessoaFisica;
/*     */   }
/*     */   
/*     */   public boolean todosCpfsPreenchidos() {
/* 107 */     boolean preenchido = true;
/*     */     
/* 109 */     Iterator<ItemRendPFDependente> it = itens().iterator();
/* 110 */     while (it.hasNext() && preenchido) {
/* 111 */       ItemRendPFDependente item = it.next();
/* 112 */       preenchido = (preenchido && !item.getCpf().isVazio());
/*     */     } 
/*     */     
/* 115 */     return preenchido;
/*     */   }
/*     */ 
/*     */   
/*     */   public ColecaoCPFDependentes getCpfDependentes() {
/* 120 */     ColecaoCPFDependentes list = new ColecaoCPFDependentes();
/*     */     
/* 122 */     Iterator<ItemRendPFDependente> it = itens().iterator();
/* 123 */     while (it.hasNext()) {
/* 124 */       ItemRendPFDependente item = it.next();
/* 125 */       CPFDependente dep = new CPFDependente();
/* 126 */       dep.getCpf().setConteudo(item.getCpf().naoFormatado());
/* 127 */       list.itens().add(dep);
/*     */     } 
/*     */     
/* 130 */     return list;
/*     */   }
/*     */   
/*     */   public boolean possuiMaisDeUmDependenteComCPF(String cpf) {
/* 134 */     int contador = 0;
/* 135 */     if ("".equals(cpf.trim())) {
/* 136 */       return false;
/*     */     }
/*     */     
/* 139 */     Iterator<ItemRendPFDependente> it = itens().iterator();
/* 140 */     while (it.hasNext()) {
/* 141 */       ItemRendPFDependente item = it.next();
/*     */       
/* 143 */       if (cpf.equals(item.getCpf().naoFormatado())) {
/* 144 */         contador++;
/*     */       }
/*     */     } 
/* 147 */     if (contador > 1) {
/* 148 */       return true;
/*     */     }
/* 150 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean possuiDependenteComCPF(String cpf) {
/* 156 */     if ("".equals(cpf.trim())) {
/* 157 */       return false;
/*     */     }
/*     */     
/* 160 */     Iterator<ItemRendPFDependente> it = itens().iterator();
/* 161 */     while (it.hasNext()) {
/* 162 */       ItemRendPFDependente item = it.next();
/*     */       
/* 164 */       if (cpf.equals(item.getCpf().naoFormatado())) {
/* 165 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 169 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void excluirDependentesComCPF(String cpf) {
/* 174 */     Iterator<ItemRendPFDependente> it = itens().iterator();
/* 175 */     while (it.hasNext()) {
/* 176 */       ItemRendPFDependente item = it.next();
/*     */       
/* 178 */       if (cpf.equals(item.getCpf().naoFormatado())) {
/* 179 */         it.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public Valor obterRendimentosRecebidosPorDependente(CPF cpf) {
/* 185 */     Valor retorno = new Valor();
/*     */     
/* 187 */     Iterator<ItemRendPFDependente> it = itens().iterator();
/* 188 */     while (it.hasNext()) {
/* 189 */       ItemRendPFDependente rend = it.next();
/* 190 */       if (rend.getCpf().naoFormatado().equals(cpf.naoFormatado())) {
/* 191 */         retorno.append('+', rend.getRendimentos().getTotalPessoaFisica());
/* 192 */         retorno.append('+', rend.getRendimentos().getTotalExterior());
/* 193 */         retorno.append('+', rend.getRendimentos().getTotalAlugueis());
/* 194 */         retorno.append('+', rend.getRendimentos().getTotalOutros());
/*     */       } 
/*     */     } 
/* 197 */     return retorno;
/*     */   }
/*     */   
/*     */   public Valor obterPrevidenciaPorDependente(CPF cpf) {
/* 201 */     Valor retorno = new Valor();
/*     */     
/* 203 */     Iterator<ItemRendPFDependente> it = itens().iterator();
/* 204 */     while (it.hasNext()) {
/* 205 */       ItemRendPFDependente rend = it.next();
/* 206 */       if (rend.getCpf().naoFormatado().equals(cpf.naoFormatado())) {
/* 207 */         retorno.append('+', rend.getRendimentos().getTotalPrevidencia());
/*     */       }
/*     */     } 
/* 210 */     return retorno;
/*     */   }
/*     */   
/*     */   public Valor obterLivroCaixaPorDependente(CPF cpf) {
/* 214 */     Valor retorno = new Valor();
/*     */     
/* 216 */     Iterator<ItemRendPFDependente> it = itens().iterator();
/* 217 */     while (it.hasNext()) {
/* 218 */       ItemRendPFDependente rend = it.next();
/* 219 */       if (rend.getCpf().naoFormatado().equals(cpf.naoFormatado())) {
/* 220 */         retorno.append('+', rend.getRendimentos().getTotalLivroCaixa());
/*     */       }
/*     */     } 
/* 223 */     return retorno;
/*     */   }
/*     */   
/*     */   public ContasAno obterContasAnoPorCPF(String cpf) {
/* 227 */     ContasAno contasAno = null;
/* 228 */     for (ItemRendPFDependente item : itens()) {
/* 229 */       if (item.getCpf().naoFormatado().equals(cpf)) {
/* 230 */         contasAno = item.getRendimentos().getContasAno();
/*     */         break;
/*     */       } 
/*     */     } 
/* 234 */     return contasAno;
/*     */   }
/*     */   
/*     */   public ItemRendPFDependente obterItemRendPFDependentePorCPF(String cpf) {
/* 238 */     ItemRendPFDependente lItem = null;
/* 239 */     for (ItemRendPFDependente item : itens()) {
/* 240 */       if (item.getCpf().naoFormatado().equals(cpf)) {
/* 241 */         lItem = item;
/*     */         break;
/*     */       } 
/*     */     } 
/* 245 */     return lItem;
/*     */   }
/*     */   
/*     */   public Valor getTotalAlugueis() {
/* 249 */     return this.totalAlugueis;
/*     */   }
/*     */   
/*     */   public Valor getTotalOutros() {
/* 253 */     return this.totalOutros;
/*     */   }
/*     */   
/*     */   public Valor getTotalImpostoPagoExteriorCompensar() {
/* 257 */     return this.totalImpostoPagoExteriorCompensar;
/*     */   }
/*     */   
/*     */   public Alfa getUsouImportacaoCarneLeaoWeb() {
/* 261 */     return this.usouImportacaoCarneLeaoWeb;
/*     */   }
/*     */   
/*     */   private List<Pendencia> getListaPendencias() {
/* 265 */     List<Pendencia> pendencias = new ArrayList<>();
/* 266 */     for (ItemRendPFDependente lItem : itens()) {
/* 267 */       for (ContasMes mes : lItem.getRendimentos().getContasAno().getColecaoEscrituracao().itens()) {
/* 268 */         pendencias.addAll(FabricaUtilitarios.verificarPendencias(mes));
/*     */       }
/*     */     } 
/* 271 */     return pendencias;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Pendencia> verificarPendencias(int numeroItem) {
/* 276 */     List<Pendencia> pendencias = super.verificarPendencias(numeroItem);
/* 277 */     pendencias.addAll(getListaPendencias());
/* 278 */     return pendencias;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendpf\ColecaoRendPFDependente.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */