/*    */ package serpro.ppgd.irpf;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import org.apache.commons.io.FileUtils;
/*    */ import serpro.ppgd.negocio.Alfa;
/*    */ import serpro.ppgd.negocio.DataHora;
/*    */ import serpro.ppgd.negocio.IdentificadorDeclaracaoXML;
/*    */ import serpro.ppgd.negocio.util.LogPPGD;
/*    */ 
/*    */ public class IdentificadorDeclaracaoTransmitida
/*    */   extends IdentificadorDeclaracao implements Comparable<IdentificadorDeclaracao>, IdentificadorDeclaracaoXML {
/* 13 */   private DataHora dataHoraTransmissao = new DataHora(this, "Data/Hora Transmissão");
/* 14 */   private Alfa numReciboEntrega = new Alfa(this, "Nº do Recibo de Entrega da Declaração", 12);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public DataHora getDataHoraTransmissao() {
/* 24 */     return this.dataHoraTransmissao;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Alfa getNumReciboEntrega() {
/* 31 */     return this.numReciboEntrega;
/*    */   }
/*    */   
/*    */   public static void main(String[] args) {
/* 35 */     String filenameDEC = "65449169868-IRPF-A-2020-2019-ORIGI.DEC";
/* 36 */     String filenameREC = "65449169868-IRPF-A-2020-2019-ORIGI.REC";
/* 37 */     String padraoNome = "-IRPF-A-2020-2019-ORIGI.";
/* 38 */     String padraoDir = "/home/01103312570/ProgramasRFB/IRPF2020/transmitidas/";
/* 39 */     File DECPadrao = new File(padraoDir + padraoDir);
/* 40 */     File RECPadrao = new File(padraoDir + padraoDir);
/* 41 */     int offset = 0;
/* 42 */     for (int i = 0; i < 2000; i++) {
/* 43 */       String variavel = "0000000" + String.valueOf(offset);
/* 44 */       variavel = variavel.substring(variavel.length() - 7);
/* 45 */       offset++;
/* 46 */       String newFileDEC = padraoDir + "9999" + padraoDir + variavel + "DEC";
/* 47 */       String newFileREC = padraoDir + "9999" + padraoDir + variavel + "REC";
/* 48 */       System.out.println(newFileDEC);
/* 49 */       File copiedDEC = new File(newFileDEC);
/* 50 */       File copiedREC = new File(newFileREC);
/*    */       try {
/* 52 */         FileUtils.copyFile(DECPadrao, copiedDEC);
/* 53 */         FileUtils.copyFile(RECPadrao, copiedREC);
/* 54 */       } catch (IOException ex) {
/*    */         
/* 56 */         LogPPGD.erro(ex.getMessage());
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\IdentificadorDeclaracaoTransmitida.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */