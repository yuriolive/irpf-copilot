/*    */ package serpro.ppgd.irpf.alimentandos;
/*    */ 
/*    */ import java.lang.ref.WeakReference;
/*    */ import java.util.Iterator;
/*    */ import serpro.ppgd.irpf.dependentes.Dependente;
/*    */ import serpro.ppgd.irpf.dependentes.Dependentes;
/*    */ import serpro.ppgd.irpf.util.MensagemUtil;
/*    */ import serpro.ppgd.negocio.CPF;
/*    */ import serpro.ppgd.negocio.ConstantesGlobais;
/*    */ import serpro.ppgd.negocio.RetornoValidacao;
/*    */ import serpro.ppgd.negocio.ValidadorDefault;
/*    */ 
/*    */ 
/*    */ public class ValidadorAlimentandoIgualDependente
/*    */   extends ValidadorDefault
/*    */ {
/*    */   private WeakReference<Dependentes> refColDependentes;
/*    */   
/*    */   public ValidadorAlimentandoIgualDependente(Dependentes dependentes) {
/* 20 */     super((byte)2);
/*    */     
/* 22 */     this.refColDependentes = new WeakReference<>(dependentes);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public RetornoValidacao validarImplementado() {
/* 28 */     Iterator<Dependente> it = ((Dependentes)this.refColDependentes.get()).itens().iterator();
/* 29 */     while (it.hasNext()) {
/* 30 */       Dependente dep = it.next();
/*    */       
/* 32 */       CPF cpfInfo = (CPF)getInformacao();
/* 33 */       if (cpfInfo.naoFormatado().equals(dep.getCpfDependente().naoFormatado())) {
/* 34 */         return new RetornoValidacao(
/* 35 */             MensagemUtil.getMensagem("alimentando_cpf_igual_dependente", new String[] { ConstantesGlobais.ANO_BASE }), (byte)2);
/*    */       }
/*    */     } 
/*    */ 
/*    */     
/* 40 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\alimentandos\ValidadorAlimentandoIgualDependente.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */