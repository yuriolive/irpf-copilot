/*     */ package serpro.ppgd.irpf.rendpj;
/*     */ 
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.util.ConstantesGlobaisIRPF;
/*     */ import serpro.ppgd.negocio.CPF;
/*     */ import serpro.ppgd.negocio.Logico;
/*     */ import serpro.ppgd.negocio.NI;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ public class ColecaoRendPJDependente
/*     */   extends ColecaoRendPJTitular
/*     */ {
/*  18 */   private WeakReference<DeclaracaoIRPF> declaracaoRef = null;
/*     */   
/*     */   public ColecaoRendPJDependente(DeclaracaoIRPF dec) {
/*  21 */     super(dec.getIdentificadorDeclaracao());
/*  22 */     this.declaracaoRef = new WeakReference<>(dec);
/*  23 */     setFicha("Rendimentos Tributáveis Recebidos de PJ pelos Dependentes");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RendPJDependente instanciaNovoObjeto() {
/*  31 */     DeclaracaoIRPF declaracaoIRPF = this.declaracaoRef.get();
/*  32 */     return new RendPJDependente(declaracaoIRPF);
/*     */   }
/*     */ 
/*     */   
/*     */   public RendPJDependente obterRendimentoPorChave(String niFontePagadora, String cpfBeneficiario) {
/*  37 */     for (RendPJTitular rend : itens()) {
/*  38 */       RendPJDependente rendDep = (RendPJDependente)rend;
/*  39 */       if (rendDep.getNIFontePagadora().naoFormatado().equals(niFontePagadora) && rendDep
/*  40 */         .getCpfDependente().naoFormatado().equals(cpfBeneficiario)) {
/*  41 */         return rendDep;
/*     */       }
/*     */     } 
/*  44 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean possuiDependenteComCPF(String cpf) {
/*  49 */     if ("".equals(cpf.trim())) {
/*  50 */       return false;
/*     */     }
/*     */     
/*  53 */     Iterator<RendPJTitular> it = itens().iterator();
/*  54 */     while (it.hasNext()) {
/*  55 */       RendPJDependente rend = (RendPJDependente)it.next();
/*  56 */       if (rend.getCpfDependente().naoFormatado().equals(cpf)) {
/*  57 */         return true;
/*     */       }
/*     */     } 
/*     */     
/*  61 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void excluirDependentesComCPF(String cpf) {
/*  66 */     Iterator<RendPJTitular> it = itens().iterator();
/*  67 */     while (it.hasNext()) {
/*  68 */       RendPJDependente rend = (RendPJDependente)it.next();
/*  69 */       if (rend.getCpfDependente().naoFormatado().equals(cpf)) {
/*  70 */         it.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public RendPJDependente getRendimentoVazioPorNIFontePagadora(CPF cpfDep, NI niFonte) {
/*  77 */     for (RendPJTitular obj : itens()) {
/*  78 */       RendPJDependente rend = (RendPJDependente)obj;
/*  79 */       if (rend.getCpfDependente().naoFormatado().equals(cpfDep.naoFormatado()) && rend.getNIFontePagadora().naoFormatado().equals(niFonte.naoFormatado()) && rend.todosValoresZerados()) {
/*  80 */         return rend;
/*     */       }
/*     */     } 
/*     */     
/*  84 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean existeRendimentoPreenchidoComNIFontePagadora(NI niFonte) {
/*  90 */     for (RendPJTitular rend : itens()) {
/*  91 */       if (rend.getNIFontePagadora().naoFormatado().equals(niFonte.naoFormatado()) && !rend.todosValoresZerados()) {
/*  92 */         return true;
/*     */       }
/*     */     } 
/*     */     
/*  96 */     return false;
/*     */   }
/*     */   
/*     */   public List<ObjetoNegocio> obterListaRendimentosDeDependentesQueSairamDoPais() {
/* 100 */     List<ObjetoNegocio> retorno = new ArrayList<>();
/*     */     
/* 102 */     Iterator<RendPJTitular> it = itens().iterator();
/* 103 */     while (it.hasNext()) {
/* 104 */       RendPJDependente rend = (RendPJDependente)it.next();
/* 105 */       if (rend.getDependenteSaiuPaisMesmaDataDeclarante().naoFormatado().equals(Logico.SIM)) {
/* 106 */         retorno.add(rend);
/*     */       }
/*     */     } 
/*     */     
/* 110 */     return retorno;
/*     */   }
/*     */   
/*     */   public Valor obterRendimentosRecebidosPJPorDependente(CPF cpf) {
/* 114 */     Valor retorno = new Valor();
/*     */     
/* 116 */     Iterator<RendPJTitular> it = itens().iterator();
/* 117 */     while (it.hasNext()) {
/* 118 */       RendPJDependente rend = (RendPJDependente)it.next();
/* 119 */       if (rend.getCpfDependente().naoFormatado().equals(cpf.naoFormatado())) {
/* 120 */         retorno.append('+', rend.getRendRecebidoPJ());
/*     */       }
/*     */     } 
/* 123 */     return retorno;
/*     */   }
/*     */   
/*     */   public Valor obterRendimentosRecebidosPorDependente(CPF cpf) {
/* 127 */     Valor retorno = new Valor();
/*     */     
/* 129 */     Iterator<RendPJTitular> it = itens().iterator();
/* 130 */     while (it.hasNext()) {
/* 131 */       RendPJDependente rend = (RendPJDependente)it.next();
/* 132 */       if (rend.getCpfDependente().naoFormatado().equals(cpf.naoFormatado())) {
/* 133 */         retorno.append('+', rend.getRendRecebidoPJ());
/* 134 */         retorno.append('+', rend.getDecimoTerceiro());
/*     */       } 
/*     */     } 
/* 137 */     return retorno;
/*     */   }
/*     */   
/*     */   public Boolean existeContribuicaoPrevidenciariaOficialPorDependente(CPF cpf) {
/* 141 */     Boolean retorno = Boolean.valueOf(false);
/*     */     
/* 143 */     Iterator<RendPJTitular> it = itens().iterator();
/* 144 */     while (it.hasNext()) {
/* 145 */       RendPJDependente rend = (RendPJDependente)it.next();
/* 146 */       if (rend.getCpfDependente().naoFormatado().equals(cpf.naoFormatado()) && !rend.getContribuicaoPrevOficial().isVazio()) {
/* 147 */         retorno = Boolean.valueOf(true);
/*     */         break;
/*     */       } 
/*     */     } 
/* 151 */     return retorno;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 156 */     return "Dependentes";
/*     */   }
/*     */   
/*     */   public Valor obterTotalRendPJPorDependente(CPF cpf) {
/* 160 */     Valor rendimentos = new Valor();
/*     */     
/* 162 */     for (RendPJTitular rendPJ : itens()) {
/* 163 */       RendPJDependente RendPJDep = (RendPJDependente)rendPJ;
/* 164 */       if (cpf.naoFormatado().equals(RendPJDep.getCpfDependente().naoFormatado())) {
/* 165 */         rendimentos.append('+', RendPJDep.getRendRecebidoPJ());
/*     */       }
/*     */     } 
/*     */     
/* 169 */     return rendimentos;
/*     */   }
/*     */   
/*     */   public Valor obterTotalPrevidenciaPorDependente(CPF cpf) {
/* 173 */     Valor rendimentos = new Valor();
/*     */     
/* 175 */     for (RendPJTitular rendPJ : itens()) {
/* 176 */       RendPJDependente RendPJDep = (RendPJDependente)rendPJ;
/* 177 */       if (cpf.naoFormatado().equals(RendPJDep.getCpfDependente().naoFormatado())) {
/* 178 */         rendimentos.append('+', RendPJDep.getContribuicaoPrevOficial());
/*     */       }
/*     */     } 
/*     */     
/* 182 */     return rendimentos;
/*     */   }
/*     */   
/*     */   public Valor obterTotalRendPJPorDependenteSemAuxilioEmergencial(CPF cpf) {
/* 186 */     Valor rendimentos = new Valor();
/*     */     
/* 188 */     for (RendPJTitular rendPJ : itens()) {
/* 189 */       RendPJDependente RendPJDep = (RendPJDependente)rendPJ;
/* 190 */       if (cpf.naoFormatado().equals(RendPJDep.getCpfDependente().naoFormatado()) && 
/* 191 */         !ConstantesGlobaisIRPF.CNPJ_AUXILIO_EMERGENCIAL.equals(rendPJ.getNIFontePagadora().naoFormatado())) {
/* 192 */         rendimentos.append('+', RendPJDep.getRendRecebidoPJ());
/*     */       }
/*     */     } 
/*     */     
/* 196 */     return rendimentos;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendpj\ColecaoRendPJDependente.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */