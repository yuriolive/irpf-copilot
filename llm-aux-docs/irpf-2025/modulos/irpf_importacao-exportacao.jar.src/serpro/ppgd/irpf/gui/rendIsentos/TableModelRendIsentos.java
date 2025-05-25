/*     */ package serpro.ppgd.irpf.gui.rendIsentos;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import serpro.ppgd.irpf.gui.TableListaModel;
/*     */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroAuxiliarAb;
/*     */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroTransporteDetalhado;
/*     */ import serpro.ppgd.irpf.rendIsentos.RendIsentos;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.Colecao;
/*     */ import serpro.ppgd.negocio.ElementoTabela;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ public class TableModelRendIsentos
/*     */   extends TableListaModel {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected List<Colecao<? extends ItemQuadroAuxiliarAb>> rendIsentos;
/*     */   protected List<ElementoTabela> tiposRendimentos;
/*     */   
/*     */   public TableModelRendIsentos(RendIsentos rendimentosIsentos) {
/*  24 */     super((ObjetoNegocio)rendimentosIsentos);
/*     */     
/*  26 */     this.tiposRendimentos = CadastroTabelasIRPF.recuperarTiposRendimentosIsentos();
/*  27 */     this.rendIsentos = rendimentosIsentos.getColecoesRendimentos();
/*     */   }
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
/*     */   public void limparRegistrosEmBranco() {
/* 144 */     for (Colecao<? extends ItemQuadroAuxiliarAb> colecao : this.rendIsentos) {
/* 145 */       colecao.excluirRegistrosEmBranco();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemQuadroAuxiliarAb novoItemRendimento(String tipo) {
/* 157 */     Colecao<? extends ItemQuadroAuxiliarAb> col = obterColecaoRendimento(tipo);
/*     */     
/* 159 */     ItemQuadroAuxiliarAb item = null;
/* 160 */     if (col != null) {
/* 161 */       item = col.itens().get(col.novoObjeto());
/*     */     }
/*     */ 
/*     */     
/* 165 */     return item;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Colecao<? extends ItemQuadroAuxiliarAb> obterColecaoRendimento(String tipo) {
/* 175 */     for (int i = 0; i < this.tiposRendimentos.size(); i++) {
/* 176 */       if (((ElementoTabela)this.tiposRendimentos.get(i)).getConteudo(0).equals(tipo)) {
/* 177 */         return this.rendIsentos.get(i);
/*     */       }
/*     */     } 
/*     */     
/* 181 */     return null;
/*     */   }
/*     */   
/*     */   protected ElementoTabela obterTipoRendimento(int row) {
/* 185 */     ParIndices indices = obterIndicesPorLinha(row);
/*     */     
/* 187 */     return this.tiposRendimentos.get(indices.getIndiceColecao());
/*     */   }
/*     */   
/*     */   protected class ParIndices {
/* 191 */     private int indiceColecao = 0;
/* 192 */     private int indiceItemColecao = 0;
/*     */     
/*     */     public int getIndiceColecao() {
/* 195 */       return this.indiceColecao;
/*     */     }
/*     */     public void setIndiceColecao(int indiceColecao) {
/* 198 */       this.indiceColecao = indiceColecao;
/*     */     }
/*     */     public int getIndiceItemColecao() {
/* 201 */       return this.indiceItemColecao;
/*     */     }
/*     */     public void setIndiceItemColecao(int indiceItemColecao) {
/* 204 */       this.indiceItemColecao = indiceItemColecao;
/*     */     }
/*     */   }
/*     */   
/*     */   protected ParIndices obterIndicesPorLinha(int row) {
/* 209 */     int indiceItemColecao = row;
/*     */     
/* 211 */     ParIndices indices = new ParIndices();
/* 212 */     for (int indiceColecao = 0; indiceColecao < this.rendIsentos.size(); indiceColecao++) {
/* 213 */       if (indiceItemColecao < ((Colecao)this.rendIsentos.get(indiceColecao)).itens().size()) {
/* 214 */         indices.setIndiceColecao(indiceColecao);
/* 215 */         indices.setIndiceItemColecao(indiceItemColecao);
/*     */         
/* 217 */         return indices;
/*     */       } 
/* 219 */       indiceItemColecao -= ((Colecao)this.rendIsentos.get(indiceColecao)).itens().size();
/*     */     } 
/*     */     
/* 222 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ObjetoNegocio getObjetoNegocio(int rowIndex) {
/* 228 */     ParIndices indices = obterIndicesPorLinha(rowIndex);
/*     */     
/* 230 */     if (indices != null) {
/* 231 */       return ((Colecao)this.rendIsentos.get(indices.getIndiceColecao())).itens().get(indices.getIndiceItemColecao());
/*     */     }
/*     */     
/* 234 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeObjetoNegocio(Integer[] rowIndexes) {
/* 239 */     Arrays.sort((Object[])rowIndexes);
/*     */     
/* 241 */     for (int i = rowIndexes.length - 1; i >= 0; i--) {
/* 242 */       ParIndices indices = obterIndicesPorLinha(rowIndexes[i].intValue());
/*     */       
/* 244 */       if (indices != null) {
/* 245 */         ((Colecao)this.rendIsentos.get(indices.getIndiceColecao())).itens().remove(indices.getIndiceItemColecao());
/*     */       }
/*     */     } 
/*     */     
/* 249 */     fireTableDataChanged();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getColumnCount() {
/* 254 */     return 5;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRowCount() {
/* 259 */     int count = 0;
/*     */     
/* 261 */     for (Colecao<? extends ItemQuadroAuxiliarAb> itens : this.rendIsentos) {
/* 262 */       count += itens.itens().size();
/*     */     }
/* 264 */     return count;
/*     */   }
/*     */ 
/*     */   
/*     */   public Informacao<?> getInformacaoAt(int row, int col) {
/* 269 */     ItemQuadroAuxiliarAb item = (ItemQuadroAuxiliarAb)getObjetoNegocio(row);
/*     */     
/* 271 */     if (item != null) {
/* 272 */       Alfa info; Alfa code; switch (col) {
/*     */         case 0:
/* 274 */           return super.getInformacaoAt(row, col);
/*     */         case 1:
/* 276 */           info = info = new Alfa();
/* 277 */           info.setConteudo(obterTipoRendimento(row).getConteudo(1));
/* 278 */           return (Informacao<?>)info;
/*     */         case 2:
/* 280 */           code = new Alfa();
/* 281 */           code.setConteudo(obterTipoRendimento(row).getConteudo(0));
/* 282 */           if (RendIsentos.TIPO_RENDISENTO_27.equals(code.naoFormatado())) {
/* 283 */             return (Informacao<?>)((ItemQuadroTransporteDetalhado)item).getCpfAlimentante();
/*     */           }
/* 285 */           return (Informacao<?>)item.getNomeFontePagadora();
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 3:
/* 291 */           if (!item.getTipoBeneficiario().naoFormatado().trim().isEmpty() && 
/* 292 */             !item.getTipoBeneficiario().naoFormatado().equals("-1")) {
/* 293 */             StringBuilder conteudo = new StringBuilder("<html>");
/* 294 */             conteudo.append(item.getTipoBeneficiario().getConteudoAtual(1));
/*     */             
/* 296 */             if (item.getTipoBeneficiario().naoFormatado().equals("Dependente") && 
/* 297 */               !item.getCpfBeneficiario().isVazio()) {
/* 298 */               conteudo.append(":<br>");
/* 299 */               conteudo.append(item.getCpfBeneficiario().formatado());
/*     */             } 
/* 301 */             conteudo.append("</html>");
/*     */             
/* 303 */             info = new Alfa();
/* 304 */             info.setConteudo(conteudo.toString());
/* 305 */             return (Informacao<?>)info;
/*     */           } 
/* 307 */           return null;
/*     */         case 4:
/* 309 */           code = new Alfa();
/* 310 */           code.setConteudo(obterTipoRendimento(row).getConteudo(0));
/* 311 */           if (RendIsentos.TIPO_RENDISENTO_10.equals(code.naoFormatado())) {
/* 312 */             return (Informacao<?>)item.getValor().operacao('+', (Valor)item.getValor13Salario());
/*     */           }
/* 314 */           return (Informacao<?>)item.getValorRendimento();
/*     */       } 
/*     */ 
/*     */     
/*     */     } 
/* 319 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getColumnName(int column) {
/* 324 */     switch (column) {
/*     */       case 0:
/* 326 */         return super.getColumnName(column);
/*     */       case 1:
/* 328 */         return "<html><center>Tipo de Rendimento</center></html>";
/*     */       case 2:
/* 330 */         return "<html><center>Fonte Pagadora</center></html>";
/*     */       case 3:
/* 332 */         return "<html><center>Beneficiário</center></html>";
/*     */       case 4:
/* 334 */         return "<html><center>Valor (R$)</center></html>";
/*     */     } 
/* 336 */     return "";
/*     */   }
/*     */   
/*     */   public void removerItem(ItemQuadroAuxiliarAb item, String tipoRendimento) {
/* 340 */     Colecao<? extends ItemQuadroAuxiliarAb> colecao = obterColecaoRendimento(tipoRendimento);
/* 341 */     if (colecao != null) {
/* 342 */       colecao.itens().remove(item);
/*     */     }
/*     */   }
/*     */   
/*     */   public int obterPosicaoCorretaPendencia(ItemQuadroAuxiliarAb item) {
/* 347 */     int count = 0;
/* 348 */     boolean achouPosicao = false;
/* 349 */     for (Colecao<? extends ItemQuadroAuxiliarAb> itens : this.rendIsentos) {
/* 350 */       for (ItemQuadroAuxiliarAb itemAtual : itens.itens()) {
/* 351 */         count++;
/* 352 */         if (item == itemAtual) {
/* 353 */           achouPosicao = true;
/* 354 */           count--;
/*     */           break;
/*     */         } 
/*     */       } 
/* 358 */       if (achouPosicao) {
/*     */         break;
/*     */       }
/*     */     } 
/*     */     
/* 363 */     return count;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\rendIsentos\TableModelRendIsentos.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */