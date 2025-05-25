/*    */ package serpro.ppgd.irpf.atividaderural.exterior;
/*    */ 
/*    */ import java.util.List;
/*    */ import serpro.ppgd.irpf.IdentificadorDeclaracao;
/*    */ import serpro.ppgd.irpf.atividaderural.ImovelAR;
/*    */ import serpro.ppgd.irpf.atividaderural.MovimentacaoRebanho;
/*    */ import serpro.ppgd.irpf.gui.atividaderural.PainelDadosImovel;
/*    */ import serpro.ppgd.irpf.util.MensagemUtil;
/*    */ import serpro.ppgd.negocio.Informacao;
/*    */ import serpro.ppgd.negocio.ObjetoFicha;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ import serpro.ppgd.negocio.Pendencia;
/*    */ 
/*    */ 
/*    */ public class ARExterior
/*    */   extends ObjetoNegocio
/*    */   implements ObjetoFicha
/*    */ {
/* 19 */   private IdentificacaoImovelARExterior identificacaoImovel = new IdentificacaoImovelARExterior();
/* 20 */   private ColecaoReceitasDespesas receitasDespesas = new ColecaoReceitasDespesas();
/*    */   private ApuracaoResultadoExterior apuracaoResultado;
/* 22 */   private MovimentacaoRebanho movimentacaoRebanho = new MovimentacaoRebanho();
/*    */   
/* 24 */   private ColecaoDividasARExterior dividas = new ColecaoDividasARExterior();
/*    */   
/*    */   public ARExterior(IdentificadorDeclaracao identificadorDeclaracao) {
/* 27 */     getMovimentacaoRebanho().setFicha("Movimentação do Rebanho - EXTERIOR");
/*    */     
/* 29 */     this.apuracaoResultado = new ApuracaoResultadoExterior(identificadorDeclaracao);
/*    */   }
/*    */   
/*    */   public ApuracaoResultadoExterior getApuracaoResultado() {
/* 33 */     return this.apuracaoResultado;
/*    */   }
/*    */   public ColecaoDividasARExterior getDividas() {
/* 36 */     return this.dividas;
/*    */   }
/*    */   public IdentificacaoImovelARExterior getIdentificacaoImovel() {
/* 39 */     return this.identificacaoImovel;
/*    */   }
/*    */   public MovimentacaoRebanho getMovimentacaoRebanho() {
/* 42 */     return this.movimentacaoRebanho;
/*    */   }
/*    */   public ColecaoReceitasDespesas getReceitasDespesas() {
/* 45 */     return this.receitasDespesas;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<Pendencia> verificarPendencias(int numeroItem) {
/* 51 */     List<Pendencia> retorno = super.verificarPendencias(numeroItem);
/*    */ 
/*    */ 
/*    */     
/* 55 */     boolean temAlgumaOutraFichaPreenchida = (!getReceitasDespesas().isVazio() || !getApuracaoResultado().isVazio() || !getMovimentacaoRebanho().isVazio() || !getDividas().isVazio());
/*    */     
/* 57 */     if (getIdentificacaoImovel().isVazio() && temAlgumaOutraFichaPreenchida) {
/*    */       
/* 59 */       getIdentificacaoImovel().novoObjeto();
/* 60 */       ImovelAR imovel = getIdentificacaoImovel().itens().get(0);
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 65 */       Pendencia p = new Pendencia((byte)3, (Informacao)imovel.getCodigo(), imovel.getCodigo().getNomeCampo(), MensagemUtil.getMensagem("ar_imovel_nao_preenchido"), numeroItem);
/*    */       
/* 67 */       p.setClassePainel(super.getClasseFicha());
/* 68 */       p.setNomeAba(getNomeAba());
/* 69 */       retorno.add(p);
/*    */     } 
/* 71 */     return retorno;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getClasseFicha() {
/* 76 */     return PainelDadosImovel.class.getName();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getNomeAba() {
/* 81 */     return "Exterior";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getTituloFichaDashboard() {
/* 87 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\atividaderural\exterior\ARExterior.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */