/*    */ package serpro.ppgd.irpf.rendIsentos;
/*    */ 
/*    */ import serpro.ppgd.irpf.ValorPositivo;
/*    */ import serpro.ppgd.negocio.Observador;
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ public class ItemQuadroAuxiliarValorTransportado extends ItemQuadroAuxiliarAb {
/*    */   protected ValorPositivo valorTransportado;
/*  9 */   protected ValorPositivo valorTotal = new ValorPositivo(this, "Valor Total");
/*    */   
/*    */   public ItemQuadroAuxiliarValorTransportado(Valor valor, Valor valorTransportado) {
/* 12 */     this.valor = (ValorPositivo)valor;
/* 13 */     this.valorTransportado = (ValorPositivo)valorTransportado;
/*    */ 
/*    */     
/* 16 */     Observador obsTotal = new Observador()
/*    */       {
/*    */         
/*    */         public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*    */         {
/* 21 */           ValorPositivo valorPositivo = new ValorPositivo();
/* 22 */           valorPositivo.append('+', ItemQuadroAuxiliarValorTransportado.this.getValor());
/* 23 */           valorPositivo.append('+', (Valor)ItemQuadroAuxiliarValorTransportado.this.getValorTransportado());
/* 24 */           ItemQuadroAuxiliarValorTransportado.this.getValorTotal().setConteudo((Valor)valorPositivo);
/*    */         }
/*    */       };
/* 27 */     obsTotal.notifica(null, null, null, null);
/* 28 */     this.valor.addObservador(obsTotal);
/* 29 */     this.valorTransportado.addObservador(obsTotal);
/*    */   }
/*    */ 
/*    */   
/*    */   public ValorPositivo getValorTransportado() {
/* 34 */     return this.valorTransportado;
/*    */   }
/*    */   
/*    */   public ValorPositivo getValorTotal() {
/* 38 */     return this.valorTotal;
/*    */   }
/*    */ 
/*    */   
/*    */   public Valor getValorRendimento() {
/* 43 */     return (Valor)getValorTotal();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isVazio() {
/* 48 */     return getValor().isVazio();
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendIsentos\ItemQuadroAuxiliarValorTransportado.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */