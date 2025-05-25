/*    */ package serpro.ppgd.irpf.atividaderural.brasil;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import serpro.ppgd.irpf.atividaderural.MovimentacaoRebanho;
/*    */ import serpro.ppgd.irpf.gui.atividaderural.PainelDadosImovel;
/*    */ import serpro.ppgd.irpf.util.MensagemUtil;
/*    */ import serpro.ppgd.negocio.Informacao;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ import serpro.ppgd.negocio.Pendencia;
/*    */ 
/*    */ 
/*    */ public class ARBrasil
/*    */   extends ObjetoNegocio
/*    */ {
/* 16 */   private IdentificacaoImovelARBrasil identificacaoImovel = new IdentificacaoImovelARBrasil();
/* 17 */   private ReceitasDespesas receitasDespesas = new ReceitasDespesas();
/* 18 */   private ApuracaoResultadoBrasil apuracaoResultado = new ApuracaoResultadoBrasil();
/* 19 */   private MovimentacaoRebanho movimentacaoRebanho = new MovimentacaoRebanho();
/* 20 */   private ColecaoDividasARBrasil dividas = new ColecaoDividasARBrasil();
/*    */   
/*    */   public ARBrasil() {
/* 23 */     getMovimentacaoRebanho().setFicha("Movimentação do Rebanho - BRASIL");
/*    */   }
/*    */   
/*    */   public ApuracaoResultadoBrasil getApuracaoResultado() {
/* 27 */     return this.apuracaoResultado;
/*    */   }
/*    */   public ColecaoDividasARBrasil getDividas() {
/* 30 */     return this.dividas;
/*    */   }
/*    */   public IdentificacaoImovelARBrasil getIdentificacaoImovel() {
/* 33 */     return this.identificacaoImovel;
/*    */   }
/*    */   public MovimentacaoRebanho getMovimentacaoRebanho() {
/* 36 */     return this.movimentacaoRebanho;
/*    */   }
/*    */   public ReceitasDespesas getReceitasDespesas() {
/* 39 */     return this.receitasDespesas;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Pendencia> verificarPendencias(int numeroItem) {
/* 44 */     List<Pendencia> retorno = super.verificarPendencias(numeroItem);
/*    */ 
/*    */ 
/*    */     
/* 48 */     boolean temAlgumaOutraFichaPreenchida = (!getReceitasDespesas().isVazio() || !getApuracaoResultado().isVazio() || !getMovimentacaoRebanho().isVazio() || !getDividas().isVazio());
/*    */     
/* 50 */     if (getIdentificacaoImovel().isVazio() && temAlgumaOutraFichaPreenchida) {
/*    */       
/* 52 */       getIdentificacaoImovel().novoObjeto();
/* 53 */       ImovelARBrasil imovel = getIdentificacaoImovel().itens().get(0);
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 58 */       Pendencia p = new Pendencia((byte)3, (Informacao)imovel.getCodigo(), imovel.getCodigo().getNomeCampo(), MensagemUtil.getMensagem("ar_imovel_nao_preenchido"), numeroItem);
/*    */       
/* 60 */       p.setClassePainel(getClasseFicha());
/* 61 */       p.setNomeAba(getNomeAba());
/* 62 */       retorno.add(p);
/*    */     } 
/* 64 */     return retorno;
/*    */   }
/*    */   
/*    */   public String getClasseFicha() {
/* 68 */     return PainelDadosImovel.class.getName();
/*    */   }
/*    */   
/*    */   public String getNomeAba() {
/* 72 */     return "Brasil";
/*    */   }
/*    */   
/*    */   public ImovelARBrasil localizaImovelPorCIB(String cib) {
/* 76 */     Iterator<ImovelARBrasil> iterator = getIdentificacaoImovel().itens().iterator();
/* 77 */     while (iterator.hasNext()) {
/* 78 */       ImovelARBrasil imovelARBrasil = iterator.next();
/* 79 */       if (imovelARBrasil.getCIB().naoFormatado().equals(cib)) {
/* 80 */         return imovelARBrasil;
/*    */       }
/*    */     } 
/* 83 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\atividaderural\brasil\ARBrasil.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */