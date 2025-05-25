/*     */ package serpro.ppgd.irpf.rendacm;
/*     */ 
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.CPF;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ 
/*     */ public class RendAcm
/*     */   extends ObjetoNegocio
/*     */ {
/*     */   public static final String OPCAO_TRIBUTACAO_VAZIA = "V";
/*     */   public static final String OPCAO_TRIBUTACAO_AJUSTE = "A";
/*     */   public static final String OPCAO_TRIBUTACAO_EXCLUSIVA = "E";
/*  25 */   private ColecaoRendAcmTitular colecaoRendAcmTitular = null;
/*  26 */   private ColecaoRendAcmDependente colecaoRendAcmDependente = null;
/*  27 */   private Valor totalRendRecebAcumuladamente = new Valor(this, "Total Rend. Receb. Acumuladamente Tit + Dep");
/*     */   
/*  29 */   private WeakReference<DeclaracaoIRPF> weakDec = null;
/*     */   
/*     */   public RendAcm(DeclaracaoIRPF dec) {
/*  32 */     this.colecaoRendAcmTitular = new ColecaoRendAcmTitular(dec);
/*  33 */     this.colecaoRendAcmDependente = new ColecaoRendAcmDependente(dec);
/*  34 */     this.weakDec = new WeakReference<>(dec);
/*     */   }
/*     */   
/*     */   public ColecaoRendAcmDependente getColecaoRendAcmDependente() {
/*  38 */     return this.colecaoRendAcmDependente;
/*     */   }
/*     */   
/*     */   public ColecaoRendAcmTitular getColecaoRendAcmTitular() {
/*  42 */     return this.colecaoRendAcmTitular;
/*     */   }
/*     */   
/*     */   public Valor getTotalRendRecebAcumuladamente() {
/*  46 */     return this.totalRendRecebAcumuladamente;
/*     */   }
/*     */   
/*     */   public List<String> obterBeneficiariosEmOrdemDecrescente() {
/*  50 */     List<String> retorno = new ArrayList<>();
/*     */     
/*  52 */     Map<String, BeneficiarioRRA> fontesPagadoras = new HashMap<>();
/*     */ 
/*     */     
/*  55 */     this.colecaoRendAcmTitular.excluirRegistrosEmBranco();
/*  56 */     Iterator<? extends ObjetoNegocio> itCol = this.colecaoRendAcmTitular.itens().iterator();
/*  57 */     while (itCol.hasNext()) {
/*  58 */       RendAcmTitular rendAcmTitularAtual = (RendAcmTitular)itCol.next();
/*     */       
/*  60 */       String chave = ((DeclaracaoIRPF)this.weakDec.get()).getIdentificadorDeclaracao().getCpf().naoFormatado();
/*  61 */       if (!fontesPagadoras.containsKey(chave)) {
/*  62 */         BeneficiarioRRA beneficiarioRRA = new BeneficiarioRRA();
/*  63 */         beneficiarioRRA.getCpf().setConteudo(((DeclaracaoIRPF)this.weakDec.get()).getIdentificadorDeclaracao().getCpf());
/*  64 */         beneficiarioRRA.getOpcaoTributacao().setConteudo(rendAcmTitularAtual.getOpcaoTributacao());
/*  65 */         fontesPagadoras.put(chave, beneficiarioRRA);
/*     */       } 
/*  67 */       BeneficiarioRRA beneficiario = fontesPagadoras.get(chave);
/*  68 */       beneficiario.getValorTotal().append('+', (Valor)rendAcmTitularAtual.getRendRecebidos());
/*     */     } 
/*     */ 
/*     */     
/*  72 */     this.colecaoRendAcmDependente.excluirRegistrosEmBranco();
/*  73 */     itCol = this.colecaoRendAcmDependente.itens().iterator();
/*  74 */     while (itCol.hasNext()) {
/*  75 */       RendAcmDependente rendAcmDependenteAtual = (RendAcmDependente)itCol.next();
/*     */       
/*  77 */       String chave = rendAcmDependenteAtual.getCpfDependente().naoFormatado();
/*  78 */       if (!fontesPagadoras.containsKey(chave)) {
/*  79 */         BeneficiarioRRA beneficiarioRRA = new BeneficiarioRRA();
/*  80 */         beneficiarioRRA.getCpf().setConteudo(rendAcmDependenteAtual.getCpfDependente());
/*  81 */         beneficiarioRRA.getOpcaoTributacao().setConteudo(rendAcmDependenteAtual.getOpcaoTributacao());
/*  82 */         fontesPagadoras.put(chave, beneficiarioRRA);
/*     */       } 
/*  84 */       BeneficiarioRRA beneficiario = fontesPagadoras.get(chave);
/*  85 */       beneficiario.getValorTotal().append('+', (Valor)rendAcmDependenteAtual.getRendRecebidos());
/*     */     } 
/*     */     
/*  88 */     List<BeneficiarioRRA> listaOrdenadaFontesPagadoras = new ArrayList<>(fontesPagadoras.values());
/*     */     
/*  90 */     Collections.sort(listaOrdenadaFontesPagadoras, new Comparator<BeneficiarioRRA>()
/*     */         {
/*     */           public int compare(RendAcm.BeneficiarioRRA o1, RendAcm.BeneficiarioRRA o2)
/*     */           {
/*  94 */             return o2.getValorTotal().comparacao(">", o1.getValorTotal()) ? 1 : (o2.getValorTotal().comparacao("=", o1.getValorTotal()) ? 0 : -1);
/*     */           }
/*     */         });
/*     */ 
/*     */     
/*  99 */     for (BeneficiarioRRA f : listaOrdenadaFontesPagadoras) {
/* 100 */       if (!f.getValorTotal().isVazio()) {
/* 101 */         retorno.add(f.getCpf().naoFormatado() + "#" + f.getCpf().naoFormatado());
/*     */       }
/*     */     } 
/*     */     
/* 105 */     return retorno;
/*     */   }
/*     */   
/*     */   private static class BeneficiarioRRA
/*     */     extends ObjetoNegocio {
/* 110 */     private CPF cpf = new CPF();
/* 111 */     private Alfa opcaoTributacao = new Alfa();
/* 112 */     private Valor valorTotal = new Valor(this, "");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public CPF getCpf() {
/* 118 */       return this.cpf;
/*     */     }
/*     */     
/*     */     public Alfa getOpcaoTributacao() {
/* 122 */       return this.opcaoTributacao;
/*     */     }
/*     */     
/*     */     public Valor getValorTotal() {
/* 126 */       return this.valorTotal;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public ValorPositivo obterTotalParcela65AnosAjuste() {
/* 132 */     ValorPositivo total = new ValorPositivo();
/* 133 */     for (RendAcmTitular rendTitular : getColecaoRendAcmTitular().itens()) {
/* 134 */       if (rendTitular.getOpcaoTributacao().naoFormatado().equals("A")) {
/* 135 */         total.append('+', (Valor)rendTitular.getParcIsenta65Anos());
/*     */       }
/*     */     } 
/* 138 */     for (RendAcmTitular rendTitular : getColecaoRendAcmDependente().itens()) {
/* 139 */       if (rendTitular.getOpcaoTributacao().naoFormatado().equals("A")) {
/* 140 */         total.append('+', (Valor)rendTitular.getParcIsenta65Anos());
/*     */       }
/*     */     } 
/* 143 */     return total;
/*     */   }
/*     */   
/*     */   public Map<String, Valor> obterTotalParcelaIsenta65AnosPorCPF() {
/* 147 */     Map<String, Valor> valoresPorCPF = new HashMap<>();
/* 148 */     String cpf = ((DeclaracaoIRPF)this.weakDec.get()).getIdentificadorDeclaracao().getCpf().formatado();
/* 149 */     for (RendAcmTitular rendTitular : getColecaoRendAcmTitular().itens()) {
/* 150 */       if (rendTitular.getOpcaoTributacao().naoFormatado().equals("A")) {
/* 151 */         if (!valoresPorCPF.containsKey(cpf)) {
/* 152 */           valoresPorCPF.put(cpf, new Valor());
/*     */         }
/* 154 */         Valor total = valoresPorCPF.get(cpf);
/* 155 */         total.append('+', (Valor)rendTitular.getParcIsenta65Anos());
/*     */       } 
/*     */     } 
/* 158 */     for (RendAcmTitular rend : getColecaoRendAcmDependente().itens()) {
/* 159 */       RendAcmDependente rendDependente = (RendAcmDependente)rend;
/* 160 */       cpf = rendDependente.getCpfDependente().formatado();
/* 161 */       if (rendDependente.getOpcaoTributacao().naoFormatado().equals("A")) {
/* 162 */         if (!valoresPorCPF.containsKey(cpf)) {
/* 163 */           valoresPorCPF.put(cpf, new Valor());
/*     */         }
/* 165 */         Valor total = valoresPorCPF.get(cpf);
/* 166 */         total.append('+', (Valor)rendDependente.getParcIsenta65Anos());
/*     */       } 
/*     */     } 
/* 169 */     return valoresPorCPF;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendacm\RendAcm.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */