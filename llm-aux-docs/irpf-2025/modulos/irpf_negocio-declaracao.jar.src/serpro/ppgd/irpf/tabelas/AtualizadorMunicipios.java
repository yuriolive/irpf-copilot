/*     */ package serpro.ppgd.irpf.tabelas;
/*     */ 
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonParser;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import serpro.ppgd.irpf.exception.BarramentoException;
/*     */ import serpro.ppgd.irpf.nuvem.BarramentoIRPFService;
/*     */ import serpro.ppgd.irpf.nuvem.RetornoBarramento;
/*     */ import serpro.ppgd.negocio.ElementoTabela;
/*     */ import serpro.ppgd.negocio.util.UtilitariosString;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AtualizadorMunicipios
/*     */ {
/*     */   public static final String NOME_ARQUIVO_JSON_PGD = "municipios-pgd.json";
/*     */   public static final String NOME_ARQUIVO_JSON_TOM = "municipios-tom.json";
/*     */   
/*     */   private List<String> obterLinhasJSonTabelasMunicipiosPGD() {
/*  36 */     List<String> linhasJson = new ArrayList<>();
/*     */ 
/*     */ 
/*     */     
/*  40 */     String[] ufs = { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PR", "PB", "PA", "PE", "PI", "RN", "RS", "RJ", "RO", "RR", "SC", "SE", "SP", "TO" };
/*     */ 
/*     */     
/*  43 */     linhasJson.add("[");
/*  44 */     for (String uf : ufs) {
/*  45 */       List<ElementoTabela> municipios = CadastroTabelasIRPF.recuperarMunicipios(uf, 1);
/*  46 */       linhasJson.add("{");
/*  47 */       linhasJson.add("\"sigla\": \"" + uf + "\",");
/*  48 */       linhasJson.add("\"municipios\": [");
/*  49 */       boolean primeiro = true;
/*  50 */       for (ElementoTabela municipio : municipios) {
/*  51 */         if (!primeiro) {
/*  52 */           linhasJson.add(",");
/*     */         }
/*  54 */         primeiro = false;
/*  55 */         linhasJson.add("{");
/*  56 */         linhasJson.add("\"codigo\": \"" + municipio.getConteudo(0) + "\",");
/*  57 */         linhasJson.add("\"nome\": \"" + UtilitariosString.retiraCaracteresEspeciais(municipio.getConteudo(1)).toUpperCase() + "\"");
/*  58 */         linhasJson.add("}");
/*     */       } 
/*  60 */       linhasJson.add("]");
/*  61 */       linhasJson.add("}");
/*  62 */       if (!"TO".equals(uf)) {
/*  63 */         linhasJson.add(",");
/*     */       }
/*     */     } 
/*  66 */     linhasJson.add("]");
/*  67 */     return linhasJson;
/*     */   }
/*     */   
/*     */   private List<String> indicarDiferencasPGDTOM(Map<String, String> mapA, Map<String, String> mapB, String nomeMapA, String nomeMapB, boolean indicarDiferencaGrafia) {
/*  71 */     List<String> diferencas = new ArrayList<>();
/*  72 */     for (String key : mapA.keySet()) {
/*  73 */       String valorA = mapA.get(key);
/*  74 */       String valorB = mapB.get(key);
/*  75 */       if (valorB == null) {
/*  76 */         diferencas.add("O município " + valorA + " existe em " + nomeMapA + " mas não existe em " + nomeMapB + "."); continue;
/*  77 */       }  if (!valorA.equals(valorB) && indicarDiferencaGrafia) {
/*  78 */         diferencas.add("O município " + valorA + " da " + nomeMapA + " se chama " + valorB + " no " + nomeMapB + ".");
/*     */       }
/*     */     } 
/*  81 */     return diferencas;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void compararTabelaMunicipiosPGDTOM() {
/*  89 */     String filePathMunicipioTOM = "municipios-tom.json";
/*  90 */     String filePathMunicipioPGD = "municipios-pgd.json";
/*     */     
/*  92 */     Map<String, String> mapMunicipiosTOM = new HashMap<>();
/*  93 */     Map<String, String> mapMunicipiosPGD = new HashMap<>();
/*     */     
/*  95 */     JsonElement jsonMunicipioTOM = null;
/*  96 */     JsonElement jsonMunicipioPGD = null; 
/*  97 */     try { FileReader readerMunicipiosTOM = new FileReader(filePathMunicipioTOM); 
/*  98 */       try { FileReader readerMunicipiosPGD = new FileReader(filePathMunicipioPGD);
/*  99 */         jsonMunicipioTOM = (new JsonParser()).parse(readerMunicipiosTOM);
/* 100 */         jsonMunicipioPGD = (new JsonParser()).parse(readerMunicipiosPGD);
/* 101 */         JsonArray arrayMunicipiosTOM = jsonMunicipioTOM.getAsJsonArray();
/* 102 */         JsonArray arrayMunicipiosPGD = jsonMunicipioPGD.getAsJsonArray();
/* 103 */         int qtdeEstados = (arrayMunicipiosTOM.size() > 27) ? 27 : arrayMunicipiosTOM.size();
/* 104 */         for (int i = 0; i < qtdeEstados; i++) {
/* 105 */           JsonElement estadoTOM = arrayMunicipiosTOM.get(i);
/* 106 */           JsonElement estadoPGD = arrayMunicipiosPGD.get(i);
/* 107 */           JsonArray municipiosTOM = estadoTOM.getAsJsonObject().getAsJsonArray("municipios");
/* 108 */           JsonArray municipiosPGD = estadoPGD.getAsJsonObject().getAsJsonArray("municipios");
/* 109 */           mapMunicipiosTOM.clear();
/* 110 */           mapMunicipiosPGD.clear();
/* 111 */           System.out.println("=====================" + estadoTOM.getAsJsonObject().getAsJsonPrimitive("sigla").getAsString() + "=====================");
/* 112 */           if (estadoTOM.getAsJsonObject().getAsJsonPrimitive("sigla").getAsString().toUpperCase().equals("EX")) {
/* 113 */             System.out.println("Pula exterior porque só existe no arquivo da TOM");
/*     */           } else {
/* 115 */             int limite = (municipiosTOM.size() > municipiosPGD.size()) ? municipiosTOM.size() : municipiosPGD.size();
/* 116 */             for (int j = 0; j < limite; j++) {
/* 117 */               if (j < municipiosTOM.size()) {
/* 118 */                 String codigoMunicipioTOM = municipiosTOM.get(j).getAsJsonObject().getAsJsonPrimitive("codigo").getAsString();
/* 119 */                 String nomeMunicipioTOM = municipiosTOM.get(j).getAsJsonObject().getAsJsonPrimitive("nome").getAsString();
/* 120 */                 mapMunicipiosTOM.put(codigoMunicipioTOM, codigoMunicipioTOM + "-" + codigoMunicipioTOM);
/*     */               } 
/* 122 */               if (j < municipiosPGD.size()) {
/* 123 */                 String codigoMunicipioPGD = municipiosPGD.get(j).getAsJsonObject().getAsJsonPrimitive("codigo").getAsString();
/* 124 */                 String nomeMunicipioPGD = municipiosPGD.get(j).getAsJsonObject().getAsJsonPrimitive("nome").getAsString();
/* 125 */                 mapMunicipiosPGD.put(codigoMunicipioPGD, codigoMunicipioPGD + "-" + codigoMunicipioPGD);
/*     */               } 
/*     */             } 
/* 128 */             List<String> diffTOMPGD = indicarDiferencasPGDTOM(mapMunicipiosTOM, mapMunicipiosPGD, "TOM", "PGD", true);
/* 129 */             List<String> diffPGDTOM = indicarDiferencasPGDTOM(mapMunicipiosPGD, mapMunicipiosTOM, "PGD", "TOM", false);
/* 130 */             if (diffTOMPGD.size() == 0 && diffPGDTOM.size() == 0) {
/* 131 */               System.out.println("sem diferenças");
/*     */             } else {
/* 133 */               for (String linha : diffTOMPGD) {
/* 134 */                 System.out.println(linha);
/*     */               }
/* 136 */               for (String linha : diffPGDTOM) {
/* 137 */                 System.out.println(linha);
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/* 142 */         readerMunicipiosTOM.close(); } catch (Throwable throwable) { try { readerMunicipiosTOM.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (IOException e)
/* 143 */     { System.out.println("Erro ao ler o arquivo: " + e.getMessage()); }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   public void criarArquivo(String nomeArquivo) {
/*     */     try {
/* 150 */       (new File(nomeArquivo)).createNewFile();
/* 151 */     } catch (IOException e) {
/* 152 */       System.out.println("Ocorreu um erro ao criar o arquivo.");
/* 153 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void escreverNoArquivo(String nomeArquivo, List<String> conteudo) {
/*     */     
/* 159 */     try { FileWriter writer = new FileWriter(nomeArquivo); 
/* 160 */       try { for (String linha : conteudo) {
/* 161 */           writer.write(linha);
/*     */         }
/* 163 */         writer.close();
/* 164 */         System.out.println("Json criado: " + nomeArquivo);
/* 165 */         writer.close(); } catch (Throwable throwable) { try { writer.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (IOException e)
/* 166 */     { System.out.println("Ocorreu um erro ao escrever no arquivo.");
/* 167 */       e.printStackTrace(); }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   public void deletarArquivo(String nomeArquivo) {
/* 173 */     File arquivo = new File(nomeArquivo);
/* 174 */     arquivo.delete();
/*     */   }
/*     */   
/*     */   private void obterMunicipiosPGD() {
/* 178 */     String nomeArquivo = "municipios-pgd.json";
/* 179 */     deletarArquivo(nomeArquivo);
/* 180 */     criarArquivo(nomeArquivo);
/* 181 */     escreverNoArquivo(nomeArquivo, obterLinhasJSonTabelasMunicipiosPGD());
/*     */   }
/*     */   
/*     */   private void criarArquivoMunicipiosTOM(String json) {
/* 185 */     String nomeArquivo = "municipios-tom.json";
/* 186 */     List<String> municipiosTOM = new ArrayList<>();
/* 187 */     municipiosTOM.add(json);
/* 188 */     deletarArquivo(nomeArquivo);
/* 189 */     criarArquivo(nomeArquivo);
/* 190 */     escreverNoArquivo(nomeArquivo, municipiosTOM);
/*     */   }
/*     */   
/*     */   private boolean obterMunicipiosTOM() {
/* 194 */     boolean retorno = false;
/*     */     try {
/* 196 */       RetornoBarramento retornoBarramento = BarramentoIRPFService.obterTodosMunicipios();
/* 197 */       if ("00".equals(retornoBarramento.getCodigoRetorno())) {
/* 198 */         criarArquivoMunicipiosTOM(retornoBarramento.getConteudo());
/* 199 */         retorno = true;
/*     */       } else {
/* 201 */         System.out.println("Falha na obtenção dos municípios da TOM.");
/*     */       } 
/* 203 */     } catch (BarramentoException|serpro.ppgd.irpf.exception.AplicacaoException ex) {
/* 204 */       System.out.println("Falha na chamada do serviço de obtenção dos municípios da TOM.");
/*     */     } 
/* 206 */     return retorno;
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 210 */     AtualizadorMunicipios atualizadorMunicipios = new AtualizadorMunicipios();
/* 211 */     if (atualizadorMunicipios.obterMunicipiosTOM()) {
/* 212 */       atualizadorMunicipios.obterMunicipiosPGD();
/* 213 */       atualizadorMunicipios.compararTabelaMunicipiosPGDTOM();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\tabelas\AtualizadorMunicipios.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */