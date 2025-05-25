/*     */ package serpro.ppgd.irpf.txt.importacao.atividaderural;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import serpro.hash.Crc32;
/*     */ import serpro.ppgd.formatosexternos.txt.DocumentoTXT;
/*     */ import serpro.ppgd.formatosexternos.txt.DocumentoTXTDefault;
/*     */ import serpro.ppgd.formatosexternos.txt.RegistroTxt;
/*     */ import serpro.ppgd.formatosexternos.txt.excecao.GeracaoTxtException;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.atividaderural.brasil.ApuracaoResultadoBrasil;
/*     */ import serpro.ppgd.irpf.atividaderural.brasil.MesReceitaDespesa;
/*     */ import serpro.ppgd.irpf.atividaderural.exterior.ApuracaoResultadoExterior;
/*     */ import serpro.ppgd.irpf.atividaderural.exterior.ColecaoReceitasDespesas;
/*     */ import serpro.ppgd.irpf.atividaderural.exterior.ReceitaDespesa;
/*     */ import serpro.ppgd.irpf.espolio.Espolio;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.saida.Saida;
/*     */ import serpro.ppgd.irpf.txt.importacao.RelatorioRepositorioTxtDadosAb;
/*     */ import serpro.ppgd.irpf.txt.importacao.VersaoDecInvalidaException;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.ConstantesGlobais;
/*     */ import serpro.ppgd.negocio.Data;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ import serpro.util.PLong;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RepositorioTxtDadosAtividadeRural
/*     */   extends RelatorioRepositorioTxtDadosAb
/*     */ {
/*  46 */   private String pathArquivo = null;
/*  47 */   private DocumentoTXT documentoTXT = null;
/*  48 */   private Map<String, DadoAtividadeRuralBrasil> mapPaises = new HashMap<>();
/*  49 */   private Map<String, DadoAtividadeRuralExterior> mapPaisesExterior = new HashMap<>();
/*     */   
/*     */   public RepositorioTxtDadosAtividadeRural(String _pathArquivo) {
/*  52 */     this.pathArquivo = _pathArquivo;
/*  53 */     this.documentoTXT = (DocumentoTXT)new DocumentoTXTDefault("ARQ_IMPORTACAO_ATIV_RURAL", this.pathArquivo);
/*  54 */     setContabilizarSucessosErros(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void validarVersaoAtividadeRural() throws Exception {
/*     */     try {
/*  60 */       this.documentoTXT.ler();
/*  61 */       verificaCRC();
/*  62 */     } catch (GeracaoTxtException|IOException gex) {
/*  63 */       throw new GeracaoTxtException("Arquivo corrompido.");
/*     */     } 
/*     */     try {
/*  66 */       List<RegistroTxt> header = this.documentoTXT.getRegistrosTxt("IR");
/*  67 */       String versao = ((RegistroTxt)header.get(0)).fieldByName("NR_VERSAO").asString();
/*     */       
/*  69 */       if (!versao.equals("110")) {
/*  70 */         String exercicio = ConstantesGlobais.EXERCICIO_ANTERIOR;
/*  71 */         throw new VersaoDecInvalidaException(MensagemUtil.getMensagem("versao_arquivo_ar_errado", new String[] { exercicio, exercicio }));
/*     */       } 
/*  73 */     } catch (Exception ex) {
/*  74 */       throw ex;
/*     */     } 
/*     */   }
/*     */   
/*     */   public String importaDados() throws Exception {
/*  79 */     String erro = null;
/*     */     try {
/*  81 */       validarVersaoAtividadeRural();
/*  82 */       importaDadosAR();
/*  83 */       incrementarQuantidadeSucessos();
/*  84 */     } catch (Exception ex) {
/*  85 */       incrementarQuantidadeErros();
/*  86 */       if (!isContabilizarSucessosErros()) {
/*  87 */         throw ex;
/*     */       }
/*  89 */       erro = ex.getMessage();
/*     */     } 
/*     */     
/*  92 */     return erro;
/*     */   }
/*     */   
/*     */   private void importaDadosAR() throws GeracaoTxtException, IOException {
/*  96 */     resetQuantidadeSucessos();
/*  97 */     resetQuantidadeErros();
/*     */ 
/*     */     
/* 100 */     Iterator<RegistroTxt> it = null;
/* 101 */     List<RegistroTxt> registros = this.documentoTXT.getRegistrosTxt("04");
/*     */ 
/*     */ 
/*     */     
/* 105 */     Collections.sort(registros, new Comparator<RegistroTxt>()
/*     */         {
/*     */           
/*     */           public int compare(RegistroTxt registroUm, RegistroTxt registroDois)
/*     */           {
/* 110 */             String paisUm = "";
/* 111 */             String paisDois = "";
/* 112 */             String mesUm = "";
/* 113 */             String mesDois = "";
/*     */             
/*     */             try {
/* 116 */               paisUm = registroUm.fieldByName("CD_PAIS").asString();
/* 117 */               paisDois = registroDois.fieldByName("CD_PAIS").asString();
/* 118 */             } catch (GeracaoTxtException e) {
/* 119 */               e.printStackTrace();
/*     */             } 
/*     */             
/* 122 */             int sComp = paisUm.compareTo(paisDois);
/*     */             
/* 124 */             if (sComp != 0) {
/* 125 */               return sComp;
/*     */             }
/*     */             try {
/* 128 */               mesUm = registroUm.fieldByName("Flag Mes").asString();
/* 129 */               mesDois = registroDois.fieldByName("Flag Mes").asString();
/* 130 */             } catch (GeracaoTxtException e) {
/* 131 */               e.printStackTrace();
/*     */             } 
/* 133 */             return mesUm.compareTo(mesDois);
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */     
/* 139 */     it = registros.iterator();
/*     */     
/* 141 */     while (it.hasNext()) {
/* 142 */       RegistroTxt reg = it.next();
/* 143 */       String cdPais = reg.fieldByName("CD_PAIS").asString();
/* 144 */       Valor receitas = reg.fieldByName("receitas").asValor();
/* 145 */       Valor despesas = reg.fieldByName("despesas").asValor();
/* 146 */       Valor produtosEntregueAdiantamento = reg.fieldByName("ProdutosEntregueAdiantamento").asValor();
/* 147 */       Valor adiantamentoRecebido = reg.fieldByName("AdiantamentoRecebido").asValor();
/*     */       
/* 149 */       if (cdPais.equals("105")) {
/* 150 */         DadoAtividadeRuralBrasil dadoAtividadeRuralBrasil = null;
/* 151 */         if (this.mapPaises.containsKey(cdPais)) {
/* 152 */           dadoAtividadeRuralBrasil = this.mapPaises.get(cdPais);
/*     */         } else {
/* 154 */           dadoAtividadeRuralBrasil = new DadoAtividadeRuralBrasil();
/* 155 */           this.mapPaises.put(cdPais, dadoAtividadeRuralBrasil);
/*     */         } 
/* 157 */         ItemMensalARBrasil itemMensalARBrasil = new ItemMensalARBrasil();
/* 158 */         itemMensalARBrasil.receita.setConteudo(receitas);
/* 159 */         itemMensalARBrasil.despesa.setConteudo(despesas);
/* 160 */         itemMensalARBrasil.produtoEntregueAdiantamento.setConteudo(produtosEntregueAdiantamento);
/* 161 */         itemMensalARBrasil.adiantamentoRecebido.setConteudo(adiantamentoRecebido);
/*     */         
/* 163 */         dadoAtividadeRuralBrasil.add(itemMensalARBrasil);
/* 164 */         dadoAtividadeRuralBrasil.totalAdiantamentoRecebido.append('+', itemMensalARBrasil.adiantamentoRecebido);
/* 165 */         dadoAtividadeRuralBrasil.totalProdutoEntregueAdiantamento.append('+', itemMensalARBrasil.produtoEntregueAdiantamento); continue;
/*     */       } 
/* 167 */       DadoAtividadeRuralExterior dadoAtividadeRuralExterior = null;
/* 168 */       if (this.mapPaisesExterior.containsKey(cdPais)) {
/* 169 */         dadoAtividadeRuralExterior = this.mapPaisesExterior.get(cdPais);
/*     */       } else {
/* 171 */         dadoAtividadeRuralExterior = new DadoAtividadeRuralExterior(cdPais);
/* 172 */         this.mapPaisesExterior.put(cdPais, dadoAtividadeRuralExterior);
/*     */       } 
/*     */       
/* 175 */       dadoAtividadeRuralExterior.totalReceitas.append('+', receitas);
/* 176 */       dadoAtividadeRuralExterior.totalDespesas.append('+', despesas);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 181 */     importaBrasil();
/* 182 */     importaExterior();
/*     */   }
/*     */ 
/*     */   
/*     */   private void importaExterior() {
/* 187 */     Iterator<DadoAtividadeRuralExterior> itPaises = this.mapPaisesExterior.values().iterator();
/* 188 */     ColecaoReceitasDespesas receitasDespesasExterior = IRPFFacade.getInstancia().getAtividadeRural().getExterior().getReceitasDespesas();
/* 189 */     receitasDespesasExterior.clear();
/* 190 */     while (itPaises.hasNext()) {
/* 191 */       DadoAtividadeRuralExterior dadoAtividadeRuralExterior = itPaises.next();
/*     */       
/* 193 */       ReceitaDespesa receita = new ReceitaDespesa();
/* 194 */       receita.getPais().setConteudo(dadoAtividadeRuralExterior.codPais);
/* 195 */       receita.getReceitaBruta().setConteudo(dadoAtividadeRuralExterior.totalReceitas);
/* 196 */       receita.getDespesaCusteio().setConteudo(dadoAtividadeRuralExterior.totalDespesas);
/*     */       
/* 198 */       receitasDespesasExterior.itens().add(receita);
/*     */ 
/*     */       
/* 201 */       ApuracaoResultadoExterior apuracaoResultadoExterior = IRPFFacade.getInstancia().getAtividadeRural().getExterior().getApuracaoResultado();
/* 202 */       apuracaoResultadoExterior.getReceitaRecebidaContaVenda().setConteudo(dadoAtividadeRuralExterior.totalAdiantamentoRecebido);
/* 203 */       apuracaoResultadoExterior.getValorAdiantamento().setConteudo(dadoAtividadeRuralExterior.totalProdutoEntregueAdiantamento);
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean verificaMesSaida(int mes) {
/* 208 */     Saida saida = IRPFFacade.getInstancia().getSaida();
/* 209 */     String mesCondicaoNaoResidente = saida.getDtCondicaoNaoResidente().isVazio() ? "12" : saida.getDtCondicaoNaoResidente().getMes();
/* 210 */     String mesCondicaoResidente = saida.getDtCondicaoResidente().isVazio() ? "1" : saida.getDtCondicaoResidente().getMes();
/* 211 */     if (mes + 1 < Integer.valueOf(mesCondicaoResidente).intValue() || mes + 1 > Integer.valueOf(mesCondicaoNaoResidente).intValue()) {
/* 212 */       return false;
/*     */     }
/* 214 */     return true;
/*     */   }
/*     */   
/*     */   private boolean verificaMesEspolio(int mes) {
/* 218 */     Espolio espolio = IRPFFacade.getInstancia().getEspolio();
/* 219 */     Data dtLimite = espolio.obterDataLimiteParaCalculos();
/*     */     
/* 221 */     if (!dtLimite.isVazio()) {
/* 222 */       String mesDecisaoJudicial = dtLimite.getMes();
/* 223 */       if (!mesDecisaoJudicial.isEmpty() && mes > Integer.valueOf(mesDecisaoJudicial).intValue() - 1) {
/* 224 */         return false;
/*     */       }
/*     */     } 
/* 227 */     return true;
/*     */   }
/*     */   
/*     */   private void importaBrasil() {
/* 231 */     DadoAtividadeRuralBrasil dadoAtividadeRuralBrasil = this.mapPaises.remove("105");
/* 232 */     if (dadoAtividadeRuralBrasil != null) {
/* 233 */       for (int i = 0; i < dadoAtividadeRuralBrasil.size(); i++) {
/* 234 */         if (ControladorGui.getDemonstrativoAberto().getIdentificadorDeclaracao().isEspolio()) {
/* 235 */           if (verificaMesEspolio(i)) {
/* 236 */             preencheItemBrasil(i, dadoAtividadeRuralBrasil);
/*     */           }
/* 238 */         } else if (ControladorGui.getDemonstrativoAberto().getIdentificadorDeclaracao().isSaida()) {
/* 239 */           if (verificaMesSaida(i)) {
/* 240 */             preencheItemBrasil(i, dadoAtividadeRuralBrasil);
/*     */           }
/*     */         } else {
/* 243 */           preencheItemBrasil(i, dadoAtividadeRuralBrasil);
/*     */         } 
/*     */       } 
/* 246 */       ApuracaoResultadoBrasil apuracaoResultadoBrasil = IRPFFacade.getInstancia().getAtividadeRural().getBrasil().getApuracaoResultado();
/* 247 */       apuracaoResultadoBrasil.getReceitaRecebidaContaVenda().setConteudo(dadoAtividadeRuralBrasil.totalAdiantamentoRecebido);
/* 248 */       apuracaoResultadoBrasil.getValorAdiantamento().setConteudo(dadoAtividadeRuralBrasil.totalProdutoEntregueAdiantamento);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void preencheItemBrasil(int i, DadoAtividadeRuralBrasil dadoAtividadeRuralBrasil) {
/* 253 */     ItemMensalARBrasil itemMensalARBrasil = dadoAtividadeRuralBrasil.get(i);
/* 254 */     MesReceitaDespesa mesReceitaDespesa = new MesReceitaDespesa();
/* 255 */     switch (i) {
/*     */       case 0:
/* 257 */         mesReceitaDespesa = IRPFFacade.getInstancia().getAtividadeRural().getBrasil().getReceitasDespesas().getJaneiro();
/*     */         break;
/*     */       case 1:
/* 260 */         mesReceitaDespesa = IRPFFacade.getInstancia().getAtividadeRural().getBrasil().getReceitasDespesas().getFevereiro();
/*     */         break;
/*     */       case 2:
/* 263 */         mesReceitaDespesa = IRPFFacade.getInstancia().getAtividadeRural().getBrasil().getReceitasDespesas().getMarco();
/*     */         break;
/*     */       case 3:
/* 266 */         mesReceitaDespesa = IRPFFacade.getInstancia().getAtividadeRural().getBrasil().getReceitasDespesas().getAbril();
/*     */         break;
/*     */       case 4:
/* 269 */         mesReceitaDespesa = IRPFFacade.getInstancia().getAtividadeRural().getBrasil().getReceitasDespesas().getMaio();
/*     */         break;
/*     */       case 5:
/* 272 */         mesReceitaDespesa = IRPFFacade.getInstancia().getAtividadeRural().getBrasil().getReceitasDespesas().getJunho();
/*     */         break;
/*     */       case 6:
/* 275 */         mesReceitaDespesa = IRPFFacade.getInstancia().getAtividadeRural().getBrasil().getReceitasDespesas().getJulho();
/*     */         break;
/*     */       case 7:
/* 278 */         mesReceitaDespesa = IRPFFacade.getInstancia().getAtividadeRural().getBrasil().getReceitasDespesas().getAgosto();
/*     */         break;
/*     */       case 8:
/* 281 */         mesReceitaDespesa = IRPFFacade.getInstancia().getAtividadeRural().getBrasil().getReceitasDespesas().getSetembro();
/*     */         break;
/*     */       case 9:
/* 284 */         mesReceitaDespesa = IRPFFacade.getInstancia().getAtividadeRural().getBrasil().getReceitasDespesas().getOutubro();
/*     */         break;
/*     */       case 10:
/* 287 */         mesReceitaDespesa = IRPFFacade.getInstancia().getAtividadeRural().getBrasil().getReceitasDespesas().getNovembro();
/*     */         break;
/*     */       case 11:
/* 290 */         mesReceitaDespesa = IRPFFacade.getInstancia().getAtividadeRural().getBrasil().getReceitasDespesas().getDezembro();
/*     */         break;
/*     */     } 
/*     */     
/* 294 */     mesReceitaDespesa.getReceitaBrutaMensal().setConteudo(itemMensalARBrasil.receita);
/* 295 */     mesReceitaDespesa.getDespesaCusteioInvestimento().setConteudo(itemMensalARBrasil.despesa);
/*     */   }
/*     */   public void verificaCRC() throws GeracaoTxtException {
/*     */     String crcLido;
/* 299 */     PLong pLongAcumulado = new PLong();
/* 300 */     Crc32 crc32Acumulado = new Crc32();
/* 301 */     long hashCalculadoLinhaAnterior = 0L;
/*     */     
/* 303 */     for (int i = 0; i < this.documentoTXT.arquivo().size() - 1; i++) {
/* 304 */       String linha = this.documentoTXT.arquivo().get(i);
/* 305 */       if (hashCalculadoLinhaAnterior != 0L) {
/* 306 */         pLongAcumulado.setValue(hashCalculadoLinhaAnterior);
/*     */       }
/* 308 */       hashCalculadoLinhaAnterior = crc32Acumulado.CalcCrc32(linha, linha.length(), pLongAcumulado);
/*     */     } 
/*     */     
/* 311 */     String crcAcumuladoFinal = String.valueOf(hashCalculadoLinhaAnterior);
/*     */ 
/*     */     
/*     */     try {
/* 315 */       RegistroTxt reg = this.documentoTXT.getRegistrosTxt("99").get(0);
/* 316 */       crcLido = reg.fieldByName("HASHCODE").asString();
/* 317 */     } catch (IndexOutOfBoundsException ex) {
/* 318 */       throw new GeracaoTxtException("Arquivo corrompido.");
/*     */     } 
/*     */     
/* 321 */     if (!crcLido.equals(crcAcumuladoFinal))
/* 322 */       throw new GeracaoTxtException("Arquivo corrompido"); 
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_importacao-exportacao.jar!\serpro\ppgd\irpf\txt\importacao\atividaderural\RepositorioTxtDadosAtividadeRural.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */