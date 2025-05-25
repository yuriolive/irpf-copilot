/*     */ package serpro.ppgd.irpf.gcap.especie;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.irpf.gcap.ObjetoGCAP;
/*     */ import serpro.ppgd.negocio.CPF;
/*     */ import serpro.ppgd.negocio.Data;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TotalizacaoMoedasAlienadas
/*     */   extends ObjetoNegocio
/*     */   implements ObjetoGCAP
/*     */ {
/*  22 */   private MoedasAlienadasMensal janeiro = new MoedasAlienadasMensal();
/*  23 */   private MoedasAlienadasMensal fevereiro = new MoedasAlienadasMensal();
/*  24 */   private MoedasAlienadasMensal marco = new MoedasAlienadasMensal();
/*  25 */   private MoedasAlienadasMensal abril = new MoedasAlienadasMensal();
/*  26 */   private MoedasAlienadasMensal maio = new MoedasAlienadasMensal();
/*  27 */   private MoedasAlienadasMensal junho = new MoedasAlienadasMensal();
/*  28 */   private MoedasAlienadasMensal julho = new MoedasAlienadasMensal();
/*  29 */   private MoedasAlienadasMensal agosto = new MoedasAlienadasMensal();
/*  30 */   private MoedasAlienadasMensal setembro = new MoedasAlienadasMensal();
/*  31 */   private MoedasAlienadasMensal outubro = new MoedasAlienadasMensal();
/*  32 */   private MoedasAlienadasMensal novembro = new MoedasAlienadasMensal();
/*  33 */   private MoedasAlienadasMensal dezembro = new MoedasAlienadasMensal();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  39 */   private CPF cpf = new CPF(this, "CPF do declarante");
/*  40 */   private Data dataInicioPermanencia = new Data(this, "Data Inicio Permanência");
/*  41 */   private Data dataFimPermanencia = new Data(this, "Data Fim Permanência");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MoedasAlienadasMensal getJaneiro() {
/*  47 */     return this.janeiro;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MoedasAlienadasMensal getFevereiro() {
/*  54 */     return this.fevereiro;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MoedasAlienadasMensal getMarco() {
/*  61 */     return this.marco;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MoedasAlienadasMensal getAbril() {
/*  68 */     return this.abril;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MoedasAlienadasMensal getMaio() {
/*  75 */     return this.maio;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MoedasAlienadasMensal getJunho() {
/*  82 */     return this.junho;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MoedasAlienadasMensal getJulho() {
/*  89 */     return this.julho;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MoedasAlienadasMensal getAgosto() {
/*  96 */     return this.agosto;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MoedasAlienadasMensal getSetembro() {
/* 103 */     return this.setembro;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MoedasAlienadasMensal getOutubro() {
/* 110 */     return this.outubro;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MoedasAlienadasMensal getNovembro() {
/* 117 */     return this.novembro;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MoedasAlienadasMensal getDezembro() {
/* 124 */     return this.dezembro;
/*     */   }
/*     */   
/*     */   public MoedasAlienadasMensal getMes(int mes) {
/* 128 */     switch (mes) { case 0:
/* 129 */         return getJaneiro();
/* 130 */       case 1: return getFevereiro();
/* 131 */       case 2: return getMarco();
/* 132 */       case 3: return getAbril();
/* 133 */       case 4: return getMaio();
/* 134 */       case 5: return getJunho();
/* 135 */       case 6: return getJulho();
/* 136 */       case 7: return getAgosto();
/* 137 */       case 8: return getSetembro();
/* 138 */       case 9: return getOutubro();
/* 139 */       case 10: return getNovembro();
/* 140 */       case 11: return getDezembro(); }
/* 141 */      return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<MoedasAlienadasMensal> getMeses() {
/* 146 */     List<MoedasAlienadasMensal> meses = new ArrayList<>();
/* 147 */     meses.add(this.janeiro);
/* 148 */     meses.add(this.fevereiro);
/* 149 */     meses.add(this.marco);
/* 150 */     meses.add(this.abril);
/* 151 */     meses.add(this.maio);
/* 152 */     meses.add(this.junho);
/* 153 */     meses.add(this.julho);
/* 154 */     meses.add(this.agosto);
/* 155 */     meses.add(this.setembro);
/* 156 */     meses.add(this.outubro);
/* 157 */     meses.add(this.novembro);
/* 158 */     meses.add(this.dezembro);
/* 159 */     return meses;
/*     */   }
/*     */   
/*     */   public ValorPositivo obterValorAnual(int campo) {
/* 163 */     ValorPositivo total = new ValorPositivo();
/* 164 */     getMeses().forEach(mes -> total.append('+', (Valor)mes.obterValor(campo)));
/*     */ 
/*     */     
/* 167 */     return total;
/*     */   }
/*     */ 
/*     */   
/*     */   public CPF getCpf() {
/* 172 */     return this.cpf;
/*     */   }
/*     */ 
/*     */   
/*     */   public Data getDataInicioPermanencia() {
/* 177 */     return this.dataInicioPermanencia;
/*     */   }
/*     */ 
/*     */   
/*     */   public Data getDataFimPermanencia() {
/* 182 */     return this.dataFimPermanencia;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\especie\TotalizacaoMoedasAlienadas.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */