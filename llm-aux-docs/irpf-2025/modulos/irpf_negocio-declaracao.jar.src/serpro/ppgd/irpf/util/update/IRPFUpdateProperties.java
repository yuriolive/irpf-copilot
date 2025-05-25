/*     */ package serpro.ppgd.irpf.util.update;
/*     */ 
/*     */ public class IRPFUpdateProperties
/*     */ {
/*   5 */   private String ultimaVersao = null;
/*   6 */   private String versaoAtual = null;
/*   7 */   private String enderecoServidor = null;
/*   8 */   private String arquivoZip = null;
/*   9 */   private String mensagem = null;
/*  10 */   private TipoAtualizacao tipoAtualizacao = null;
/*  11 */   private String ultimaVersaoManual = null;
/*     */   private boolean downloadManual = false;
/*  13 */   private int periodoEspera = 0;
/*  14 */   private String enderecoServidorDownloadManual = "https://idg.receita.fazenda.gov.br/interface/cidadao/irpf/";
/*     */   
/*     */   public enum TipoAtualizacao {
/*  17 */     Obrigatoria, Opcional;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEnderecoServidor() {
/*  24 */     return this.enderecoServidor;
/*     */   }
/*     */   
/*     */   public void setEnderecoServidor(String enderecoServidor) {
/*  28 */     this.enderecoServidor = enderecoServidor;
/*     */   }
/*     */   
/*     */   public String getArquivoZip() {
/*  32 */     return this.arquivoZip;
/*     */   }
/*     */   
/*     */   public void setArquivoZip(String arquivoZip) {
/*  36 */     this.arquivoZip = arquivoZip;
/*     */   }
/*     */   
/*     */   public String getMensagem() {
/*  40 */     return this.mensagem;
/*     */   }
/*     */   
/*     */   public void setMensagem(String mensagem) {
/*  44 */     this.mensagem = mensagem;
/*     */   }
/*     */   
/*     */   public String getUltimaVersao() {
/*  48 */     return this.ultimaVersao;
/*     */   }
/*     */   
/*     */   public void setUltimaVersao(String ultimaVersao) {
/*  52 */     this.ultimaVersao = ultimaVersao;
/*     */   }
/*     */   
/*     */   public String getVersaoAtual() {
/*  56 */     return this.versaoAtual;
/*     */   }
/*     */   
/*     */   public void setVersaoAtual(String versaoAtual) {
/*  60 */     this.versaoAtual = versaoAtual;
/*     */   }
/*     */   
/*     */   public TipoAtualizacao getTipoAtualizacao() {
/*  64 */     return this.tipoAtualizacao;
/*     */   }
/*     */   
/*     */   public void setTipoAtualizacao(TipoAtualizacao tipoAtualizacao) {
/*  68 */     this.tipoAtualizacao = tipoAtualizacao;
/*     */   }
/*     */   
/*     */   public String getUltimaVersaoManual() {
/*  72 */     return this.ultimaVersaoManual;
/*     */   }
/*     */   
/*     */   public void setUltimaVersaoManual(String ultimaVersaoManual) {
/*  76 */     this.ultimaVersaoManual = ultimaVersaoManual;
/*     */   }
/*     */   
/*     */   public boolean isDownloadManual() {
/*  80 */     return this.downloadManual;
/*     */   }
/*     */   
/*     */   public void setDownloadManual(boolean downloadManual) {
/*  84 */     this.downloadManual = downloadManual;
/*     */   }
/*     */   
/*     */   public int getPeriodoEspera() {
/*  88 */     return this.periodoEspera;
/*     */   }
/*     */   
/*     */   public void setPeriodoEspera(int periodoEspera) {
/*  92 */     this.periodoEspera = periodoEspera;
/*     */   }
/*     */   
/*     */   public String getEnderecoServidorDownloadManual() {
/*  96 */     return this.enderecoServidorDownloadManual;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnderecoServidorDownloadManual(String enderecoServidorDownloadManual) {
/* 101 */     this.enderecoServidorDownloadManual = enderecoServidorDownloadManual;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irp\\uti\\update\IRPFUpdateProperties.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */