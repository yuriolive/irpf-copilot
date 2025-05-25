/*     */ package serpro.ppgd.irpf.rendIsentos;
/*     */ 
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.Iterator;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.negocio.Colecao;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ public class ColecaoItemQuadroPensaoMolestiaGrave extends Colecao<ItemQuadroPensaoMolestiaGrave> {
/*  12 */   private Valor totais = new Valor((ObjetoNegocio)this, "Totais");
/*  13 */   private Valor totalRendimento = new Valor((ObjetoNegocio)this, "Total Rendimento");
/*  14 */   private Valor totalIRRFDependentes = new Valor((ObjetoNegocio)this, "Total IRRF Dependentes");
/*  15 */   private Valor totalIRRFTitular = new Valor((ObjetoNegocio)this, "Total IRRF do Titular");
/*  16 */   private Valor total13Salario = new Valor((ObjetoNegocio)this, "Total 13 Salário");
/*  17 */   private Valor totalIRRF13SalarioDependentes = new Valor((ObjetoNegocio)this, "Total IRRF sobre o 13 Salário");
/*  18 */   private Valor totalIRRF13SalarioTitular = new Valor((ObjetoNegocio)this, "Total IRRF sobre o 13 Salário do Titular");
/*  19 */   private Valor totalPrevidenciaOficialTitular = new Valor((ObjetoNegocio)this, "Total Previdência Oficial do Titular");
/*  20 */   private Valor totalPrevidenciaOficialDependentes = new Valor((ObjetoNegocio)this, "Total Previdência Oficial dos Dependentes");
/*     */   
/*  22 */   private WeakReference<DeclaracaoIRPF> dec = null;
/*     */   
/*     */   public ColecaoItemQuadroPensaoMolestiaGrave() {
/*  25 */     getTotais().setReadOnly(true);
/*     */   }
/*     */   
/*     */   public Valor getTotais() {
/*  29 */     return this.totais;
/*     */   }
/*     */   
/*     */   public Valor getTotalRendimento() {
/*  33 */     return this.totalRendimento;
/*     */   }
/*     */   
/*     */   public Valor getTotalIRRFTitular() {
/*  37 */     return this.totalIRRFTitular;
/*     */   }
/*     */   
/*     */   public Valor getTotalIRRF13SalarioTitular() {
/*  41 */     return this.totalIRRF13SalarioTitular;
/*     */   }
/*     */   
/*     */   public Valor getTotalIRRFDependentes() {
/*  45 */     return this.totalIRRFDependentes;
/*     */   }
/*     */   
/*     */   public Valor getTotal13Salario() {
/*  49 */     return this.total13Salario;
/*     */   }
/*     */   
/*     */   public Valor getTotalIRRF13SalarioDependentes() {
/*  53 */     return this.totalIRRF13SalarioDependentes;
/*     */   }
/*     */   
/*     */   public Valor getTotalPrevidenciaOficialTitular() {
/*  57 */     return this.totalPrevidenciaOficialTitular;
/*     */   }
/*     */   
/*     */   public Valor getTotalPrevidenciaOficialDependentes() {
/*  61 */     return this.totalPrevidenciaOficialDependentes;
/*     */   }
/*     */ 
/*     */   
/*     */   public void objetoInserido(ItemQuadroPensaoMolestiaGrave itemQuadroPensaoMolestiaGrave) {
/*  66 */     itemQuadroPensaoMolestiaGrave.getValor().addObservador((Observador)this);
/*  67 */     itemQuadroPensaoMolestiaGrave.getValorIRRF().addObservador((Observador)this);
/*  68 */     itemQuadroPensaoMolestiaGrave.getValor13Salario().addObservador((Observador)this);
/*  69 */     itemQuadroPensaoMolestiaGrave.getValorIRRF13Salario().addObservador((Observador)this);
/*  70 */     itemQuadroPensaoMolestiaGrave.getValorPrevidenciaOficial().addObservador((Observador)this);
/*     */     
/*  72 */     calculaTotal();
/*     */   }
/*     */ 
/*     */   
/*     */   public void objetoRemovido(Object o) {
/*  77 */     ((ItemQuadroPensaoMolestiaGrave)o).getValor().removeObservador((Observador)this);
/*  78 */     ((ItemQuadroPensaoMolestiaGrave)o).getValorIRRF().removeObservador((Observador)this);
/*  79 */     ((ItemQuadroPensaoMolestiaGrave)o).getValor13Salario().removeObservador((Observador)this);
/*  80 */     ((ItemQuadroPensaoMolestiaGrave)o).getValorIRRF13Salario().removeObservador((Observador)this);
/*  81 */     ((ItemQuadroPensaoMolestiaGrave)o).getValorPrevidenciaOficial().removeObservador((Observador)this);
/*     */     
/*  83 */     calculaTotal();
/*     */   }
/*     */   
/*     */   private void calculaTotal() {
/*  87 */     Valor total = new Valor();
/*  88 */     Valor totalRendimento = new Valor();
/*  89 */     Valor total13Salario = new Valor();
/*  90 */     Valor totalIRRFDependentes = new Valor();
/*  91 */     Valor totalIRRFTitular = new Valor();
/*  92 */     Valor totalIRRF13SalarioDependentes = new Valor();
/*  93 */     Valor totalIRRF13SalarioTitular = new Valor();
/*  94 */     Valor totalPrevidenciaOficialDependentes = new Valor();
/*  95 */     Valor totalPrevidenciaOficialTitular = new Valor();
/*     */     
/*  97 */     Iterator<ItemQuadroPensaoMolestiaGrave> it = itens().iterator();
/*  98 */     while (it.hasNext()) {
/*  99 */       ItemQuadroPensaoMolestiaGrave item = it.next();
/* 100 */       totalRendimento.append('+', item.getValor());
/* 101 */       total13Salario.append('+', (Valor)item.getValor13Salario());
/*     */       
/* 103 */       if (item.getTipoBeneficiario().naoFormatado().equals("Titular")) {
/* 104 */         totalIRRFTitular.append('+', (Valor)item.getValorIRRF());
/* 105 */         totalIRRF13SalarioTitular.append('+', (Valor)item.getValorIRRF13Salario());
/* 106 */         totalPrevidenciaOficialTitular.append('+', (Valor)item.getValorPrevidenciaOficial()); continue;
/*     */       } 
/* 108 */       totalIRRFDependentes.append('+', (Valor)item.getValorIRRF());
/* 109 */       totalIRRF13SalarioDependentes.append('+', (Valor)item.getValorIRRF13Salario());
/* 110 */       totalPrevidenciaOficialDependentes.append('+', (Valor)item.getValorPrevidenciaOficial());
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 115 */     total.append('+', totalRendimento);
/* 116 */     total.append('+', total13Salario);
/* 117 */     getTotais().setConteudo(total);
/*     */     
/* 119 */     getTotalRendimento().setConteudo(totalRendimento);
/* 120 */     getTotal13Salario().setConteudo(total13Salario);
/*     */     
/* 122 */     getTotalIRRFDependentes().setConteudo(totalIRRFDependentes);
/* 123 */     getTotalIRRFTitular().setConteudo(totalIRRFTitular);
/*     */     
/* 125 */     getTotalIRRF13SalarioDependentes().setConteudo(totalIRRF13SalarioDependentes);
/* 126 */     getTotalIRRF13SalarioTitular().setConteudo(totalIRRF13SalarioTitular);
/*     */     
/* 128 */     getTotalPrevidenciaOficialDependentes().setConteudo(totalPrevidenciaOficialDependentes);
/* 129 */     getTotalPrevidenciaOficialTitular().setConteudo(totalPrevidenciaOficialTitular);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/* 135 */     if (nomePropriedade.equals("Valor") || nomePropriedade
/* 136 */       .equals("Rendimento") || nomePropriedade
/* 137 */       .equals("IRRF") || nomePropriedade
/* 138 */       .equals("13º Salário") || nomePropriedade
/* 139 */       .equals("IRRF sobre o 13º Salário") || nomePropriedade
/* 140 */       .equals("Previdência Oficial")) {
/* 141 */       calculaTotal();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean possuiDependenteComCPF(String cpf) {
/* 148 */     if ("".equals(cpf.trim())) {
/* 149 */       return false;
/*     */     }
/*     */     
/* 152 */     Iterator<ItemQuadroPensaoMolestiaGrave> it = itens().iterator();
/* 153 */     while (it.hasNext()) {
/* 154 */       ItemQuadroPensaoMolestiaGrave item = it.next();
/*     */       
/* 156 */       if (item.getTipoBeneficiario().naoFormatado().equals("Dependente") && cpf.equals(item.getCpfBeneficiario().naoFormatado())) {
/* 157 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 161 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void excluirDependentesComCPF(String cpf) {
/* 166 */     Iterator<ItemQuadroPensaoMolestiaGrave> it = itens().iterator();
/* 167 */     while (it.hasNext()) {
/* 168 */       ItemQuadroPensaoMolestiaGrave item = it.next();
/*     */       
/* 170 */       if (item.getTipoBeneficiario().naoFormatado().equals("Dependente") && cpf.equals(item.getCpfBeneficiario().naoFormatado())) {
/* 171 */         it.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemQuadroPensaoMolestiaGrave instanciaNovoObjeto() {
/* 178 */     return new ItemQuadroPensaoMolestiaGrave(this.dec.get());
/*     */   }
/*     */   
/*     */   public WeakReference<DeclaracaoIRPF> getDec() {
/* 182 */     return this.dec;
/*     */   }
/*     */   
/*     */   public void setDec(WeakReference<DeclaracaoIRPF> dec) {
/* 186 */     this.dec = dec;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendIsentos\ColecaoItemQuadroPensaoMolestiaGrave.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */