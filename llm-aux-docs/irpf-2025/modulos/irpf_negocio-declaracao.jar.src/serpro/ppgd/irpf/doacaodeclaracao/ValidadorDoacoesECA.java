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
/*    */ 
/*    */ public class ValidadorDoacoesECA
/*    */   extends ValidadorDefault
/*    */ {
/* 20 */   private static final DecimalFormat df = new DecimalFormat("0.00");
/*    */   
/*    */   private Valor imposto;
/* 23 */   private Valor totDoacoes = new Valor();
/* 24 */   private Valor limite = new Valor();
/*    */   
/* 26 */   private WeakReference<DeclaracaoIRPF> weakDec = null;
/*    */   
/*    */   public ValidadorDoacoesECA(DeclaracaoIRPF dec) {
/* 29 */     super((byte)3);
/* 30 */     this.weakDec = new WeakReference<>(dec);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public RetornoValidacao validarImplementado() {
/* 36 */     ItemLimiteDeducaoIncentivo lItemLimiteDeducaoIncentivo = null;
/*    */     
/* 38 */     String proximoConteudo = getInformacao().naoFormatado();
/* 39 */     if (proximoConteudo.indexOf(" ") != -1) {
/* 40 */       proximoConteudo = "0,00";
/*    */     }
/*    */     
/* 43 */     if (proximoConteudo != null && !proximoConteudo.trim().isEmpty() && !proximoConteudo.equals("0,00")) {
/* 44 */       String msg = null;
/*    */       
/* 46 */       this.totDoacoes.setConteudo(((DeclaracaoIRPF)this.weakDec.get()).getColecaoEstatutoCriancaAdolescente().getTotalDeducaoIncentivoBruto());
/*    */       
/* 48 */       this.imposto = ((DeclaracaoIRPF)this.weakDec.get()).getModeloCompleta().getImposto();
/*    */       
/* 50 */       if (((DeclaracaoIRPF)this.weakDec.get()).getColecaoEstatutoCriancaAdolescente().getValorDisponivelDoacao().comparacao("<", "0,00")) {
/*    */         
/* 52 */         lItemLimiteDeducaoIncentivo = CalculosDeducoesIncentivos.calculaDeducaoIncentivo(this.weakDec.get(), this.imposto);
/* 53 */         this.limite.setConteudo(this.totDoacoes);
/* 54 */         this.limite.append('+', ((DeclaracaoIRPF)this.weakDec.get()).getColecaoEstatutoCriancaAdolescente().getValorDisponivelDoacao());
/* 55 */         if (this.limite.comparacao("<", "0,00")) {
/* 56 */           this.limite.clear();
/*    */         }
/* 58 */         Valor valorIncentivoMaisDesporto = new Valor();
/* 59 */         valorIncentivoMaisDesporto.setConteudo(TabelaAliquotasIRPF.ConstantesAliquotas.deducaoIncentivo.getValor());
/* 60 */         valorIncentivoMaisDesporto.append('+', TabelaAliquotasIRPF.ConstantesAliquotas.deducoesIncDesporto.getValor());
/* 61 */         String mensagem = MensagemUtil.getMensagem("doacoes_eca_pendencia_excede_limite_doacao", new String[] { TabelaAliquotasIRPF.ConstantesAliquotas.deducoesIncECA
/* 62 */               .getValor().formatado(), TabelaAliquotasIRPF.ConstantesAliquotas.deducaoIncentivo
/* 63 */               .getValor().formatado(), valorIncentivoMaisDesporto
/* 64 */               .formatado() });
/* 65 */         msg = "<html>" + mensagem + "</html>";
/*    */       } 
/*    */       
/* 68 */       if (msg != null && !"".equals(msg)) {
/* 69 */         RetornoValidacao retorno = new RetornoValidacao(msg);
/* 70 */         return retorno;
/*    */       } 
/*    */     } 
/*    */     
/* 74 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\doacaodeclaracao\ValidadorDoacoesECA.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */