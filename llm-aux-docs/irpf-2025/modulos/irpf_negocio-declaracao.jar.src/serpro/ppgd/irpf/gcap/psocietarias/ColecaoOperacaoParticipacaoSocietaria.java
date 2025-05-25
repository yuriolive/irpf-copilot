/*     */ package serpro.ppgd.irpf.gcap.psocietarias;
/*     */ 
/*     */ import java.util.List;
/*     */ import serpro.ppgd.irpf.ValorBigDecimal;
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.negocio.Colecao;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ import serpro.ppgd.negocio.util.Validador;
/*     */ 
/*     */ public class ColecaoOperacaoParticipacaoSocietaria extends Colecao<OperacaoParticipacaoSocietaria> {
/*  13 */   private ValorPositivo estoqueInicial = new ValorPositivo((ObjetoNegocio)this, "Estoque inicial", 11, 2); private ValorPositivo saldoInicial = new ValorPositivo((ObjetoNegocio)this, "Saldo inicial (R$)", 11, 2); private ValorPositivo custoMedioInicial = new ValorPositivo((ObjetoNegocio)this, "Custo médio inicial", 11, 6); private ValorPositivo totalQtdAdquirida = new ValorPositivo((ObjetoNegocio)this, "Quantidade total adquirida", 11, 2); private ValorPositivo totalQtdAlienada = new ValorPositivo((ObjetoNegocio)this, "Quantidade total alienada", 11, 2); private ValorPositivo ultimoCustoMedioTransportado = new ValorPositivo((ObjetoNegocio)this, "Último custo médio transportado (R$)", 11, 6);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean estoqueNegativo = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Observador obsCalculoGanhoCapital;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColecaoOperacaoParticipacaoSocietaria(String pNomeCampo, String pNomeFicha, String pNomeAba) {
/*  29 */     super(pNomeCampo, pNomeFicha, pNomeAba);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 236 */     this.obsCalculoGanhoCapital = new Observador()
/*     */       {
/*     */         
/*     */         public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */         {
/* 241 */           ColecaoOperacaoParticipacaoSocietaria.this.calcularGanhoTotal(); } }; } public ColecaoOperacaoParticipacaoSocietaria(String pNomeCampo) { super(pNomeCampo); this.obsCalculoGanhoCapital = new Observador() { public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) { ColecaoOperacaoParticipacaoSocietaria.this.calcularGanhoTotal(); } }; } public ColecaoOperacaoParticipacaoSocietaria(int tamanho) { super(tamanho); this.obsCalculoGanhoCapital = new Observador() { public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) { ColecaoOperacaoParticipacaoSocietaria.this.calcularGanhoTotal(); } }; } public ValorPositivo getEstoqueInicial() { return this.estoqueInicial; } public ValorPositivo getSaldoInicial() { return this.saldoInicial; } public ValorPositivo getCustoMedioInicial() { return this.custoMedioInicial; } public int obterOrdemOperacaoParticipacaoSocietaria(OperacaoParticipacaoSocietaria operacao) { int ordem = 0; List<OperacaoParticipacaoSocietaria> listaOperacoesOrdenada = itens(); boolean dataOperacaoNovaValida = (Validador.verificaData(operacao.getData().formatado()) == 0); boolean tipoOperacaoNovaValido = (operacao.getTipo().naoFormatado().equals("1") || operacao.getTipo().naoFormatado().equals("2") || operacao.getTipo().naoFormatado().equals("0")); for (; ordem < listaOperacoesOrdenada.size(); ordem++) { OperacaoParticipacaoSocietaria op = listaOperacoesOrdenada.get(ordem); boolean dataOperacaoValida = (Validador.verificaData(op.getData().formatado()) == 0); boolean tipoOperacaoValido = (op.getTipo().naoFormatado().equals("1") || op.getTipo().naoFormatado().equals("2") || op.getTipo().naoFormatado().equals("0")); String dataOperacaoNova = "99999999"; String tipoOperacaoNova = "9"; String dataOperacao = "99999999"; String tipoOperacao = "9"; if (dataOperacaoNovaValida) dataOperacaoNova = operacao.getData().getAno() + operacao.getData().getAno() + operacao.getData().getMes();  if (tipoOperacaoNovaValido) tipoOperacaoNova = operacao.getTipo().naoFormatado();  if (dataOperacaoValida) dataOperacao = op.getData().getAno() + op.getData().getAno() + op.getData().getMes();  if (tipoOperacaoValido) tipoOperacao = op.getTipo().naoFormatado();  String chaveOperacaoNova = dataOperacaoNova + dataOperacaoNova; String chaveOperacao = dataOperacao + dataOperacao; if (chaveOperacaoNova.compareTo(chaveOperacao) < 0) break;  }  return ordem; } public int alterarOrdemOperacaoParticipacaoSocietaria(int ordem) { OperacaoParticipacaoSocietaria op = itens().remove(ordem); return inserirOperacaoParticipacaoSocietaria(op); } public int inserirOperacaoParticipacaoSocietaria(OperacaoParticipacaoSocietaria operacao) { int ordem = obterOrdemOperacaoParticipacaoSocietaria(operacao); itens().add(ordem, operacao); return ordem; } public int inserirNovaOperacaoSaldoInicial() { OperacaoParticipacaoSocietaria op = new OperacaoParticipacaoSocietaria(); op.getTipo().setConteudo("0"); op.getData().setConteudo("31/12/2017"); op.getData().setReadOnly(true); return inserirOperacaoParticipacaoSocietaria(op); } public int inserirNovaOperacaoCompra() { OperacaoParticipacaoSocietaria op = new OperacaoParticipacaoSocietaria(); op.getTipo().setConteudo("1"); return inserirOperacaoParticipacaoSocietaria(op); } public int inserirNovaOperacaoVenda() { OperacaoParticipacaoSocietaria op = new OperacaoParticipacaoSocietaria(); op.getTipo().setConteudo("2"); return inserirOperacaoParticipacaoSocietaria(op); } public void recalcularGanhoTotal() { calcularGanhoTotal(); } public void calcularSaldo(OperacaoParticipacaoSocietaria operacao, String estoqueAnterior, String saldoAnterior, String custoMedioAnterior) { ValorBigDecimal estoque = new ValorBigDecimal(estoqueAnterior); ValorPositivo saldo = new ValorPositivo(saldoAnterior); ValorPositivo custoMedio = new ValorPositivo(custoMedioAnterior); ValorPositivo custoAlienacao = new ValorPositivo("0,00"); if (Validador.verificaData(operacao.getData().formatado()) == 0) if (operacao.getTipo().naoFormatado().equals("1") || operacao.getTipo().naoFormatado().equals("0")) { estoque.append('+', (Valor)operacao.getQuantidade()); if (estoque.comparacao(">", "0,00")) { saldo.append('+', (Valor)operacao.getValor()); } else { saldo.clear(); }  custoMedio.setConteudo((Valor)calcularCustoMedio(estoque, saldo)); } else if (operacao.getTipo().naoFormatado().equals("2")) { custoAlienacao.setConteudo(operacao.getQuantidade().operacao('*', (Valor)custoMedio)); estoque.append('-', (Valor)operacao.getQuantidade()); if (estoque.comparacao(">", "0,00") && saldo.comparacao(">", (Valor)custoAlienacao)) { saldo.append('-', (Valor)custoAlienacao); } else { saldo.clear(); }  }   operacao.getCustoAlienacao().setConteudo((Valor)custoAlienacao); operacao.getEstoque().setConteudo(estoque); operacao.getSaldo().setConteudo((Valor)saldo); operacao.getCustoMedio().setConteudo((Valor)custoMedio); } public ValorPositivo calcularCustoMedio(ValorBigDecimal estoque, ValorPositivo saldo) { ValorPositivo custoMedio = new ValorPositivo("0,000000"); if (estoque.comparacao(">", "0,00")) { custoMedio.setConteudo(saldo.operacao('/', (Valor)estoque)); } else { custoMedio.clear(); }  return custoMedio; } private void calcularGanhoTotal() { ValorBigDecimal estoque = new ValorBigDecimal(getEstoqueInicial().naoFormatado()); ValorPositivo saldo = new ValorPositivo(getSaldoInicial().naoFormatado()); ValorPositivo custoMedio = new ValorPositivo(getCustoMedioInicial().naoFormatado()); ValorPositivo totalQuantidadeAdquirida = new ValorPositivo("0,00"); ValorPositivo totalQuantidadeAlienada = new ValorPositivo("0,00"); this.estoqueNegativo = false; for (OperacaoParticipacaoSocietaria op : itens()) { calcularSaldo(op, estoque.naoFormatado(), saldo.naoFormatado(), custoMedio.naoFormatado()); estoque.setConteudo(op.getEstoque()); saldo.setConteudo((Valor)op.getSaldo()); custoMedio.setConteudo((Valor)op.getCustoMedio()); if (op.getTipo().naoFormatado().equals("1") || op.getTipo().naoFormatado().equals("0")) { totalQuantidadeAdquirida.append('+', (Valor)op.getQuantidade()); } else if (op.getTipo().naoFormatado().equals("2")) { totalQuantidadeAlienada.append('+', (Valor)op.getQuantidade()); }  this.estoqueNegativo = (this.estoqueNegativo || estoque.comparacao("<", "0,00")); }  getTotalQtdAdquirida().setConteudo((Valor)totalQuantidadeAdquirida); getTotalQtdAlienada().setConteudo((Valor)totalQuantidadeAlienada); } public void objetoInserido(OperacaoParticipacaoSocietaria operacao) { operacao.getData().addObservador(this.obsCalculoGanhoCapital); operacao.getQuantidade().addObservador(this.obsCalculoGanhoCapital); operacao.getValor().addObservador(this.obsCalculoGanhoCapital); } public void objetoRemovido(Object o) { OperacaoParticipacaoSocietaria op = (OperacaoParticipacaoSocietaria)o; op.getData().removeObservador(this.obsCalculoGanhoCapital); op.getQuantidade().removeObservador(this.obsCalculoGanhoCapital); op.getValor().removeObservador(this.obsCalculoGanhoCapital); } public ColecaoOperacaoParticipacaoSocietaria() { this.obsCalculoGanhoCapital = new Observador() { public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) { ColecaoOperacaoParticipacaoSocietaria.this.calcularGanhoTotal(); }
/*     */          }
/*     */       ;
/*     */     this.ultimoCustoMedioTransportado.setHabilitado(false); }
/*     */    public ValorPositivo getTotalQtdAdquirida() {
/* 246 */     return this.totalQtdAdquirida;
/*     */   }
/*     */   
/*     */   public ValorPositivo getTotalQtdAlienada() {
/* 250 */     return this.totalQtdAlienada;
/*     */   }
/*     */ 
/*     */   
/*     */   public OperacaoParticipacaoSocietaria instanciaNovoObjeto() {
/* 255 */     return new OperacaoParticipacaoSocietaria();
/*     */   }
/*     */   
/*     */   public boolean isEstoqueNegativo() {
/* 259 */     return this.estoqueNegativo;
/*     */   }
/*     */   
/*     */   public ValorPositivo getUltimoCustoMedioTransportado() {
/* 263 */     return this.ultimoCustoMedioTransportado;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\psocietarias\ColecaoOperacaoParticipacaoSocietaria.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */