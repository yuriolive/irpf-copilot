/*     */ package serpro.ppgd.irpf.gui.rendTributacaoExclusiva;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import serpro.ppgd.irpf.gui.TableListaModel;
/*     */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroAuxiliarAb;
/*     */ import serpro.ppgd.irpf.rendTributacaoExclusiva.RendTributacaoExclusiva;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.Colecao;
/*     */ import serpro.ppgd.negocio.ElementoTabela;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ 
/*     */ public class TableModelRendTributExclusiva
/*     */   extends TableListaModel
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected List<Colecao<? extends ItemQuadroAuxiliarAb>> listaRendTributExclusiva;
/*     */   protected List<ElementoTabela> tiposRendimentos;
/*     */   
/*     */   public TableModelRendTributExclusiva(RendTributacaoExclusiva rendimentos) {
/*  23 */     super((ObjetoNegocio)rendimentos);
/*     */     
/*  25 */     this.tiposRendimentos = CadastroTabelasIRPF.recuperarTiposRendimentosTributacaoExclusiva();
/*  26 */     this.listaRendTributExclusiva = rendimentos.getColecoesRendimentos();
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
/* 143 */     for (Colecao<? extends ItemQuadroAuxiliarAb> colecao : this.listaRendTributExclusiva) {
/* 144 */       colecao.excluirRegistrosEmBranco();
/*     */     }
/*     */   }
/*     */   
/*     */   public ItemQuadroAuxiliarAb novoItemRendimento(String tipo) {
/* 149 */     Colecao<? extends ItemQuadroAuxiliarAb> col = obterColecaoRendimento(tipo);
/*     */     
/* 151 */     return col.itens().get(col.novoObjeto());
/*     */   }
/*     */   
/*     */   protected Colecao<? extends ItemQuadroAuxiliarAb> obterColecaoRendimento(String tipo) {
/* 155 */     for (int i = 0; i < this.tiposRendimentos.size(); i++) {
/* 156 */       if (((ElementoTabela)this.tiposRendimentos.get(i)).getConteudo(0).equals(tipo)) {
/* 157 */         return this.listaRendTributExclusiva.get(i);
/*     */       }
/*     */     } 
/*     */     
/* 161 */     return null;
/*     */   }
/*     */   
/*     */   protected ElementoTabela obterTipoRendimento(int row) {
/* 165 */     ParIndices indices = obterIndicesPorLinha(row);
/*     */     
/* 167 */     return this.tiposRendimentos.get(indices.getIndiceColecao());
/*     */   }
/*     */   
/*     */   protected class ParIndices {
/* 171 */     private int indiceColecao = 0;
/* 172 */     private int indiceItemColecao = 0;
/*     */     
/*     */     public int getIndiceColecao() {
/* 175 */       return this.indiceColecao;
/*     */     }
/*     */     public void setIndiceColecao(int indiceColecao) {
/* 178 */       this.indiceColecao = indiceColecao;
/*     */     }
/*     */     public int getIndiceItemColecao() {
/* 181 */       return this.indiceItemColecao;
/*     */     }
/*     */     public void setIndiceItemColecao(int indiceItemColecao) {
/* 184 */       this.indiceItemColecao = indiceItemColecao;
/*     */     }
/*     */   }
/*     */   
/*     */   protected ParIndices obterIndicesPorLinha(int row) {
/* 189 */     int indiceItemColecao = row;
/*     */     
/* 191 */     ParIndices indices = new ParIndices();
/* 192 */     for (int indiceColecao = 0; indiceColecao < this.listaRendTributExclusiva.size(); indiceColecao++) {
/* 193 */       if (indiceItemColecao < ((Colecao)this.listaRendTributExclusiva.get(indiceColecao)).itens().size()) {
/* 194 */         indices.setIndiceColecao(indiceColecao);
/* 195 */         indices.setIndiceItemColecao(indiceItemColecao);
/*     */         
/* 197 */         return indices;
/*     */       } 
/* 199 */       indiceItemColecao -= ((Colecao)this.listaRendTributExclusiva.get(indiceColecao)).itens().size();
/*     */     } 
/*     */     
/* 202 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ObjetoNegocio getObjetoNegocio(int rowIndex) {
/* 208 */     ParIndices indices = obterIndicesPorLinha(rowIndex);
/*     */     
/* 210 */     if (indices != null) {
/* 211 */       return ((Colecao)this.listaRendTributExclusiva.get(indices.getIndiceColecao())).itens().get(indices.getIndiceItemColecao());
/*     */     }
/*     */     
/* 214 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeObjetoNegocio(Integer[] rowIndexes) {
/* 219 */     Arrays.sort((Object[])rowIndexes);
/*     */     
/* 221 */     for (int i = rowIndexes.length - 1; i >= 0; i--) {
/* 222 */       ParIndices indices = obterIndicesPorLinha(rowIndexes[i].intValue());
/*     */       
/* 224 */       if (indices != null) {
/* 225 */         ((Colecao)this.listaRendTributExclusiva.get(indices.getIndiceColecao())).itens().remove(indices.getIndiceItemColecao());
/*     */       }
/*     */     } 
/*     */     
/* 229 */     fireTableDataChanged();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getColumnCount() {
/* 234 */     return 5;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRowCount() {
/* 239 */     int count = 0;
/*     */     
/* 241 */     for (Colecao<? extends ItemQuadroAuxiliarAb> itens : this.listaRendTributExclusiva) {
/* 242 */       count += itens.itens().size();
/*     */     }
/* 244 */     return count;
/*     */   }
/*     */ 
/*     */   
/*     */   public Informacao<?> getInformacaoAt(int row, int col) {
/* 249 */     ItemQuadroAuxiliarAb item = (ItemQuadroAuxiliarAb)getObjetoNegocio(row);
/*     */     
/* 251 */     if (item != null) {
/* 252 */       Alfa info = null;
/* 253 */       switch (col) {
/*     */         case 0:
/* 255 */           return super.getInformacaoAt(row, col);
/*     */         case 1:
/* 257 */           info = new Alfa();
/* 258 */           info.setConteudo(obterTipoRendimento(row).getConteudo(1));
/* 259 */           return (Informacao<?>)info;
/*     */         case 2:
/* 261 */           return (Informacao<?>)item.getNomeFontePagadora();
/*     */         case 3:
/* 263 */           if (!item.getTipoBeneficiario().naoFormatado().trim().isEmpty() && 
/* 264 */             !item.getTipoBeneficiario().naoFormatado().equals("-1")) {
/* 265 */             StringBuilder conteudo = new StringBuilder("<html>");
/* 266 */             conteudo.append(item.getTipoBeneficiario().getConteudoAtual(1));
/*     */             
/* 268 */             if (item.getTipoBeneficiario().naoFormatado().equals("Dependente") && 
/* 269 */               !item.getCpfBeneficiario().isVazio()) {
/* 270 */               conteudo.append(":<br>");
/* 271 */               conteudo.append(item.getCpfBeneficiario().formatado());
/*     */             } 
/* 273 */             conteudo.append("</html>");
/*     */             
/* 275 */             info = new Alfa();
/* 276 */             info.setConteudo(conteudo.toString());
/* 277 */             return (Informacao<?>)info;
/*     */           } 
/* 279 */           return null;
/*     */         case 4:
/* 281 */           return (Informacao<?>)item.getValorRendimento();
/*     */       } 
/*     */     
/*     */     } 
/* 285 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getColumnName(int column) {
/* 290 */     switch (column) {
/*     */       case 0:
/* 292 */         return super.getColumnName(column);
/*     */       case 1:
/* 294 */         return "<html><center>Tipo de Rendimento</center></html>";
/*     */       case 2:
/* 296 */         return "<html><center>Fonte Pagadora</center></html>";
/*     */       case 3:
/* 298 */         return "<html><center>Beneficiário</center></html>";
/*     */       case 4:
/* 300 */         return "<html><center>Valor (R$)</center></html>";
/*     */     } 
/* 302 */     return "";
/*     */   }
/*     */   
/*     */   public void removerItem(ItemQuadroAuxiliarAb item, String tipoRendimento) {
/* 306 */     obterColecaoRendimento(tipoRendimento).itens().remove(item);
/*     */   }
/*     */   
/*     */   public int obterPosicaoCorretaPendencia(ItemQuadroAuxiliarAb item) {
/* 310 */     int count = 0;
/* 311 */     boolean achouPosicao = false;
/* 312 */     for (Colecao<? extends ItemQuadroAuxiliarAb> itens : this.listaRendTributExclusiva) {
/* 313 */       for (ItemQuadroAuxiliarAb itemAtual : itens.itens()) {
/* 314 */         count++;
/* 315 */         if (item == itemAtual) {
/* 316 */           achouPosicao = true;
/* 317 */           count--;
/*     */           break;
/*     */         } 
/*     */       } 
/* 321 */       if (achouPosicao) {
/*     */         break;
/*     */       }
/*     */     } 
/*     */     
/* 326 */     return count;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\rendTributacaoExclusiva\TableModelRendTributExclusiva.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */