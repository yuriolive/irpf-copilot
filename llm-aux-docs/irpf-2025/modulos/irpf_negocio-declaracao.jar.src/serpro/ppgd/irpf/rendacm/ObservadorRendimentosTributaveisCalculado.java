/*     */ package serpro.ppgd.irpf.rendacm;
/*     */ 
/*     */ import java.lang.ref.WeakReference;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroTransporteDetalhado;
/*     */ import serpro.ppgd.negocio.Colecao;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ 
/*     */ public class ObservadorRendimentosTributaveisCalculado
/*     */   extends Observador
/*     */ {
/*     */   private RendAcmTitular rendAcm;
/*     */   private WeakReference<DeclaracaoIRPF> weakDec;
/*  18 */   private WeakReference<? extends Colecao<RendAcmTitular>> weakColecao = null;
/*     */   
/*     */   public ObservadorRendimentosTributaveisCalculado(RendAcmTitular rendAcm, DeclaracaoIRPF dec, Colecao<RendAcmTitular> colecao) {
/*  21 */     this.rendAcm = rendAcm;
/*  22 */     this.weakDec = new WeakReference<>(dec);
/*  23 */     this.weakColecao = new WeakReference<>(colecao);
/*     */   }
/*     */ 
/*     */   
/*     */   public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/*  28 */     if (this.rendAcm.getOpcaoTributacao().naoFormatado().equals("E")) {
/*  29 */       ValorPositivo rendTributCalculado = new ValorPositivo();
/*  30 */       rendTributCalculado.append('+', (Valor)this.rendAcm.getRendRecebidosInformado());
/*  31 */       rendTributCalculado.append('+', (Valor)this.rendAcm.getParcIsenta65Anos());
/*  32 */       this.rendAcm.getRendRecebidos().setConteudo((Valor)rendTributCalculado);
/*  33 */     } else if (this.rendAcm.getOpcaoTributacao().naoFormatado().equals("A")) {
/*  34 */       if (nomePropriedade.equals("Parcela isenta 65 anos") || nomePropriedade.equals("Dependente")) {
/*  35 */         Valor disponivel65 = new Valor();
/*  36 */         Valor totalDeclaradoIsento65SemAtual = new Valor();
/*  37 */         Valor rendimento = new Valor();
/*  38 */         Valor isento65 = new Valor();
/*  39 */         Valor sobra = new Valor();
/*  40 */         boolean alterouIsento = false;
/*  41 */         boolean registroAtualContabilizado = false;
/*  42 */         String cpfContribuinte = ((DeclaracaoIRPF)this.weakDec.get()).getIdentificadorDeclaracao().getCpf().formatado();
/*  43 */         if (this.rendAcm instanceof RendAcmDependente) {
/*  44 */           cpfContribuinte = ((RendAcmDependente)this.rendAcm).getCpfDependente().formatado();
/*     */         }
/*  46 */         for (RendAcmTitular lRendAcm : ((Colecao)this.weakColecao.get()).itens()) {
/*     */           String cpfAtual;
/*  48 */           if (this.rendAcm instanceof RendAcmDependente) {
/*  49 */             cpfAtual = ((RendAcmDependente)lRendAcm).getCpfDependente().formatado();
/*     */           } else {
/*  51 */             cpfAtual = ((DeclaracaoIRPF)this.weakDec.get()).getIdentificadorDeclaracao().getCpf().formatado();
/*     */           } 
/*  53 */           if (cpfContribuinte.equals(cpfAtual)) {
/*  54 */             if (!registroAtualContabilizado && lRendAcm
/*  55 */               .getParcIsenta65Anos().formatado().equals(this.rendAcm.getParcIsenta65Anos().formatado())) {
/*  56 */               registroAtualContabilizado = true; continue;
/*     */             } 
/*  58 */             totalDeclaradoIsento65SemAtual.append('+', (Valor)lRendAcm.getParcIsenta65Anos());
/*     */           } 
/*     */         } 
/*     */         
/*  62 */         for (ItemQuadroTransporteDetalhado item : ((DeclaracaoIRPF)this.weakDec.get()).getRendIsentos().getParcIsentaAposentadoriaQuadroAuxiliar().itens()) {
/*  63 */           if (item.getCpfBeneficiario().formatado().equals(cpfContribuinte)) {
/*  64 */             totalDeclaradoIsento65SemAtual.append('+', item.getValor());
/*     */           }
/*     */         } 
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
/*  86 */         disponivel65.setConteudo("22.847,76");
/*  87 */         disponivel65.append('-', totalDeclaradoIsento65SemAtual);
/*     */         
/*  89 */         if (disponivel65.comparacao(">", "0,00")) {
/*  90 */           if (this.rendAcm.getParcIsenta65Anos().comparacao(">", "1.903,98")) {
/*  91 */             if (disponivel65.comparacao(">", "1.903,98")) {
/*  92 */               sobra.setConteudo((Valor)this.rendAcm.getParcIsenta65Anos());
/*  93 */               sobra.append('-', "1.903,98");
/*  94 */               rendimento.setConteudo((Valor)this.rendAcm.getRendRecebidosInformado());
/*  95 */               rendimento.append('+', sobra);
/*  96 */               isento65.setConteudo("1.903,98");
/*  97 */               alterouIsento = true;
/*     */             } else {
/*  99 */               sobra.setConteudo((Valor)this.rendAcm.getParcIsenta65Anos());
/* 100 */               sobra.append('-', disponivel65);
/* 101 */               rendimento.setConteudo((Valor)this.rendAcm.getRendRecebidosInformado());
/* 102 */               rendimento.append('+', sobra);
/* 103 */               isento65.setConteudo(disponivel65);
/* 104 */               alterouIsento = true;
/*     */             }
/*     */           
/* 107 */           } else if (disponivel65.comparacao("<", (Valor)this.rendAcm.getParcIsenta65Anos())) {
/* 108 */             sobra.setConteudo((Valor)this.rendAcm.getParcIsenta65Anos());
/* 109 */             sobra.append('-', disponivel65);
/* 110 */             rendimento.setConteudo((Valor)this.rendAcm.getRendRecebidosInformado());
/* 111 */             rendimento.append('+', sobra);
/* 112 */             isento65.setConteudo(disponivel65);
/* 113 */             alterouIsento = true;
/*     */           } else {
/* 115 */             rendimento.setConteudo((Valor)this.rendAcm.getRendRecebidosInformado());
/* 116 */             isento65.setConteudo((Valor)this.rendAcm.getParcIsenta65Anos());
/*     */           } 
/*     */         } else {
/*     */           
/* 120 */           rendimento.setConteudo((Valor)this.rendAcm.getRendRecebidosInformado());
/* 121 */           rendimento.append('+', (Valor)this.rendAcm.getParcIsenta65Anos());
/* 122 */           isento65.clear();
/* 123 */           if (!this.rendAcm.getParcIsenta65Anos().isVazio()) {
/* 124 */             alterouIsento = true;
/*     */           }
/*     */         } 
/* 127 */         if (alterouIsento) {
/* 128 */           GuiUtil.mostrarAviso("rendisentos_limite_aposentadoria_rra_excedido", new String[] { cpfContribuinte });
/*     */         }
/* 130 */         this.rendAcm.getRendRecebidosInformado().setConteudo(rendimento);
/*     */         
/* 132 */         this.rendAcm.getParcIsenta65Anos().setConteudo(isento65);
/*     */       } 
/*     */       
/* 135 */       this.rendAcm.getRendRecebidos().setConteudo((Valor)this.rendAcm.getRendRecebidosInformado());
/*     */     } 
/*     */     
/* 138 */     if (this.rendAcm.getOpcaoTributacao().getNomeCampo().equals(nomePropriedade) && "V"
/* 139 */       .equals(valorAntigo) && "A"
/* 140 */       .equals(valorNovo))
/* 141 */       this.rendAcm.getParcIsenta65Anos().forcaDisparoObservadores(); 
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendacm\ObservadorRendimentosTributaveisCalculado.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */