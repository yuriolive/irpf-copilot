/*     */ package serpro.ppgd.irpf.rendacm;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.negocio.CPF;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ public class ColecaoRendAcmDependente
/*     */   extends ColecaoRendAcmTitular
/*     */ {
/*     */   public ColecaoRendAcmDependente(DeclaracaoIRPF dec) {
/*  15 */     super(dec);
/*  16 */     setFicha("Rendimentos Recebidos Acumuladamente pelos Dependentes");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RendAcmDependente instanciaNovoObjeto() {
/*  24 */     DeclaracaoIRPF declaracaoIRPF = this.weakDec.get();
/*  25 */     return new RendAcmDependente(declaracaoIRPF, this);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean possuiDependenteComCPF(String cpf) {
/*  30 */     if (cpf.trim().isEmpty()) {
/*  31 */       return false;
/*     */     }
/*     */     
/*  34 */     Iterator<RendAcmTitular> it = itens().iterator();
/*  35 */     while (it.hasNext()) {
/*  36 */       RendAcmDependente rend = (RendAcmDependente)it.next();
/*  37 */       if (rend.getCpfDependente().naoFormatado().equals(cpf)) {
/*  38 */         return true;
/*     */       }
/*     */     } 
/*     */     
/*  42 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void excluirDependentesComCPF(String cpf) {
/*  47 */     Iterator<RendAcmTitular> it = itens().iterator();
/*  48 */     while (it.hasNext()) {
/*  49 */       RendAcmDependente rend = (RendAcmDependente)it.next();
/*  50 */       if (rend.getCpfDependente().naoFormatado().equals(cpf)) {
/*  51 */         it.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public Valor obterRendimentosRecebidosPorDependente(CPF cpf) {
/*  57 */     Valor retorno = new Valor();
/*     */     
/*  59 */     Iterator<RendAcmTitular> it = itens().iterator();
/*  60 */     while (it.hasNext()) {
/*  61 */       RendAcmDependente rend = (RendAcmDependente)it.next();
/*  62 */       if (rend.getCpfDependente().naoFormatado().equals(cpf.naoFormatado())) {
/*  63 */         retorno.append('+', (Valor)rend.getRendRecebidos());
/*     */       }
/*     */     } 
/*  66 */     return retorno;
/*     */   }
/*     */   
/*     */   public Valor obterPrevidenciaAjustePorDependente(CPF cpf) {
/*  70 */     Valor retorno = new Valor();
/*     */     
/*  72 */     Iterator<RendAcmTitular> it = itens().iterator();
/*  73 */     while (it.hasNext()) {
/*  74 */       RendAcmDependente rend = (RendAcmDependente)it.next();
/*  75 */       if (rend.getCpfDependente().naoFormatado().equals(cpf.naoFormatado()) && "A"
/*  76 */         .equals(rend.getOpcaoTributacao().naoFormatado())) {
/*  77 */         retorno.append('+', (Valor)rend.getContribuicaoPrevOficial());
/*     */       }
/*     */     } 
/*  80 */     return retorno;
/*     */   }
/*     */   
/*     */   public Valor obterPensaoAlimenticiaPorDependente(CPF cpf) {
/*  84 */     Valor retorno = new Valor();
/*     */     
/*  86 */     Iterator<RendAcmTitular> it = itens().iterator();
/*  87 */     while (it.hasNext()) {
/*  88 */       RendAcmDependente rend = (RendAcmDependente)it.next();
/*  89 */       if (rend.getCpfDependente().naoFormatado().equals(cpf.naoFormatado()) && "A"
/*  90 */         .equals(rend.getOpcaoTributacao().naoFormatado())) {
/*  91 */         retorno.append('+', (Valor)rend.getPensaoAlimenticia());
/*     */       }
/*     */     } 
/*  94 */     return retorno;
/*     */   }
/*     */   
/*     */   public Valor obterRendimentosRecebidosAjustePorDependente(CPF cpf) {
/*  98 */     Valor retorno = new Valor();
/*     */     
/* 100 */     Iterator<RendAcmTitular> it = itens().iterator();
/* 101 */     while (it.hasNext()) {
/* 102 */       RendAcmDependente rend = (RendAcmDependente)it.next();
/* 103 */       if (rend.getCpfDependente().naoFormatado().equals(cpf.naoFormatado()) && "A"
/* 104 */         .equals(rend.getOpcaoTributacao().naoFormatado())) {
/* 105 */         retorno.append('+', (Valor)rend.getRendRecebidos());
/*     */       }
/*     */     } 
/* 108 */     return retorno;
/*     */   }
/*     */   
/*     */   public List<RendAcmDependente> obterRRAsPorCPF(String cpfDependente) {
/* 112 */     ArrayList<RendAcmDependente> rendimentosRRA = new ArrayList<>();
/* 113 */     for (RendAcmTitular rendacm : itens()) {
/* 114 */       RendAcmDependente rendRRADep = (RendAcmDependente)rendacm;
/* 115 */       if (rendRRADep.getCpfDependente().naoFormatado().equals(cpfDependente)) {
/* 116 */         rendimentosRRA.add(rendRRADep);
/*     */       }
/*     */     } 
/* 119 */     return rendimentosRRA;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendacm\ColecaoRendAcmDependente.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */