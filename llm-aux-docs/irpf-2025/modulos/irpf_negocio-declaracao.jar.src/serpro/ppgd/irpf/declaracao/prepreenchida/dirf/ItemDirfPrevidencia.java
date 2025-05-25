/*    */ package serpro.ppgd.irpf.declaracao.prepreenchida.dirf;
/*    */ 
/*    */ import serpro.ppgd.negocio.Alfa;
/*    */ import serpro.ppgd.negocio.CNPJ;
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ public class ItemDirfPrevidencia
/*    */   extends ItemDirf {
/*  9 */   private Alfa numeroDeclaracao = new Alfa(this, "Número da Declaração", 12);
/* 10 */   private Alfa anoCalendario = new Alfa(this, "Ano calendário da consulta", 4);
/* 11 */   private CNPJ cnpjPrevidencia = new CNPJ(this, "NI da Previdência Complementar");
/* 12 */   private Alfa nomePrevidencia = new Alfa(this, "Nome do NI da Previdência Complementar", 60);
/* 13 */   private Alfa tipoPrevidencia = new Alfa(this, "Tipos de Previdência Complementar (1, 2, 3)", 1);
/* 14 */   private Valor valorPagoPrevidencia = new Valor(this, "Total pago da previdência complementar");
/* 15 */   private Valor valorPagoEntePatrocinador = new Valor(this, "Total pago pelo ente Patrocinador");
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Alfa getNumeroDeclaracao() {
/* 21 */     return this.numeroDeclaracao;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Alfa getAnoCalendario() {
/* 28 */     return this.anoCalendario;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CNPJ getCnpjPrevidencia() {
/* 35 */     return this.cnpjPrevidencia;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Alfa getNomePrevidencia() {
/* 42 */     return this.nomePrevidencia;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Alfa getTipoPrevidencia() {
/* 49 */     return this.tipoPrevidencia;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Valor getValorPagoPrevidencia() {
/* 56 */     return this.valorPagoPrevidencia;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Valor getValorPagoEntePatrocinador() {
/* 63 */     return this.valorPagoEntePatrocinador;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\declaracao\prepreenchida\dirf\ItemDirfPrevidencia.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */