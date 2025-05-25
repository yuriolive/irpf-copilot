/*     */ package serpro.ppgd.irpf.gui.pagamentos;
/*     */ 
/*     */ import serpro.ppgd.irpf.gui.TableListaModel;
/*     */ import serpro.ppgd.irpf.pagamentos.Pagamento;
/*     */ import serpro.ppgd.irpf.pagamentos.Pagamentos;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ 
/*     */ public class TableModelPagamentos extends TableListaModel {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public TableModelPagamentos(Pagamentos pObj) {
/*  14 */     super((ObjetoNegocio)pObj);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getColumnCount() {
/*  19 */     return 7;
/*     */   }
/*     */   public Informacao getInformacaoAt(int row, int col) {
/*     */     StringBuilder conteudo;
/*     */     Alfa nomeBeneficiario, tipo;
/*     */     String codTipo;
/*  25 */     Pagamentos dividas = (Pagamentos)getObjetoNegocio();
/*  26 */     Pagamento item = dividas.itens().get(row);
/*     */     
/*  28 */     switch (col) {
/*     */       case 0:
/*  30 */         return super.getInformacaoAt(row, col);
/*     */       case 1:
/*  32 */         if (item.getCodigo().formatado().equals("-1")) {
/*  33 */           return null;
/*     */         }
/*  35 */         return (Informacao)item.getCodigo();
/*     */       
/*     */       case 2:
/*  38 */         conteudo = new StringBuilder();
/*  39 */         if (item.isPensao()) {
/*  40 */           conteudo.append(item.getDependenteOuAlimentando().naoFormatado());
/*     */         } else {
/*  42 */           if (item.getCodigo().naoFormatado().equals("50")) {
/*  43 */             conteudo.append("<html>");
/*     */           }
/*  45 */           conteudo.append(item.getNomeBeneficiario().naoFormatado());
/*     */         } 
/*  47 */         if (item.getCodigo().naoFormatado().equals("50")) {
/*  48 */           conteudo.append("<br>");
/*  49 */           conteudo.append("NIT Empregado Doméstico: ");
/*  50 */           conteudo.append(item.getNitEmpregadoDomestico().formatado());
/*  51 */           conteudo.append("</html>");
/*     */         } 
/*  53 */         nomeBeneficiario = new Alfa();
/*  54 */         nomeBeneficiario.setConteudo(conteudo.toString());
/*  55 */         return (Informacao)nomeBeneficiario;
/*     */       case 3:
/*  57 */         return (Informacao)item.getNiBeneficiario();
/*     */       case 4:
/*  59 */         tipo = new Alfa();
/*  60 */         codTipo = item.getTipo().formatado();
/*  61 */         if (codTipo.equals("T")) {
/*  62 */           tipo.setConteudo("Titular");
/*  63 */         } else if (codTipo.equals("D")) {
/*  64 */           String dep = item.getDependenteOuAlimentando().naoFormatado();
/*  65 */           StringBuilder aux = new StringBuilder("<html>Dependente");
/*  66 */           if (!dep.equals("-1")) {
/*  67 */             aux.append("<br>" + dep);
/*     */           }
/*  69 */           aux.append("</html>");
/*  70 */           tipo.setConteudo(aux.toString());
/*  71 */         } else if (codTipo.equals("A")) {
/*  72 */           StringBuilder aux = new StringBuilder("<html>Alimentando");
/*  73 */           String alim = item.getDependenteOuAlimentando().naoFormatado();
/*  74 */           if (!item.isPensao() && !alim.equals("-1")) {
/*  75 */             aux.append("<br>" + alim);
/*     */           }
/*  77 */           aux.append("</html>");
/*  78 */           tipo.setConteudo(aux.toString());
/*     */         } 
/*     */         
/*  81 */         return (Informacao)tipo;
/*     */       case 5:
/*  83 */         return (Informacao)item.getValorPago();
/*     */       case 6:
/*  85 */         return (Informacao)item.getParcelaNaoDedutivel();
/*     */     } 
/*  87 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getColumnName(int column) {
/*  92 */     switch (column) {
/*     */       case 0:
/*  94 */         return super.getColumnName(column);
/*     */       case 1:
/*  96 */         return "<html><center>Cód.</center></html>";
/*     */       case 2:
/*  98 */         return "<html><center>Nome do<br>Beneficiário</center></html>";
/*     */       case 3:
/* 100 */         return "<html><center>CPF/CNPJ do<br>Beneficiário</center></html>";
/*     */       case 4:
/* 102 */         return "<html><center>Despesa<br>Realizada<br>Com<br>(Tit/Dep/Ali)</center></html>";
/*     */       case 5:
/* 104 */         return "<html><center>Valor Pago</center></html>";
/*     */       case 6:
/* 106 */         return "<html><center>Parc. Não<br>Dedutível</center></html>";
/*     */     } 
/* 108 */     return "";
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\pagamentos\TableModelPagamentos.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */