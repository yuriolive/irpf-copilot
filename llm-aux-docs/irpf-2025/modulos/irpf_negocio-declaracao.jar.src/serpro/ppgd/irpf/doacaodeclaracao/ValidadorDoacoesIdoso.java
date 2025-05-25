/*    */ package serpro.ppgd.irpf.doacaodeclaracao;
/*    */ 
/*    */ import java.lang.ref.WeakReference;
/*    */ import java.text.DecimalFormat;
/*    */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*    */ import serpro.ppgd.irpf.ItemLimiteDeducaoIncentivo;
/*    */ import serpro.ppgd.irpf.calculos.CalculosDeducoesIncentivos;
/*    */ import serpro.ppgd.irpf.tabelas.TabelaAliquotasIRPF;
/*    */ import serpro.ppgd.irpf.util.MensagemUtil;
/*    */ import serpro.ppgd.negocio.RetornoValidacao;
/*    */ import serpro.ppgd.negocio.ValidadorDefault;
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ValidadorDoacoesIdoso
/*    */   extends ValidadorDefault
/*    */ {
/* 19 */   private static final DecimalFormat df = new DecimalFormat("0.00");
/*    */   
/*    */   private Valor imposto;
/* 22 */   private Valor totDoacoes = new Valor();
/* 23 */   private Valor limite = new Valor();
/*    */   
/* 25 */   private WeakReference<DeclaracaoIRPF> weakDec = null;
/*    */   
/*    */   public ValidadorDoacoesIdoso(DeclaracaoIRPF dec) {
/* 28 */     super((byte)3);
/* 29 */     this.weakDec = new WeakReference<>(dec);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public RetornoValidacao validarImplementado() {
/* 35 */     ItemLimiteDeducaoIncentivo lItemLimiteDeducaoIncentivo = null;
/*    */     
/* 37 */     String proximoConteudo = getInformacao().naoFormatado();
/* 38 */     if (proximoConteudo.indexOf(" ") != -1) {
/* 39 */       proximoConteudo = "0,00";
/*    */     }
/*    */     
/* 42 */     if (proximoConteudo != null && !proximoConteudo.trim().isEmpty() && !proximoConteudo.equals("0,00")) {
/* 43 */       String msg = null;
/*    */       
/* 45 */       this.totDoacoes.setConteudo(((DeclaracaoIRPF)this.weakDec.get()).getColecaoEstatutoIdoso().getTotalDeducaoIncentivoBruto());
/*    */       
/* 47 */       this.imposto = ((DeclaracaoIRPF)this.weakDec.get()).getModeloCompleta().getImposto();
/*    */       
/* 49 */       if (((DeclaracaoIRPF)this.weakDec.get()).getColecaoEstatutoIdoso().getValorDisponivelDoacao().comparacao("<", "0,00")) {
/*    */         
/* 51 */         lItemLimiteDeducaoIncentivo = CalculosDeducoesIncentivos.calculaDeducaoIncentivo(this.weakDec.get(), this.imposto);
/* 52 */         this.limite.setConteudo(this.totDoacoes);
/* 53 */         this.limite.append('+', ((DeclaracaoIRPF)this.weakDec.get()).getColecaoEstatutoIdoso().getValorDisponivelDoacao());
/* 54 */         if (this.limite.comparacao("<", "0,00")) {
/* 55 */           this.limite.clear();
/*    */         }
/* 57 */         Valor valorIncentivoMaisDesporto = new Valor();
/* 58 */         valorIncentivoMaisDesporto.setConteudo(TabelaAliquotasIRPF.ConstantesAliquotas.deducaoIncentivo.getValor());
/* 59 */         valorIncentivoMaisDesporto.append('+', TabelaAliquotasIRPF.ConstantesAliquotas.deducoesIncDesporto.getValor());
/* 60 */         String mensagem = MensagemUtil.getMensagem("doacoes_idoso_pendencia_excede_limite_doacao", new String[] { TabelaAliquotasIRPF.ConstantesAliquotas.deducoesIncECA
/* 61 */               .getValor().formatado(), TabelaAliquotasIRPF.ConstantesAliquotas.deducaoIncentivo
/* 62 */               .getValor().formatado(), valorIncentivoMaisDesporto
/* 63 */               .formatado() });
/* 64 */         msg = "<html>" + mensagem + "</html>";
/*    */       } 
/*    */       
/* 67 */       if (msg != null && !"".equals(msg)) {
/* 68 */         RetornoValidacao retorno = new RetornoValidacao(msg);
/* 69 */         return retorno;
/*    */       } 
/*    */     } 
/*    */     
/* 73 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\doacaodeclaracao\ValidadorDoacoesIdoso.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */