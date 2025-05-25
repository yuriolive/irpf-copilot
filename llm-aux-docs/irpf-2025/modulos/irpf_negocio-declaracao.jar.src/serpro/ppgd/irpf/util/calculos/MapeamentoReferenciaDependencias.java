/*      */ package serpro.ppgd.irpf.util.calculos;
/*      */ 
/*      */ import java.lang.ref.WeakReference;
/*      */ import java.lang.reflect.Array;
/*      */ import java.lang.reflect.Field;
/*      */ import java.lang.reflect.Method;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.LinkedHashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Objects;
/*      */ import java.util.WeakHashMap;
/*      */ import serpro.ppgd.negocio.Colecao;
/*      */ import serpro.ppgd.negocio.Observador;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class MapeamentoReferenciaDependencias
/*      */ {
/*   24 */   final Map<String, ArrayList<String>> dependenciasMap = new HashMap<>();
/*   25 */   final Map<String, ArrayList<String>> dependentesMap = new HashMap<>();
/*   26 */   final Map<Object, FichaInfo> fichas = new WeakHashMap<>();
/*      */   
/*      */   protected ArrayList<IdentificadorInformacao> dependenciasAlteradas;
/*      */ 
/*      */   
/*      */   public class FichaInfo
/*      */   {
/*      */     private final String idFicha;
/*      */     
/*      */     private final WeakReference<Object> ficha;
/*      */     private final WeakReference<Object> pai;
/*      */     private final Map<String, List<WeakReference<?>>> filhas;
/*      */     
/*      */     public FichaInfo(String idFicha, Object ficha, Object pai, Map<String, List<?>> filhas) {
/*   40 */       Objects.requireNonNull(filhas);
/*      */       
/*   42 */       Map<String, List<WeakReference<?>>> mapFilhas = new HashMap<>(filhas.size());
/*   43 */       for (String filha : filhas.keySet()) {
/*   44 */         List<WeakReference<?>> novaLista = new ArrayList<>();
/*   45 */         for (Object objFilha : filhas.get(filha)) {
/*   46 */           WeakReference<?> w = new WeakReference(objFilha);
/*   47 */           novaLista.add(w);
/*      */         } 
/*   49 */         mapFilhas.put(filha, novaLista);
/*      */       } 
/*      */       
/*   52 */       this.idFicha = Objects.<String>requireNonNull(idFicha);
/*   53 */       this.ficha = new WeakReference(Objects.requireNonNull(ficha));
/*   54 */       this.pai = new WeakReference(pai);
/*   55 */       this.filhas = mapFilhas;
/*      */     }
/*      */     
/*      */     public String getIdFicha() {
/*   59 */       return this.idFicha;
/*      */     }
/*      */     
/*      */     public Object getFicha() {
/*   63 */       return this.ficha.get();
/*      */     }
/*      */     
/*      */     public Object getPai() {
/*   67 */       return this.pai.get();
/*      */     }
/*      */     
/*      */     public void adicionarFilha(String nomeFilha, Object filha) {
/*   71 */       List<WeakReference<?>> l = this.filhas.get(nomeFilha);
/*   72 */       if (l == null) {
/*   73 */         l = new ArrayList<>();
/*   74 */         this.filhas.put(nomeFilha, l);
/*      */       } 
/*   76 */       l.add(new WeakReference(filha));
/*      */     }
/*      */     
/*      */     public void removerFilha(String nomeFicha, Object item) {
/*   80 */       List<WeakReference<?>> lista = this.filhas.get(nomeFicha);
/*      */       
/*   82 */       for (int i = 0; i < lista.size(); i++) {
/*   83 */         Object obj = ((WeakReference)lista.get(i)).get();
/*   84 */         if (obj != null && obj == item) {
/*   85 */           lista.remove(i);
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     public List<?> getFilha(String nomeFicha) {
/*   92 */       List<Object> list = new ArrayList();
/*   93 */       List<WeakReference<?>> listRemove = new ArrayList<>();
/*   94 */       for (WeakReference<?> wFilha : this.filhas.get(nomeFicha)) {
/*   95 */         if (wFilha.get() != null) {
/*   96 */           list.add(wFilha.get()); continue;
/*      */         } 
/*   98 */         listRemove.add(wFilha);
/*      */       } 
/*      */       
/*  101 */       ((List)this.filhas.get(nomeFicha)).removeAll(listRemove);
/*      */       
/*  103 */       return list;
/*      */     }
/*      */     
/*      */     public Map<String, List<?>> getFilhas() {
/*  107 */       Map<String, List<?>> map = new HashMap<>();
/*  108 */       for (String filha : this.filhas.keySet()) {
/*  109 */         List<Object> list = new ArrayList();
/*  110 */         List<WeakReference<?>> listRemove = new ArrayList<>();
/*  111 */         for (WeakReference<?> wFilha : this.filhas.get(filha)) {
/*  112 */           if (wFilha.get() != null) {
/*  113 */             list.add(wFilha.get()); continue;
/*      */           } 
/*  115 */           listRemove.add(wFilha);
/*      */         } 
/*      */         
/*  118 */         ((List)this.filhas.get(filha)).removeAll(listRemove);
/*      */         
/*  120 */         map.put(filha, list);
/*      */       } 
/*      */       
/*  123 */       return map;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public List<?> obterFichaPorReferencia(Object ficha, String referencia) throws MapeamentoDependenciasException {
/*  129 */     List<Object> fichas = new ArrayList();
/*  130 */     Object fichaAtual = ficha;
/*      */     
/*  132 */     int i = 0;
/*  133 */     while (fichaAtual != null && i < referencia
/*  134 */       .length() && referencia
/*  135 */       .startsWith("..", i)) {
/*  136 */       FichaInfo fichaInfo = this.fichas.get(fichaAtual);
/*  137 */       if (fichaInfo == null) {
/*  138 */         throw new MapeamentoDependenciasException("Ficha não mapeada: " + fichaAtual.getClass().getName());
/*      */       }
/*  140 */       fichaAtual = fichaInfo.getPai();
/*  141 */       i++;
/*      */     } 
/*      */     
/*  144 */     fichas.add(fichaAtual);
/*      */     
/*  146 */     for (String nomeFicha : referencia.substring(i).split("\\.")) {
/*  147 */       if (!nomeFicha.isEmpty()) {
/*  148 */         ArrayList<Object> lista = new ArrayList();
/*  149 */         for (Object fichaObj : fichas) {
/*  150 */           FichaInfo fichaInfo = this.fichas.get(fichaObj);
/*  151 */           if (fichaInfo == null) {
/*  152 */             throw new MapeamentoDependenciasException("Ficha não mapeada: " + fichaObj.getClass().getName());
/*      */           }
/*  154 */           List<?> filha = fichaInfo.getFilha(nomeFicha);
/*  155 */           if (filha == null) {
/*  156 */             throw new MapeamentoDependenciasException("Propriedade \"" + nomeFicha + "\" não mapeada para a Ficha " + fichaInfo.getIdFicha());
/*      */           }
/*  158 */           lista.addAll(filha);
/*      */         } 
/*      */         
/*  161 */         fichas = lista;
/*      */       } 
/*      */     } 
/*      */     
/*  165 */     return fichas;
/*      */   }
/*      */   
/*      */   public List<?> obterInformacaoPorReferencia(Object fichaInicial, String referencia) throws MapeamentoDependenciasException {
/*  169 */     int lastIndex = referencia.lastIndexOf(".") + 1;
/*  170 */     String refFicha = referencia.substring(0, lastIndex);
/*      */     
/*  172 */     List<Object> listaInfo = new ArrayList();
/*  173 */     List<?> listaInstanciasFicha = obterFichaPorReferencia(fichaInicial, refFicha);
/*      */     
/*  175 */     String campo = referencia.substring(lastIndex);
/*  176 */     for (Object ficha : listaInstanciasFicha) {
/*  177 */       Field field = getField(ficha.getClass(), campo);
/*  178 */       if (field != null) {
/*      */         try {
/*  180 */           boolean access = field.isAccessible();
/*  181 */           field.setAccessible(true);
/*      */           try {
/*  183 */             listaInfo.add(field.get(ficha));
/*      */           } finally {
/*  185 */             field.setAccessible(access);
/*      */           } 
/*  187 */         } catch (Exception e) {
/*  188 */           throw new MapeamentoDependenciasException("Ocorreu um erro ao acessar Informacao", e);
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/*  193 */     return listaInfo;
/*      */   }
/*      */   
/*      */   public boolean mapearInstanciasFichas(Object ficha, Object fichaPai, String prefixo) throws MapeamentoDependenciasException {
/*  197 */     Class<?> classFicha = ficha.getClass();
/*  198 */     boolean ehFicha = classFicha.isAnnotationPresent((Class)FichaInformacao.class);
/*      */ 
/*      */     
/*  201 */     if (ehFicha) {
/*  202 */       HashMap<String, List<?>> filhas = new HashMap<>();
/*  203 */       String idFicha = prefixo.replaceAll("\\[N?\\]", "");
/*      */       
/*  205 */       Field[] fields = getFields(classFicha);
/*  206 */       for (Field field : fields) {
/*  207 */         if (!field.getName().startsWith("this$")) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  212 */           String nomeCampo = prefixo + "." + prefixo;
/*      */           
/*  214 */           if (field.isAnnotationPresent((Class)CampoInformacao.class)) {
/*      */             
/*  216 */             String idInformacao = idFicha + "." + idFicha;
/*      */             
/*  218 */             if (this.dependenciasMap.containsKey(idInformacao) && 
/*  219 */               !this.dependentesMap.containsKey(idInformacao)) {
/*  220 */               ObservadorDependenciaInformacao obs = new ObservadorDependenciaInformacao(ficha, field.getName());
/*      */               
/*      */               try {
/*  223 */                 boolean accessible = field.isAccessible();
/*  224 */                 field.setAccessible(true);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*      */               }
/*  233 */               catch (Exception e) {
/*  234 */                 throw new MapeamentoDependenciasException("Ocorreu um erro ao acessar Informacao (" + nomeCampo + ")", e);
/*      */               
/*      */               }
/*      */ 
/*      */             
/*      */             }
/*      */           
/*      */           }
/*  242 */           else if (field.getType().isAnnotationPresent((Class)FichaInformacao.class)) {
/*      */             try {
/*      */               Object filha;
/*  245 */               boolean accessible = field.isAccessible();
/*  246 */               field.setAccessible(true);
/*      */               
/*      */               try {
/*  249 */                 filha = field.get(ficha);
/*      */               } finally {
/*  251 */                 field.setAccessible(accessible);
/*      */               } 
/*  253 */               if (mapearInstanciasFichas(filha, ficha, nomeCampo)) {
/*  254 */                 ArrayList<Object> listInstanciasFilha = new ArrayList();
/*  255 */                 listInstanciasFilha.add(filha);
/*  256 */                 filhas.put(field.getName(), listInstanciasFilha);
/*      */               } 
/*  258 */             } catch (Exception e) {
/*  259 */               throw new MapeamentoDependenciasException("Ocorreu um erro ao acessar Ficha (" + nomeCampo + ")", e);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*  264 */       ListaFichaInformacao listaFichaInfo = classFicha.<ListaFichaInformacao>getAnnotation(ListaFichaInformacao.class);
/*  265 */       if (listaFichaInfo != null && listaFichaInfo
/*  266 */         .classeFicha().isAnnotationPresent((Class)FichaInformacao.class)) {
/*  267 */         String nomeCampo = prefixo + "._itens";
/*      */         
/*  269 */         ObservadorLista obs = new ObservadorLista(ficha, nomeCampo);
/*      */         
/*  271 */         ((Colecao)ficha).addObservador(obs);
/*      */         
/*  273 */         String nomeLista = listaFichaInfo.lista();
/*  274 */         List<?> listInstanciasFilha = obterListaFichas(ficha, nomeLista);
/*      */         
/*  276 */         for (Object filha : listInstanciasFilha) {
/*  277 */           mapearInstanciasFichas(filha, ficha, nomeCampo);
/*      */         }
/*  279 */         filhas.put("_itens", listInstanciasFilha);
/*      */       } 
/*      */       
/*  282 */       FichaInfo fichaInfo = new FichaInfo(idFicha, ficha, fichaPai, filhas);
/*      */ 
/*      */       
/*  285 */       this.fichas.put(ficha, fichaInfo);
/*      */       
/*  287 */       if (idFicha.endsWith("_itens")) {
/*  288 */         fichaInfo = this.fichas.get(fichaPai);
/*  289 */         if (fichaInfo != null) {
/*  290 */           fichaInfo.adicionarFilha("_itens", ficha);
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  296 */     return ehFicha;
/*      */   }
/*      */   public List<?> obterListaFichas(Object ficha, String nomeLista) throws MapeamentoDependenciasException {
/*      */     List<?> listaItens;
/*  300 */     Class<?> classFicha = ficha.getClass();
/*      */     
/*      */     try {
/*  303 */       if (!nomeLista.isEmpty()) {
/*      */         
/*  305 */         Field field = getField(classFicha, nomeLista);
/*  306 */         if (field != null) {
/*  307 */           boolean accessible = field.isAccessible();
/*  308 */           field.setAccessible(true);
/*      */           try {
/*  310 */             listaItens = (List)field.get(ficha);
/*      */           } finally {
/*  312 */             field.setAccessible(accessible);
/*      */           } 
/*      */         } else {
/*      */           
/*  316 */           Method m = classFicha.getMethod(nomeLista, new Class[0]);
/*  317 */           listaItens = (List)m.invoke(ficha, new Object[0]);
/*      */         } 
/*      */       } else {
/*  320 */         listaItens = (List)ficha;
/*      */       } 
/*  322 */     } catch (Exception e) {
/*  323 */       throw new MapeamentoDependenciasException("Ocorreu um erro ao acessar lista de Fichas (" + classFicha.getName() + "." + nomeLista + ")", e);
/*      */     } 
/*      */     
/*  326 */     return listaItens;
/*      */   }
/*      */   
/*      */   public class ObservadorLista
/*      */     extends Observador {
/*      */     Object ficha;
/*      */     String prefixo;
/*      */     
/*      */     public ObservadorLista(Object ficha, String prefixo) {
/*  335 */       this.ficha = ficha;
/*  336 */       this.prefixo = prefixo;
/*      */     }
/*      */ 
/*      */     
/*      */     public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/*      */       try {
/*  342 */         if ("ObjetoInserido".equals(nomePropriedade)) {
/*  343 */           MapeamentoReferenciaDependencias.this.mapearInstanciasFichas(valorNovo, this.ficha, this.prefixo);
/*      */           
/*  345 */           MapeamentoReferenciaDependencias.this.informarAlteracao(this.ficha, "_quantidadeItensLista");
/*  346 */           MapeamentoReferenciaDependencias.this.executarAtualizacoes();
/*  347 */         } else if ("ObjetoRemovido".equals(nomePropriedade)) {
/*  348 */           ((MapeamentoReferenciaDependencias.FichaInfo)MapeamentoReferenciaDependencias.this.fichas.get(observado)).removerFilha("_itens", valorNovo);
/*      */           
/*  350 */           MapeamentoReferenciaDependencias.this.informarAlteracao(this.ficha, "_quantidadeItensLista");
/*  351 */           MapeamentoReferenciaDependencias.this.executarAtualizacoes();
/*      */         } 
/*  353 */       } catch (MapeamentoDependenciasException e) {
/*  354 */         e.printStackTrace();
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @FichaInformacao
/*      */   public class FichaDeclaracao
/*      */   {
/*  374 */     MapeamentoReferenciaDependencias.ListaRendPJ listaRendPJ = new MapeamentoReferenciaDependencias.ListaRendPJ();
/*      */     
/*  376 */     MapeamentoReferenciaDependencias.FichaClaculo fichaCalculo = new MapeamentoReferenciaDependencias.FichaClaculo();
/*      */     @CampoInformacao
/*  378 */     private String campoA = "FichaDeclaracao::campoA";
/*      */ 
/*      */ 
/*      */     
/*      */     public FichaDeclaracao() {
/*  383 */       this.listaRendPJ.add(new MapeamentoReferenciaDependencias.FichaDetalheRendPJ());
/*  384 */       this.listaRendPJ.add(new MapeamentoReferenciaDependencias.FichaDetalheRendPJ());
/*  385 */       this.listaRendPJ.add(new MapeamentoReferenciaDependencias.FichaDetalheRendPJ());
/*      */     } }
/*      */   
/*      */   @FichaInformacao
/*      */   public class FichaDetalheRend {
/*      */     @CampoInformacao
/*  391 */     private String campoA = "FichaDetalheRend:campoA";
/*      */     
/*      */     @CampoInformacao
/*  394 */     private String campoB = "FichaDetalheRend:campoB";
/*      */     
/*      */     @CampoInformacao(dependencias = {".campoA", ".campoB"})
/*  397 */     private String campoC = "FichaDetalheRend:campoC";
/*      */     
/*      */     @CampoInformacao(dependencias = {".....fichaCalculo.campoA"})
/*  400 */     private String campoD = "FichaDetalheRend:campoD";
/*      */     
/*      */     @CampoInformacao(dependencias = {".._itens[].campoA"})
/*  403 */     private String campoE = "FichaDetalheRend:campoE";
/*      */   }
/*      */   
/*      */   @FichaInformacao
/*      */   @ListaFichaInformacao(classeFicha = FichaDetalheRend.class)
/*      */   public class ListaRend
/*      */     extends ArrayList<FichaDetalheRend> {
/*      */     private static final long serialVersionUID = 1L;
/*      */     @CampoInformacao(dependencias = {"._itens[].campoC"})
/*  412 */     private String campoA = "ListaRend:campoA";
/*      */   }
/*      */   
/*      */   @FichaInformacao
/*      */   public class FichaDetalheRendPJ
/*      */   {
/*      */     @CampoInformacao
/*  419 */     private String campoA = "FichaDetalheRendPJ:campoA";
/*      */     
/*      */     @CampoInformacao
/*  422 */     private String campoB = "FichaDetalheRendPJ:campoB";
/*      */     
/*      */     @CampoInformacao(dependencias = {".campoA", ".campoB"})
/*  425 */     private String campoC = "FichaDetalheRendPJ:campoC";
/*      */ 
/*      */ 
/*      */     
/*  429 */     private String campoD = "FichaDetalheRendPJ:campoD";
/*      */     @CampoInformacao(dependencias = {"..campoA"})
/*  431 */     private String campoE = "FichaDetalheRendPJ:campoE";
/*      */ 
/*      */     
/*  434 */     private MapeamentoReferenciaDependencias.ListaRend listaRend = new MapeamentoReferenciaDependencias.ListaRend();
/*      */     
/*      */     public FichaDetalheRendPJ() {
/*  437 */       this.listaRend.add(new MapeamentoReferenciaDependencias.FichaDetalheRend());
/*  438 */       this.listaRend.add(new MapeamentoReferenciaDependencias.FichaDetalheRend());
/*  439 */       this.listaRend.add(new MapeamentoReferenciaDependencias.FichaDetalheRend());
/*  440 */       this.listaRend.add(new MapeamentoReferenciaDependencias.FichaDetalheRend());
/*      */     }
/*      */   }
/*      */   
/*      */   @FichaInformacao
/*      */   @ListaFichaInformacao(classeFicha = FichaDetalheRendPJ.class)
/*      */   public class ListaRendPJ extends ArrayList<FichaDetalheRendPJ> {
/*      */     private static final long serialVersionUID = 1L;
/*      */     @CampoInformacao(dependencias = {"._itens[].campoC"}, classeAtualizacao = MapeamentoReferenciaDependencias.class, metodoAtualizacao = "calculaCampoA")
/*  449 */     private String campoA = "ListaRendPJ:campoA";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @FichaInformacao
/*      */   public class FichaClaculo
/*      */   {
/*      */     @CampoInformacao
/*  460 */     private String campoA = "FichaClaculo:campoA";
/*      */     
/*      */     @CampoInformacao
/*  463 */     private String campoB = "FichaClaculo:campoB";
/*      */     
/*      */     @CampoInformacao
/*  466 */     private String campoC = "FichaClaculo:campoC";
/*      */     
/*      */     @CampoInformacao(dependencias = {".campoA", ".campoB"})
/*  469 */     private String campoD = "FichaClaculo:campoD";
/*      */     
/*      */     @CampoInformacao(dependencias = {".campoB", ".campoC"})
/*  472 */     private String campoE = "FichaClaculo:campoE";
/*      */     
/*      */     @CampoInformacao(dependencias = {".campoD", ".campoE"})
/*  475 */     private String campoF = "FichaClaculo:campoF";
/*      */     
/*      */     @CampoInformacao(dependencias = {"..campoA"})
/*  478 */     private String campoG = "FichaClaculo:campoG";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Field[] getFields(Class<?> c) {
/*  486 */     Field[] thisFields = c.getDeclaredFields();
/*      */ 
/*      */     
/*  489 */     if (c.getSuperclass() != null) {
/*      */ 
/*      */ 
/*      */       
/*  493 */       Field[] superClassFields = getFields(c.getSuperclass());
/*      */ 
/*      */       
/*  496 */       Field[] allFields = new Field[superClassFields.length + thisFields.length];
/*      */ 
/*      */       
/*  499 */       System.arraycopy(superClassFields, 0, allFields, 0, superClassFields.length);
/*      */ 
/*      */       
/*  502 */       System.arraycopy(thisFields, 0, allFields, superClassFields.length, thisFields.length);
/*      */ 
/*      */       
/*  505 */       return allFields;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  510 */     return thisFields;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Field getField(Class<?> c, String name) {
/*      */     try {
/*  517 */       return c.getDeclaredField(name);
/*  518 */     } catch (Exception e) {
/*      */       
/*  520 */       if (c.getSuperclass() != null)
/*      */       {
/*      */         
/*  523 */         return getField(c.getSuperclass(), name);
/*      */       }
/*      */ 
/*      */       
/*  527 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public class MapeamentoDependenciasException extends Exception { private static final long serialVersionUID = 1L;
/*      */     
/*      */     public MapeamentoDependenciasException(String arg0, Throwable arg1) {
/*  534 */       super(arg0, arg1);
/*      */     }
/*      */     
/*      */     public MapeamentoDependenciasException(String arg0) {
/*  538 */       super(arg0);
/*      */     } }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String nomeInfoPelaReferencia(String nomeCampo, String referencia) throws MapeamentoDependenciasException {
/*  545 */     String nomeInfo = "";
/*      */     
/*  547 */     int lastIndex = nomeCampo.length();
/*  548 */     int i = 0;
/*  549 */     for (; i < referencia.length() && referencia.startsWith(".", i); 
/*  550 */       i++) {
/*  551 */       lastIndex = nomeCampo.lastIndexOf(".", lastIndex);
/*  552 */       if (lastIndex >= 0) {
/*  553 */         nomeInfo = nomeCampo.substring(0, lastIndex) + nomeCampo.substring(0, lastIndex);
/*  554 */         lastIndex--;
/*      */       } else {
/*  556 */         throw new MapeamentoDependenciasException("Identificacao de campo Informacao inconsistente: \"" + referencia + "\"");
/*      */       } 
/*      */     } 
/*      */     
/*  560 */     return nomeInfo;
/*      */   }
/*      */   public String referenciaPeloNomeInfo(String nomeCampo, String nomeInfo) throws MapeamentoDependenciasException {
/*  563 */     StringBuilder sbDependente = new StringBuilder();
/*  564 */     int lastIndex = nomeInfo.length();
/*      */     
/*  566 */     while ((lastIndex = nomeInfo.lastIndexOf(".", lastIndex)) >= 0) {
/*  567 */       String prefix = nomeInfo.substring(0, lastIndex);
/*  568 */       if (nomeCampo.startsWith(prefix)) {
/*      */         break;
/*      */       }
/*  571 */       sbDependente.append(".");
/*  572 */       lastIndex--;
/*      */     } 
/*  574 */     sbDependente.append(nomeCampo.substring(lastIndex));
/*      */     
/*  576 */     return sbDependente.toString();
/*      */   }
/*      */   
/*      */   public void mapearInformacoes(Class<?> classFicha, String prefixo) throws MapeamentoDependenciasException {
/*  580 */     String nomeCampo = null;
/*      */     
/*  582 */     FichaInformacao fichaInfo = classFicha.<FichaInformacao>getAnnotation(FichaInformacao.class);
/*      */     
/*  584 */     if (fichaInfo != null) {
/*      */       
/*  586 */       Field[] fields = getFields(classFicha);
/*  587 */       for (Field field : fields) {
/*  588 */         if (!field.getName().startsWith("this$")) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  593 */           nomeCampo = prefixo + "." + prefixo;
/*      */           
/*  595 */           CampoInformacao campoInfo = field.<CampoInformacao>getAnnotation(CampoInformacao.class);
/*  596 */           if (campoInfo != null) {
/*      */             
/*  598 */             String[] dependencias = campoInfo.dependencias();
/*  599 */             if (dependencias.length > 0) {
/*  600 */               System.out.println("\nCampo Dependente: " + nomeCampo);
/*      */               
/*  602 */               for (String dependencia : dependencias) {
/*      */                 
/*  604 */                 String nomeDependencia = nomeInfoPelaReferencia(nomeCampo, dependencia);
/*  605 */                 System.out.println("\t" + nomeDependencia);
/*      */ 
/*      */                 
/*  608 */                 String idDependente = nomeCampo.replaceAll("\\[N?\\]", "");
/*  609 */                 ArrayList<String> listDependencias = this.dependentesMap.get(idDependente);
/*  610 */                 if (listDependencias == null) {
/*  611 */                   listDependencias = new ArrayList<>();
/*  612 */                   this.dependentesMap.put(idDependente, listDependencias);
/*      */                 } 
/*      */                 
/*  615 */                 listDependencias.add(dependencia.replaceAll("\\[N?\\]", ""));
/*      */ 
/*      */                 
/*  618 */                 String idDependencia = nomeDependencia.replaceAll("\\[N?\\]", "");
/*  619 */                 ArrayList<String> listDependentes = this.dependenciasMap.get(idDependencia);
/*  620 */                 if (listDependentes == null) {
/*  621 */                   listDependentes = new ArrayList<>();
/*  622 */                   this.dependenciasMap.put(idDependencia, listDependentes);
/*      */                 } 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*  628 */                 String dependente = referenciaPeloNomeInfo(nomeCampo, nomeDependencia);
/*  629 */                 dependente = dependente.replaceAll("\\[N?\\]", "");
/*  630 */                 listDependentes.add(dependente);
/*      */               } 
/*      */             } 
/*      */           } else {
/*  634 */             mapearInformacoes(field.getType(), nomeCampo);
/*      */           } 
/*      */         } 
/*      */       } 
/*  638 */       ListaFichaInformacao listaFicha = classFicha.<ListaFichaInformacao>getAnnotation(ListaFichaInformacao.class);
/*  639 */       if (listaFicha != null) {
/*  640 */         nomeCampo = prefixo + "._itens[N]";
/*      */         
/*  642 */         mapearInformacoes(listaFicha.classeFicha(), nomeCampo);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public class IdentificadorInformacao
/*      */   {
/*      */     private String hashCodeInformacao;
/*      */     private String nomeCampoInformacao;
/*      */     private Object ficha;
/*      */     
/*      */     public IdentificadorInformacao(String nomeCampoInformacao, Object ficha) {
/*  654 */       this.nomeCampoInformacao = Objects.<String>requireNonNull(nomeCampoInformacao);
/*  655 */       this.ficha = Objects.requireNonNull(ficha);
/*  656 */       this.hashCodeInformacao = "" + ficha.hashCode() + "." + ficha.hashCode();
/*      */     }
/*      */     
/*      */     public String getHashCodeInformacao() {
/*  660 */       return this.hashCodeInformacao;
/*      */     }
/*      */     
/*      */     public String getNomeCampoInformacao() {
/*  664 */       return this.nomeCampoInformacao;
/*      */     }
/*      */     
/*      */     public Object getFicha() {
/*  668 */       return this.ficha;
/*      */     }
/*      */     
/*      */     public String getIdInformacao() throws MapeamentoReferenciaDependencias.MapeamentoDependenciasException {
/*  672 */       MapeamentoReferenciaDependencias.FichaInfo fichaInfo = MapeamentoReferenciaDependencias.this.fichas.get(getFicha());
/*  673 */       if (fichaInfo == null) {
/*  674 */         throw new MapeamentoReferenciaDependencias.MapeamentoDependenciasException("Ficha não mapeada:" + getFicha().getClass().getName());
/*      */       }
/*      */       
/*  677 */       return fichaInfo.getIdFicha() + "." + fichaInfo.getIdFicha();
/*      */     }
/*      */     
/*      */     private Field getField() {
/*  681 */       return MapeamentoReferenciaDependencias.this.getField(getFicha().getClass(), getNomeCampoInformacao());
/*      */     }
/*      */     
/*      */     public Object getInformacao() throws MapeamentoReferenciaDependencias.MapeamentoDependenciasException {
/*      */       try {
/*  686 */         Field field = getField();
/*  687 */         boolean acces = field.isAccessible();
/*  688 */         field.setAccessible(true);
/*      */         try {
/*  690 */           return field.get(getFicha());
/*      */         } finally {
/*  692 */           field.setAccessible(acces);
/*      */         } 
/*  694 */       } catch (Exception e) {
/*  695 */         throw new MapeamentoReferenciaDependencias.MapeamentoDependenciasException("Erro ao acessar Informacao: \"" + getIdInformacao() + "\".", e);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void executarMetodoAtualizacao(List<List<Object>> listaDependencias) throws MapeamentoReferenciaDependencias.MapeamentoDependenciasException {
/*  700 */       String nomeMetodo = getCampoInformacao().metodoAtualizacao();
/*  701 */       Class<?> classeAtualizacao = getCampoInformacao().classeAtualizacao();
/*      */       
/*  703 */       Method[] methods = classeAtualizacao.getMethods();
/*  704 */       for (Method method : methods) {
/*  705 */         if (method.getName().equals(nomeMetodo)) {
/*  706 */           Class<?>[] tiposParametros = method.getParameterTypes();
/*  707 */           if (listaDependencias.size() + 2 != tiposParametros.length) {
/*  708 */             throw new MapeamentoReferenciaDependencias.MapeamentoDependenciasException("Erro ao executar metodo de atualizacao \"" + method.getName() + "\": Parametros incompativeis.");
/*      */           }
/*      */           
/*  711 */           Object[] parametros = new Object[tiposParametros.length];
/*  712 */           parametros[0] = getFicha();
/*  713 */           parametros[1] = getNomeCampoInformacao();
/*  714 */           int i = 0, j = 2;
/*  715 */           for (; i < listaDependencias.size() && j < parametros.length; 
/*  716 */             i++, j++) {
/*  717 */             List<?> lista = listaDependencias.get(i);
/*  718 */             if (tiposParametros[j].isArray()) {
/*  719 */               parametros[j] = lista.toArray((Object[])Array.newInstance(tiposParametros[j].getComponentType(), 0));
/*  720 */             } else if (!lista.isEmpty()) {
/*  721 */               parametros[j] = lista.get(0);
/*      */             } 
/*      */           } 
/*      */           
/*      */           try {
/*  726 */             method.invoke(null, parametros);
/*  727 */           } catch (IllegalAccessException|IllegalArgumentException|java.lang.reflect.InvocationTargetException e) {
/*  728 */             throw new MapeamentoReferenciaDependencias.MapeamentoDependenciasException("Erro ao executar metodo de atualizacao \"" + method.getName() + "\"", e);
/*      */           } 
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     private CampoInformacao getCampoInformacao() {
/*  737 */       return getField().<CampoInformacao>getAnnotation(CampoInformacao.class);
/*      */     }
/*      */     
/*      */     public String[] getReferenciasDependencias() throws MapeamentoReferenciaDependencias.MapeamentoDependenciasException {
/*  741 */       return (String[])((ArrayList)MapeamentoReferenciaDependencias.this.dependentesMap.get(getIdInformacao())).toArray((Object[])new String[0]);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<IdentificadorInformacao> obterDependentes(List<IdentificadorInformacao> dependencias) throws MapeamentoDependenciasException {
/*  753 */     ArrayList<IdentificadorInformacao> listaDependentes = new ArrayList<>();
/*  754 */     LinkedHashMap<String, IdentificadorInformacao> mapaDependentes = new LinkedHashMap<>();
/*      */     
/*  756 */     ArrayList<IdentificadorInformacao> listaDependencias = new ArrayList<>();
/*  757 */     listaDependencias.addAll(dependencias);
/*      */     
/*  759 */     int i = 0;
/*  760 */     while (!listaDependencias.isEmpty() && i < 20) {
/*  761 */       for (IdentificadorInformacao dependencia : listaDependencias) {
/*  762 */         String idDependencia = dependencia.getIdInformacao();
/*      */ 
/*      */ 
/*      */         
/*  766 */         ArrayList<String> refDependentes = this.dependenciasMap.get(idDependencia);
/*      */         
/*  768 */         if (refDependentes != null) {
/*  769 */           for (String refDependente : refDependentes) {
/*  770 */             String refFichaDependente = refDependente.replaceAll("(.*)(\\.)([^\\.]+$)", "$1$2");
/*      */             
/*  772 */             List<?> fichasDependente = obterFichaPorReferencia(dependencia.getFicha(), refFichaDependente);
/*  773 */             for (Object ficha : fichasDependente) {
/*  774 */               String nomeCampoDependente = refDependente.replaceAll("(.*)(\\.)([^\\.]+$)", "$3");
/*  775 */               IdentificadorInformacao idInfo = new IdentificadorInformacao(nomeCampoDependente, ficha);
/*      */               
/*  777 */               mapaDependentes.remove(idInfo.getHashCodeInformacao());
/*  778 */               mapaDependentes.put(idInfo.getHashCodeInformacao(), idInfo);
/*      */               
/*  780 */               listaDependentes.add(idInfo);
/*      */             } 
/*      */           } 
/*      */         }
/*      */       } 
/*  785 */       listaDependencias.clear();
/*  786 */       listaDependencias.addAll(listaDependentes);
/*  787 */       listaDependentes.clear();
/*  788 */       i++;
/*      */     } 
/*      */     
/*  791 */     if (!listaDependencias.isEmpty()) {
/*  792 */       throw new MapeamentoDependenciasException("Foi detectado uma referencia circular.");
/*      */     }
/*      */     
/*  795 */     listaDependentes.addAll(mapaDependentes.values());
/*      */     
/*  797 */     return listaDependentes;
/*      */   }
/*      */   
/*      */   public void atualizarDependente(IdentificadorInformacao dependente) throws MapeamentoDependenciasException {
/*  801 */     List<List<Object>> listaParametros = new ArrayList<>();
/*      */     
/*  803 */     String[] refDependencias = dependente.getReferenciasDependencias();
/*      */     
/*  805 */     if (refDependencias != null) {
/*  806 */       for (String refDependencia : refDependencias) {
/*  807 */         List<Object> dependencias = new ArrayList();
/*  808 */         String refFichaDependencia = refDependencia.replaceAll("(.*)(\\.)([^\\.]+$)", "$1$2");
/*      */         
/*  810 */         List<?> fichasDependencia = obterFichaPorReferencia(dependente.getFicha(), refFichaDependencia);
/*  811 */         for (Object ficha : fichasDependencia) {
/*  812 */           String nomeCampoDependencia = refDependencia.replaceAll("(.*)(\\.)([^\\.]+$)", "$3");
/*      */           
/*  814 */           if ("_quantidadeItensLista".equals(nomeCampoDependencia)) {
/*  815 */             ListaFichaInformacao listaFichaInfo = ficha.getClass().<ListaFichaInformacao>getAnnotation(ListaFichaInformacao.class);
/*  816 */             if (listaFichaInfo != null) {
/*  817 */               List<?> lista = obterListaFichas(ficha, listaFichaInfo.lista());
/*  818 */               dependencias.add(new Integer(lista.size()));
/*      */             }  continue;
/*      */           } 
/*  821 */           dependencias.add((new IdentificadorInformacao(nomeCampoDependencia, ficha)).getInformacao());
/*      */         } 
/*      */ 
/*      */         
/*  825 */         listaParametros.add(dependencias);
/*      */       } 
/*  827 */       dependente.executarMetodoAtualizacao(listaParametros);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void executarAtualizacoes(List<IdentificadorInformacao> dependencias) throws MapeamentoDependenciasException {
/*  832 */     List<IdentificadorInformacao> dependentes = obterDependentes(dependencias);
/*      */     
/*  834 */     for (IdentificadorInformacao dependente : dependentes)
/*  835 */       atualizarDependente(dependente); 
/*      */   }
/*      */   
/*      */   public MapeamentoReferenciaDependencias() {
/*  839 */     this.dependenciasAlteradas = new ArrayList<>();
/*      */   }
/*      */   public void informarAlteracao(Object ficha, String nomeCampoInformacao) {
/*  842 */     this.dependenciasAlteradas.add(new IdentificadorInformacao(nomeCampoInformacao, ficha));
/*      */   }
/*      */   
/*      */   public void executarAtualizacoes() throws MapeamentoDependenciasException {
/*  846 */     if (this.dependenciasAlteradas.isEmpty());
/*      */ 
/*      */ 
/*      */     
/*  850 */     executarAtualizacoes(this.dependenciasAlteradas);
/*  851 */     this.dependenciasAlteradas.clear();
/*      */   }
/*      */   
/*      */   public void executarAtualizacoes(Object ficha, String nomeCampoInformacao) throws MapeamentoDependenciasException {
/*  855 */     ArrayList<IdentificadorInformacao> dependencias = new ArrayList<>();
/*  856 */     dependencias.add(new IdentificadorInformacao(nomeCampoInformacao, ficha));
/*      */     
/*  858 */     executarAtualizacoes(dependencias);
/*      */   }
/*      */   
/*      */   public class ObservadorDependenciaInformacao
/*      */     extends Observador {
/*      */     Object ficha;
/*      */     String nomeCampoInformacao;
/*      */     
/*      */     public ObservadorDependenciaInformacao(Object ficha, String nomeCampoInformacao) {
/*  867 */       this.ficha = ficha;
/*  868 */       this.nomeCampoInformacao = nomeCampoInformacao;
/*      */     }
/*      */ 
/*      */     
/*      */     public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/*  873 */       MapeamentoReferenciaDependencias.this.informarAlteracao(this.ficha, this.nomeCampoInformacao);
/*      */       try {
/*  875 */         MapeamentoReferenciaDependencias.this.executarAtualizacoes();
/*  876 */       } catch (MapeamentoDependenciasException e) {
/*  877 */         e.printStackTrace();
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <T> List<T> teste(T[] a) {
/*  886 */     List<T> lista = new ArrayList<>();
/*  887 */     for (T t : a) {
/*  888 */       lista.add(t);
/*      */     }
/*      */ 
/*      */     
/*  892 */     return lista;
/*      */   }
/*      */   
/*      */   public static void calculaCampoA(ListaRendPJ ficha, String nomeCampo, String[] campoC) {
/*  896 */     System.out.println("\n--- calculaCampoA() --------------------------------------------------");
/*  897 */     System.out.println("Calculando campo \"" + nomeCampo + "\"");
/*  898 */     for (String conteudo : campoC) {
/*  899 */       System.out.println("\t" + conteudo);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void main_teste_1(String[] args) {
/*  904 */     MapeamentoReferenciaDependencias mapeamento = new MapeamentoReferenciaDependencias();
/*      */     
/*      */     try {
/*  907 */       mapeamento.mapearInformacoes(FichaDeclaracao.class, "dec");
/*      */       
/*  909 */       System.out.println("\n////////////////////////////////////////////////////");
/*  910 */       System.out.println("dependenciasMap: " + mapeamento.dependenciasMap);
/*  911 */       System.out.println("dependentesMap: " + mapeamento.dependentesMap);
/*  912 */       System.out.println("////////////////////////////////////////////////////\n");
/*      */       
/*  914 */       Objects.requireNonNull(mapeamento); FichaDeclaracao fichaDec = new FichaDeclaracao();
/*  915 */       mapeamento.mapearInstanciasFichas(fichaDec, null, "dec");
/*      */       
/*  917 */       for (FichaInfo f : mapeamento.fichas.values()) {
/*  918 */         System.out.println("Ficha: " + f.idFicha);
/*      */       }
/*      */ 
/*      */       
/*  922 */       System.out.println("\n\n--- Teste - obterInformacaoPorReferencia() ----------------------------------------");
/*  923 */       Object ficha = fichaDec.listaRendPJ.get(0);
/*  924 */       List<?> listInfo = mapeamento.obterInformacaoPorReferencia(ficha, ".campoC");
/*  925 */       for (Object info : listInfo) {
/*  926 */         System.out.println("Informacao: " + info);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  931 */       System.out.println("\n\n--- Teste - obterDependentes()  -----------------------------------------");
/*      */       
/*  933 */       ficha = fichaDec.listaRendPJ.get(0);
/*      */       
/*  935 */       Objects.requireNonNull(mapeamento); IdentificadorInformacao dependencia = new IdentificadorInformacao("campoA", ficha);
/*  936 */       ArrayList<IdentificadorInformacao> dependencias = new ArrayList<>();
/*  937 */       dependencias.add(dependencia);
/*      */       
/*  939 */       List<IdentificadorInformacao> dependentes = mapeamento.obterDependentes(dependencias);
/*      */       
/*  941 */       System.out.println("Dependencia => " + dependencia.getIdInformacao());
/*  942 */       for (IdentificadorInformacao identificadorInformacao : dependentes) {
/*  943 */         System.out.println("\t" + identificadorInformacao.getIdInformacao());
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  948 */       System.out.println("\n\n--- Teste - atualizarDependente() ------------------------------------------");
/*  949 */       ListaRendPJ listaRendPJ = fichaDec.listaRendPJ;
/*  950 */       Objects.requireNonNull(mapeamento); IdentificadorInformacao dependente = new IdentificadorInformacao("campoA", listaRendPJ);
/*  951 */       System.out.println("Atualizar dependente: " + dependente.getIdInformacao());
/*  952 */       mapeamento.atualizarDependente(dependente);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*  958 */     catch (Exception e) {
/*  959 */       e.printStackTrace();
/*      */     } 
/*      */   }
/*      */   public static void main_teste_2(String[] args) {
/*  963 */     MapeamentoReferenciaDependencias mapeamento = new MapeamentoReferenciaDependencias();
/*      */     
/*      */     try {
/*  966 */       System.out.println("\n--- Teste - mapearInformacoes() -----------------------------------------------");
/*  967 */       mapeamento.mapearInformacoes(DeclaracaoMultiExercicio.class, "dec");
/*  968 */       System.out.println("\ndependentesMap:");
/*  969 */       for (String dependente : mapeamento.dependentesMap.keySet()) {
/*  970 */         System.out.println("\tdependente : " + dependente);
/*  971 */         for (String dependencia : mapeamento.dependentesMap.get(dependente)) {
/*  972 */           System.out.println("\t\tdependencia : " + dependencia);
/*      */         }
/*      */       } 
/*  975 */       System.out.println("\ndependenciasMap:");
/*  976 */       for (String dependencia : mapeamento.dependenciasMap.keySet()) {
/*  977 */         System.out.println("\tdependencia : " + dependencia);
/*  978 */         for (String dependente : mapeamento.dependenciasMap.get(dependencia)) {
/*  979 */           System.out.println("\t\tdependente : " + dependente);
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*  984 */       DeclaracaoMultiExercicio dec = new DeclaracaoMultiExercicio();
/*      */ 
/*      */       
/*  987 */       System.out.println("\n--- Teste - mapearInstanciasFichas() -----------------------------------------------");
/*      */       
/*  989 */       Objects.requireNonNull(dec); dec.getRendPJ().getColecaoRendPJTitular().itens().add(new DeclaracaoMultiExercicio.DetalheRendPJ(dec));
/*      */       
/*  991 */       mapeamento.mapearInstanciasFichas(dec, null, "dec");
/*      */       
/*  993 */       System.out.println("Fichas mapeadas:");
/*  994 */       for (FichaInfo ficha : mapeamento.fichas.values()) {
/*  995 */         System.out.println("\t" + ficha.idFicha);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1000 */       System.out.println("\n--- Teste - mapearInstanciasFichas() -----------------------------------------------");
/*      */       
/* 1002 */       Objects.requireNonNull(dec); DeclaracaoMultiExercicio.DetalheRendPJ rendPJTitular = new DeclaracaoMultiExercicio.DetalheRendPJ(dec);
/* 1003 */       dec.getRendPJ().getColecaoRendPJTitular().itens().add(rendPJTitular);
/*      */ 
/*      */       
/* 1006 */       rendPJTitular = null;
/*      */       
/* 1008 */       Objects.requireNonNull(dec); DeclaracaoMultiExercicio.DetalheRendPJ rendPJDependente = new DeclaracaoMultiExercicio.DetalheRendPJ(dec);
/* 1009 */       dec.getRendPJ().getColecaoRendPJDependente().itens().add(rendPJDependente);
/*      */ 
/*      */       
/* 1012 */       rendPJDependente = null;
/*      */       
/* 1014 */       System.out.println("Fichas mapeadas:");
/* 1015 */       for (FichaInfo ficha : mapeamento.fichas.values()) {
/* 1016 */         System.out.println("\t" + ficha.idFicha);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1021 */       System.out.println("\n\n--- Teste - obterDependentes()  -----------------------------------------");
/*      */       
/* 1023 */       ArrayList<IdentificadorInformacao> dependencias = new ArrayList<>();
/* 1024 */       Objects.requireNonNull(mapeamento); dependencias.add(new IdentificadorInformacao("rendRecebidoPJ", dec.getRendPJ().getColecaoRendPJTitular().itens().get(0)));
/* 1025 */       Objects.requireNonNull(mapeamento); dependencias.add(new IdentificadorInformacao("rendRecebidoPJ", dec.getRendPJ().getColecaoRendPJDependente().itens().get(0)));
/*      */       
/* 1027 */       System.out.println("Dependencias => ");
/* 1028 */       for (int i = 0; i < dependencias.size(); i++) {
/* 1029 */         System.out.println("\t" + ((IdentificadorInformacao)dependencias.get(i)).getIdInformacao());
/*      */       }
/*      */       
/* 1032 */       List<IdentificadorInformacao> dependentes = mapeamento.obterDependentes(dependencias);
/* 1033 */       dependencias = null;
/*      */       
/* 1035 */       System.out.println("Dependentes => ");
/* 1036 */       for (int j = 0; j < dependentes.size(); j++) {
/* 1037 */         System.out.println("\t" + ((IdentificadorInformacao)dependentes.get(j)).getIdInformacao());
/*      */       }
/* 1039 */       dependentes = null;
/*      */ 
/*      */ 
/*      */       
/* 1043 */       System.out.println("\n\n--- Teste - executarAtualizacoes()  -----------------------------------------");
/*      */       
/* 1045 */       ((DeclaracaoMultiExercicio.DetalheRendPJ)dec.getRendPJ().getColecaoRendPJTitular().itens().get(0)).getRendRecebidoPJ().setConteudo("120000,00");
/*      */ 
/*      */       
/* 1048 */       ((DeclaracaoMultiExercicio.DetalheRendPJ)dec.getRendPJ().getColecaoRendPJDependente().itens().get(0)).getRendRecebidoPJ().setConteudo("70000,00");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1055 */       System.out.println("\n\n--- Teste - excluir item de lista  -----------------------------------------");
/*      */       
/* 1057 */       System.out.println("Fichas mapeadas:");
/* 1058 */       for (FichaInfo ficha : mapeamento.fichas.values()) {
/* 1059 */         System.out.println("\t" + ficha.idFicha);
/*      */       }
/* 1061 */       System.out.println("Numero de fichas mapeadas: " + mapeamento.fichas.values().size());
/*      */ 
/*      */       
/* 1064 */       System.out.println("Apagando um item em ColecaoRendPJDependente...");
/*      */       
/* 1066 */       WeakReference<Object> w = new WeakReference(dec.getRendPJ().getColecaoRendPJDependente().itens().get(0));
/* 1067 */       dec.getRendPJ().getColecaoRendPJDependente().itens().remove(0);
/*      */       
/* 1069 */       while (w.get() != null) {
/* 1070 */         System.gc();
/* 1071 */         System.out.println("Esperando GC..");
/*      */       } 
/*      */       
/* 1074 */       System.out.println("Fichas mapeadas:");
/* 1075 */       for (FichaInfo ficha : mapeamento.fichas.values()) {
/* 1076 */         System.out.println("\t" + ficha.idFicha);
/*      */       }
/* 1078 */       System.out.println("Numero de fichas mapeadas: " + mapeamento.fichas.values().size());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1101 */       System.out.println("Excluindo Declaracao...");
/* 1102 */       w = new WeakReference(dec);
/* 1103 */       dec = null;
/* 1104 */       while (w.get() != null) {
/* 1105 */         System.gc();
/* 1106 */         System.out.println("Esperando GC...");
/*      */       
/*      */       }
/*      */ 
/*      */     
/*      */     }
/* 1112 */     catch (Exception e) {
/* 1113 */       e.printStackTrace();
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void main(String[] args) {
/* 1118 */     MapeamentoReferenciaDependencias mapeamento = new MapeamentoReferenciaDependencias();
/*      */     
/*      */     try {
/* 1121 */       System.out.println("\n--- Teste - mapearInformacoes() -----------------------------------------------");
/*      */       
/* 1123 */       mapeamento.mapearInformacoes(DeclaracaoMultiExercicio.class, "dec");
/*      */       
/* 1125 */       System.out.println("\ndependentesMap:");
/* 1126 */       for (String dependente : mapeamento.dependentesMap.keySet()) {
/* 1127 */         System.out.println("\tdependente : " + dependente);
/* 1128 */         for (String dependencia : mapeamento.dependentesMap.get(dependente)) {
/* 1129 */           System.out.println("\t\tdependencia : " + dependencia);
/*      */         }
/*      */       } 
/* 1132 */       System.out.println("\ndependenciasMap:");
/* 1133 */       for (String dependencia : mapeamento.dependenciasMap.keySet()) {
/* 1134 */         System.out.println("\tdependencia : " + dependencia);
/* 1135 */         for (String dependente : mapeamento.dependenciasMap.get(dependencia)) {
/* 1136 */           System.out.println("\t\tdependente : " + dependente);
/*      */         }
/*      */       } 
/*      */       
/* 1140 */       DeclaracaoMultiExercicio dec = new DeclaracaoMultiExercicio();
/*      */ 
/*      */ 
/*      */       
/* 1144 */       System.out.println("\n--- Teste - mapearInstanciasFichas() -----------------------------------------------");
/*      */       
/* 1146 */       mapeamento.mapearInstanciasFichas(dec, null, "dec");
/*      */       
/* 1148 */       System.out.println("Fichas mapeadas:");
/* 1149 */       for (FichaInfo ficha : mapeamento.fichas.values()) {
/* 1150 */         System.out.println("\t" + ficha.idFicha);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1155 */       System.out.println("\n--- Teste - Inserir Dados -----------------------------------------------");
/*      */       
/* 1157 */       Objects.requireNonNull(dec); dec.getRendPJ().getColecaoRendPJTitular().itens().add(new DeclaracaoMultiExercicio.DetalheRendPJ(dec));
/*      */       
/* 1159 */       Objects.requireNonNull(dec); dec.getRendPJ().getColecaoRendPJDependente().itens().add(new DeclaracaoMultiExercicio.DetalheRendPJ(dec));
/*      */       
/* 1161 */       System.out.println("Fichas mapeadas:");
/* 1162 */       for (FichaInfo ficha : mapeamento.fichas.values()) {
/* 1163 */         System.out.println("\t" + ficha.idFicha);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1168 */       System.out.println("\n\n--- Teste - executarAtualizacoes()  -----------------------------------------");
/*      */       
/* 1170 */       ((DeclaracaoMultiExercicio.DetalheRendPJ)dec.getRendPJ().getColecaoRendPJTitular().itens().get(0)).getRendRecebidoPJ().setConteudo("120000,00");
/*      */       
/* 1172 */       ((DeclaracaoMultiExercicio.DetalheRendPJ)dec.getRendPJ().getColecaoRendPJDependente().itens().get(0)).getRendRecebidoPJ().setConteudo("70000,00");
/*      */     
/*      */     }
/* 1175 */     catch (Exception e) {
/*      */       
/* 1177 */       e.printStackTrace();
/*      */     } 
/*      */   }
/*      */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irp\\util\calculos\MapeamentoReferenciaDependencias.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */