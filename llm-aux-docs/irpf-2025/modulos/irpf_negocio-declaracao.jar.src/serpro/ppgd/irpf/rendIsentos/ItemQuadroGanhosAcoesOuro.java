/*    */ package serpro.ppgd.irpf.rendIsentos;
/*    */ 
/*    */ import java.text.DecimalFormat;
/*    */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*    */ import serpro.ppgd.irpf.ValidadorNaoNuloIRPF;
/*    */ import serpro.ppgd.irpf.util.MensagemUtil;
/*    */ import serpro.ppgd.negocio.RetornoValidacao;
/*    */ import serpro.ppgd.negocio.ValidadorIf;
/*    */ import serpro.ppgd.negocio.ValidadorImpeditivoDefault;
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ 
/*    */ public class ItemQuadroGanhosAcoesOuro
/*    */   extends ItemQuadroAuxiliar
/*    */ {
/* 16 */   private static final DecimalFormat df = new DecimalFormat("0.00");
/*    */ 
/*    */   
/*    */   public ItemQuadroGanhosAcoesOuro(DeclaracaoIRPF dec, final ColecaoItemQuadroGanhosAcoesOuro colecaoItemQuadroGanhosAcoesOuro) {
/* 20 */     super(dec);
/*    */     
/* 22 */     getValor().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3));
/* 23 */     getValor().addValidador((ValidadorIf)new ValidadorImpeditivoDefault("rendisentos_quadro_auxiliar_ganhos_acoes_ouro_valor_limite")
/*    */         {
/*    */           public void acaoCancelar() {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */           
/*    */           public void acaoOk() {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */           
/*    */           public String getTituloPopup() {
/* 38 */             return "Aviso";
/*    */           }
/*    */ 
/*    */           
/*    */           public RetornoValidacao validarImplementado() {
/* 43 */             String val = (String)getProximoConteudo();
/* 44 */             Valor valAnterior = (Valor)getInformacao();
/* 45 */             if (val != null && !val.trim().isEmpty() && !val.equals("0,00")) {
/* 46 */               Double doubleVal = Double.valueOf(val.replace(".", "").replace(',', '.'));
/* 47 */               String valFormatado = ItemQuadroGanhosAcoesOuro.df.format(doubleVal);
/* 48 */               Valor atual = new Valor(valFormatado);
/* 49 */               Valor totalCpf = colecaoItemQuadroGanhosAcoesOuro.obterTotalPorCPF(ItemQuadroGanhosAcoesOuro.this.getCpfBeneficiario().naoFormatado(), valAnterior);
/*    */               
/* 51 */               Valor totalCpfMaisAtual = totalCpf.operacao('+', atual);
/* 52 */               if (totalCpfMaisAtual.comparacao(">", "240.000,00")) {
/* 53 */                 Valor diferenca = totalCpfMaisAtual.operacao('-', "240.000,00");
/* 54 */                 setMensagemValidacao(MensagemUtil.getMensagem("rendisentos_quadro_auxiliar_ganhos_acoes_ouro_valor_limite", new String[] { diferenca
/* 55 */                         .formatado() }));
/* 56 */                 setTipoExibicao(0);
/* 57 */                 setSeveridade((byte)4);
/* 58 */                 RetornoValidacao retorno = new RetornoValidacao(getMensagemValidacao(), getSeveridade());
/* 59 */                 return retorno;
/*    */               } 
/*    */             } 
/* 62 */             return null;
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getTituloFichaDashboard() {
/* 72 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendIsentos\ItemQuadroGanhosAcoesOuro.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */