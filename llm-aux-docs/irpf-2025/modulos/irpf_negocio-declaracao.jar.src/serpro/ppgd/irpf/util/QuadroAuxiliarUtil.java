/*     */ package serpro.ppgd.irpf.util;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroAuxiliar;
/*     */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroAuxiliarAb;
/*     */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroPensaoMolestiaGrave;
/*     */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroTransporteDetalhado;
/*     */ import serpro.ppgd.negocio.CPF;
/*     */ import serpro.ppgd.negocio.Colecao;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.Pendencia;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ import serpro.ppgd.negocio.util.FabricaUtilitarios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class QuadroAuxiliarUtil
/*     */ {
/*     */   public static Valor recuperarTotalQuadroAuxiliarTitular(Colecao<? extends ItemQuadroAuxiliar> colecaoQuadroAuxiliar) {
/*  27 */     return recuperarTotalQuadroAuxiliarPorTipo(colecaoQuadroAuxiliar, "Titular");
/*     */   }
/*     */   
/*     */   public static Valor recuperarTotalQuadroAuxiliarDependente(Colecao<? extends ItemQuadroAuxiliar> colecaoQuadroAuxiliar) {
/*  31 */     return recuperarTotalQuadroAuxiliarPorTipo(colecaoQuadroAuxiliar, "Dependente");
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<Pendencia> validarQuadroAuxiliar(Colecao<? extends ItemQuadroAuxiliar> colecao, Informacao informacao, String complementoMensagem) {
/*  36 */     List<Pendencia> pendencias = new ArrayList<>();
/*     */     
/*  38 */     List<? extends ItemQuadroAuxiliar> itens = colecao.itens();
/*     */     
/*  40 */     for (ItemQuadroAuxiliar item : itens) {
/*  41 */       item.adicionaCamposParaPendencia();
/*  42 */       pendencias.addAll(FabricaUtilitarios.verificarPendencias(colecao));
/*  43 */       item.removeCamposParaPendencia();
/*     */     } 
/*     */     
/*  46 */     for (Pendencia p : pendencias) {
/*  47 */       p.setCampoInformacao(informacao);
/*  48 */       p.setCampo(informacao.getNomeCampo());
/*  49 */       p.setMsg(complementoMensagem + " - " + complementoMensagem);
/*     */     } 
/*     */     
/*  52 */     return pendencias;
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
/*     */   public static List<Pendencia> validarQuadroAuxiliarAb(Colecao<? extends ItemQuadroAuxiliarAb> colecao, int posicaoInicial, String complementoMensagem, boolean isIsento) {
/*  79 */     List<Pendencia> pendencias = new ArrayList<>();
/*     */     
/*  81 */     List<? extends ItemQuadroAuxiliarAb> itens = colecao.itens();
/*     */     
/*  83 */     for (ItemQuadroAuxiliarAb item : itens) {
/*  84 */       item.adicionaCamposParaPendencia();
/*  85 */       List<Pendencia> novasPendencias = FabricaUtilitarios.verificarPendencias(item);
/*  86 */       for (Pendencia p : novasPendencias) {
/*  87 */         p.setMsg(p.getMsg() + " (" + p.getMsg() + ")");
/*  88 */         p.setNumItem(posicaoInicial);
/*  89 */         if (isIsento) {
/*  90 */           p.getCampoInformacao().setFicha("Rendimentos Isentos e Não Tributáveis"); continue;
/*     */         } 
/*  92 */         p.getCampoInformacao().setFicha("Rendimentos Sujeitos à Tributação Exclusiva/Definitiva");
/*     */       } 
/*     */       
/*  95 */       posicaoInicial++;
/*  96 */       pendencias.addAll(novasPendencias);
/*  97 */       item.removeCamposParaPendencia();
/*     */     } 
/*     */     
/* 100 */     return pendencias;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Valor recuperarTotalQuadroAuxiliarPorBeneficiario(Colecao<? extends ItemQuadroAuxiliar> colecaoQuadroAuxiliar, CPF cpfBeneficiario, boolean somar13Salario) {
/* 105 */     Valor total = new Valor();
/*     */     
/* 107 */     Iterator<? extends ItemQuadroAuxiliar> it = colecaoQuadroAuxiliar.itens().iterator();
/* 108 */     while (it.hasNext()) {
/* 109 */       ItemQuadroAuxiliar item = it.next();
/* 110 */       if (item.getCpfBeneficiario().naoFormatado().equals(cpfBeneficiario.naoFormatado())) {
/* 111 */         total.append('+', item.getValor());
/*     */         
/* 113 */         if (somar13Salario) {
/* 114 */           total.append('+', (Valor)item.getValor13Salario());
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 119 */     return total;
/*     */   }
/*     */ 
/*     */   
/*     */   private static Valor recuperarTotalQuadroAuxiliarPorTipo(Colecao<? extends ItemQuadroAuxiliar> colecaoQuadroAuxiliar, String tipo) {
/* 124 */     Valor total = new Valor();
/*     */     
/* 126 */     Iterator<? extends ItemQuadroAuxiliar> it = colecaoQuadroAuxiliar.itens().iterator();
/* 127 */     while (it.hasNext()) {
/* 128 */       ItemQuadroAuxiliar item = it.next();
/* 129 */       if (item.getTipoBeneficiario().naoFormatado().equals(tipo)) {
/* 130 */         total.append('+', item.getValor());
/*     */         
/* 132 */         if (item instanceof ItemQuadroPensaoMolestiaGrave) {
/* 133 */           total.append('+', (Valor)((ItemQuadroPensaoMolestiaGrave)item).getValor13Salario()); continue;
/*     */         } 
/* 135 */         if (item instanceof ItemQuadroTransporteDetalhado) {
/*     */           
/* 137 */           ItemQuadroTransporteDetalhado itemDetalhado = (ItemQuadroTransporteDetalhado)item;
/* 138 */           if (itemDetalhado.isParcIsentaAposentadoria()) {
/* 139 */             total.append('+', (Valor)((ItemQuadroTransporteDetalhado)item).getValor13Salario());
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 145 */     return total;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irp\\util\QuadroAuxiliarUtil.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */