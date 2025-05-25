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
/*      */ 
/*      */ public class MapeadorCalculos
/*      */ {
/*   25 */   final Map<String, ArrayList<String>> dependenciasMap = new HashMap<>();
/*   26 */   final Map<String, ArrayList<String>> dependentesMap = new HashMap<>();
/*   27 */   final Map<Object, FichaInfo> fichas = new WeakHashMap<>();
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
/*   41 */       Objects.requireNonNull(filhas);
/*      */       
/*   43 */       Map<String, List<WeakReference<?>>> mapFilhas = new HashMap<>(filhas.size());
/*   44 */       for (String filha : filhas.keySet()) {
/*   45 */         List<WeakReference<?>> novaLista = new ArrayList<>();
/*   46 */         for (Object objFilha : filhas.get(filha)) {
/*   47 */           WeakReference<?> w = new WeakReference(objFilha);
/*   48 */           novaLista.add(w);
/*      */         } 
/*   50 */         mapFilhas.put(filha, novaLista);
/*      */       } 
/*      */       
/*   53 */       this.idFicha = Objects.<String>requireNonNull(idFicha);
/*   54 */       this.ficha = new WeakReference(Objects.requireNonNull(ficha));
/*   55 */       this.pai = new WeakReference(pai);
/*   56 */       this.filhas = mapFilhas;
/*      */     }
/*      */     
/*      */     public String getIdFicha() {
/*   60 */       return this.idFicha;
/*      */     }
/*      */     
/*      */     public Object getFicha() {
/*   64 */       return this.ficha.get();
/*      */     }
/*      */     
/*      */     public Object getPai() {
/*   68 */       return this.pai.get();
/*      */     }
/*      */     
/*      */     public void adicionarFilha(String nomeFilha, Object filha) {
/*   72 */       List<WeakReference<?>> l = this.filhas.get(nomeFilha);
/*   73 */       if (l == null) {
/*   74 */         l = new ArrayList<>();
/*   75 */         this.filhas.put(nomeFilha, l);
/*      */       } 
/*   77 */       l.add(new WeakReference(filha));
/*      */     }
/*      */     
/*      */     public void removerFilha(String nomeFicha, Object item) {
/*   81 */       List<WeakReference<?>> lista = this.filhas.get(nomeFicha);
/*      */       
/*   83 */       for (int i = 0; i < lista.size(); i++) {
/*   84 */         Object obj = ((WeakReference)lista.get(i)).get();
/*   85 */         if (obj != null && obj == item) {
/*   86 */           lista.remove(i);
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     public List<?> getFilha(String nomeFicha) {
/*   93 */       List<Object> list = new ArrayList();
/*   94 */       List<WeakReference<?>> listRemove = new ArrayList<>();
/*   95 */       for (WeakReference<?> wFilha : this.filhas.get(nomeFicha)) {
/*   96 */         if (wFilha.get() != null) {
/*   97 */           list.add(wFilha.get()); continue;
/*      */         } 
/*   99 */         listRemove.add(wFilha);
/*      */       } 
/*      */       
/*  102 */       ((List)this.filhas.get(nomeFicha)).removeAll(listRemove);
/*      */       
/*  104 */       return list;
/*      */     }
/*      */     
/*      */     public Map<String, List<?>> getFilhas() {
/*  108 */       Map<String, List<?>> map = new HashMap<>();
/*  109 */       for (String filha : this.filhas.keySet()) {
/*  110 */         List<Object> list = new ArrayList();
/*  111 */         List<WeakReference<?>> listRemove = new ArrayList<>();
/*  112 */         for (WeakReference<?> wFilha : this.filhas.get(filha)) {
/*  113 */           if (wFilha.get() != null) {
/*  114 */             list.add(wFilha.get()); continue;
/*      */           } 
/*  116 */           listRemove.add(wFilha);
/*      */         } 
/*      */         
/*  119 */         ((List)this.filhas.get(filha)).removeAll(listRemove);
/*      */         
/*  121 */         map.put(filha, list);
/*      */       } 
/*      */       
/*  124 */       return map;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public List<?> obterFichaPorReferencia(Object ficha, String referencia) throws MapeamentoDependenciasException {
/*  130 */     List<Object> fichas = new ArrayList();
/*  131 */     Object fichaAtual = ficha;
/*      */     
/*  133 */     int i = 0;
/*  134 */     while (fichaAtual != null && i < referencia
/*  135 */       .length() && referencia
/*  136 */       .startsWith("..", i)) {
/*  137 */       FichaInfo fichaInfo = this.fichas.get(fichaAtual);
/*  138 */       if (fichaInfo == null) {
/*  139 */         throw new MapeamentoDependenciasException("Ficha não mapeada: " + fichaAtual.getClass().getName());
/*      */       }
/*  141 */       fichaAtual = fichaInfo.getPai();
/*  142 */       i++;
/*      */     } 
/*      */     
/*  145 */     fichas.add(fichaAtual);
/*      */     
/*  147 */     for (String nomeFicha : referencia.substring(i).split("\\.")) {
/*  148 */       if (!nomeFicha.isEmpty()) {
/*  149 */         ArrayList<Object> lista = new ArrayList();
/*  150 */         for (Object fichaObj : fichas) {
/*  151 */           FichaInfo fichaInfo = this.fichas.get(fichaObj);
/*  152 */           if (fichaInfo == null) {
/*  153 */             throw new MapeamentoDependenciasException("Ficha não mapeada: " + fichaObj.getClass().getName());
/*      */           }
/*  155 */           List<?> filha = fichaInfo.getFilha(nomeFicha);
/*  156 */           if (filha == null) {
/*  157 */             throw new MapeamentoDependenciasException("Propriedade \"" + nomeFicha + "\" não mapeada para a Ficha " + fichaInfo.getIdFicha());
/*      */           }
/*  159 */           lista.addAll(filha);
/*      */         } 
/*      */         
/*  162 */         fichas = lista;
/*      */       } 
/*      */     } 
/*      */     
/*  166 */     return fichas;
/*      */   }
/*      */   
/*      */   public boolean mapearInstanciasFichas(Object ficha, Object fichaPai, String prefixo) throws MapeamentoDependenciasException {
/*  170 */     Class<?> classFicha = ficha.getClass();
/*  171 */     boolean ehFicha = classFicha.isAnnotationPresent((Class)FichaInformacao.class);
/*      */ 
/*      */     
/*  174 */     if (ehFicha) {
/*  175 */       HashMap<String, List<?>> filhas = new HashMap<>();
/*  176 */       String idFicha = prefixo.replaceAll("\\[N?\\]", "");
/*      */       
/*  178 */       Field[] fields = getFields(classFicha);
/*  179 */       for (Field field : fields) {
/*  180 */         if (!field.getName().startsWith("this$")) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  185 */           String nomeCampo = prefixo + "." + prefixo;
/*      */           
/*  187 */           if (field.isAnnotationPresent((Class)CampoInformacao.class)) {
/*      */             
/*  189 */             String idInformacao = idFicha + "." + idFicha;
/*      */             
/*  191 */             if (this.dependenciasMap.containsKey(idInformacao) && 
/*  192 */               !this.dependentesMap.containsKey(idInformacao)) {
/*  193 */               ObservadorDependenciaInformacao obs = new ObservadorDependenciaInformacao(ficha, field.getName());
/*      */               
/*      */               try {
/*  196 */                 boolean accessible = field.isAccessible();
/*  197 */                 field.setAccessible(true);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*      */               }
/*  206 */               catch (Exception e) {
/*  207 */                 throw new MapeamentoDependenciasException("Ocorreu um erro ao acessar Informacao (" + nomeCampo + ")", e);
/*      */               
/*      */               }
/*      */ 
/*      */             
/*      */             }
/*      */           
/*      */           }
/*  215 */           else if (field.getType().isAnnotationPresent((Class)FichaInformacao.class)) {
/*      */             try {
/*      */               Object filha;
/*  218 */               boolean accessible = field.isAccessible();
/*  219 */               field.setAccessible(true);
/*      */               
/*      */               try {
/*  222 */                 filha = field.get(ficha);
/*      */               } finally {
/*  224 */                 field.setAccessible(accessible);
/*      */               } 
/*  226 */               if (mapearInstanciasFichas(filha, ficha, nomeCampo)) {
/*  227 */                 ArrayList<Object> listInstanciasFilha = new ArrayList();
/*  228 */                 listInstanciasFilha.add(filha);
/*  229 */                 filhas.put(field.getName(), listInstanciasFilha);
/*      */               } 
/*  231 */             } catch (Exception e) {
/*  232 */               throw new MapeamentoDependenciasException("Ocorreu um erro ao acessar Ficha (" + nomeCampo + ")", e);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*  237 */       ListaFichaInformacao listaFichaInfo = classFicha.<ListaFichaInformacao>getAnnotation(ListaFichaInformacao.class);
/*  238 */       if (listaFichaInfo != null && listaFichaInfo
/*  239 */         .classeFicha().isAnnotationPresent((Class)FichaInformacao.class)) {
/*  240 */         String nomeCampo = prefixo + "._itens";
/*      */         
/*  242 */         ObservadorLista obs = new ObservadorLista(ficha, nomeCampo);
/*      */         
/*  244 */         ((Colecao)ficha).addObservador(obs);
/*      */         
/*  246 */         String nomeLista = listaFichaInfo.lista();
/*  247 */         List<?> listInstanciasFilha = obterListaFichas(ficha, nomeLista);
/*      */         
/*  249 */         for (Object filha : listInstanciasFilha) {
/*  250 */           mapearInstanciasFichas(filha, ficha, nomeCampo);
/*      */         }
/*  252 */         filhas.put("_itens", listInstanciasFilha);
/*      */       } 
/*      */       
/*  255 */       FichaInfo fichaInfo = new FichaInfo(idFicha, ficha, fichaPai, filhas);
/*      */ 
/*      */       
/*  258 */       this.fichas.put(ficha, fichaInfo);
/*      */       
/*  260 */       if (idFicha.endsWith("_itens")) {
/*  261 */         fichaInfo = this.fichas.get(fichaPai);
/*  262 */         if (fichaInfo != null) {
/*  263 */           fichaInfo.adicionarFilha("_itens", ficha);
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  269 */     return ehFicha;
/*      */   }
/*      */   public List<?> obterListaFichas(Object ficha, String nomeLista) throws MapeamentoDependenciasException {
/*      */     List<?> listaItens;
/*  273 */     Class<?> classFicha = ficha.getClass();
/*      */     
/*      */     try {
/*  276 */       if (!nomeLista.isEmpty()) {
/*      */         
/*  278 */         Field field = getField(classFicha, nomeLista);
/*  279 */         if (field != null) {
/*  280 */           boolean accessible = field.isAccessible();
/*  281 */           field.setAccessible(true);
/*      */           try {
/*  283 */             listaItens = (List)field.get(ficha);
/*      */           } finally {
/*  285 */             field.setAccessible(accessible);
/*      */           } 
/*      */         } else {
/*      */           
/*  289 */           Method m = classFicha.getMethod(nomeLista, new Class[0]);
/*  290 */           listaItens = (List)m.invoke(ficha, new Object[0]);
/*      */         } 
/*      */       } else {
/*  293 */         listaItens = (List)ficha;
/*      */       } 
/*  295 */     } catch (Exception e) {
/*  296 */       throw new MapeamentoDependenciasException("Ocorreu um erro ao acessar lista de Fichas (" + classFicha.getName() + "." + nomeLista + ")", e);
/*      */     } 
/*      */     
/*  299 */     return listaItens;
/*      */   }
/*      */   
/*      */   public class ObservadorLista
/*      */     extends Observador {
/*      */     Object ficha;
/*      */     String prefixo;
/*      */     
/*      */     public ObservadorLista(Object ficha, String prefixo) {
/*  308 */       this.ficha = ficha;
/*  309 */       this.prefixo = prefixo;
/*      */     }
/*      */ 
/*      */     
/*      */     public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/*      */       try {
/*  315 */         if ("ObjetoInserido".equals(nomePropriedade)) {
/*  316 */           MapeadorCalculos.this.mapearInstanciasFichas(valorNovo, this.ficha, this.prefixo);
/*      */           
/*  318 */           MapeadorCalculos.this.informarAlteracao(this.ficha, "_quantidadeItensLista");
/*  319 */           MapeadorCalculos.this.executarAtualizacoes();
/*  320 */         } else if ("ObjetoRemovido".equals(nomePropriedade)) {
/*  321 */           ((MapeadorCalculos.FichaInfo)MapeadorCalculos.this.fichas.get(observado)).removerFilha("_itens", valorNovo);
/*      */           
/*  323 */           MapeadorCalculos.this.informarAlteracao(this.ficha, "_quantidadeItensLista");
/*  324 */           MapeadorCalculos.this.executarAtualizacoes();
/*      */         } 
/*  326 */       } catch (MapeamentoDependenciasException e) {
/*  327 */         e.printStackTrace();
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Field[] getFields(Class<?> c) {
/*  349 */     Field[] thisFields = c.getDeclaredFields();
/*      */ 
/*      */     
/*  352 */     if (c.getSuperclass() != null) {
/*      */ 
/*      */ 
/*      */       
/*  356 */       Field[] superClassFields = getFields(c.getSuperclass());
/*      */ 
/*      */       
/*  359 */       Field[] allFields = new Field[superClassFields.length + thisFields.length];
/*      */ 
/*      */       
/*  362 */       System.arraycopy(superClassFields, 0, allFields, 0, superClassFields.length);
/*      */ 
/*      */       
/*  365 */       System.arraycopy(thisFields, 0, allFields, superClassFields.length, thisFields.length);
/*      */ 
/*      */       
/*  368 */       return allFields;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  373 */     return thisFields;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Field getField(Class<?> c, String name) {
/*      */     try {
/*  380 */       return c.getDeclaredField(name);
/*  381 */     } catch (Exception e) {
/*      */       
/*  383 */       if (c.getSuperclass() != null)
/*      */       {
/*      */         
/*  386 */         return getField(c.getSuperclass(), name);
/*      */       }
/*      */ 
/*      */       
/*  390 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public class MapeamentoDependenciasException extends Exception { private static final long serialVersionUID = 1L;
/*      */     
/*      */     public MapeamentoDependenciasException(String arg0, Throwable arg1) {
/*  397 */       super(arg0, arg1);
/*      */     }
/*      */     
/*      */     public MapeamentoDependenciasException(String arg0) {
/*  401 */       super(arg0);
/*      */     } }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String nomeInfoPelaReferencia(String nomeCampo, String referencia) throws MapeamentoDependenciasException {
/*  408 */     String nomeInfo = "";
/*      */     
/*  410 */     int lastIndex = nomeCampo.length();
/*  411 */     int i = 0;
/*  412 */     for (; i < referencia.length() && referencia.startsWith(".", i); 
/*  413 */       i++) {
/*  414 */       lastIndex = nomeCampo.lastIndexOf(".", lastIndex);
/*  415 */       if (lastIndex >= 0) {
/*  416 */         nomeInfo = nomeCampo.substring(0, lastIndex) + nomeCampo.substring(0, lastIndex);
/*  417 */         lastIndex--;
/*      */       } else {
/*  419 */         throw new MapeamentoDependenciasException("Identificacao de campo Informacao inconsistente: \"" + referencia + "\"");
/*      */       } 
/*      */     } 
/*      */     
/*  423 */     return nomeInfo;
/*      */   }
/*      */   public String referenciaPeloNomeInfo(String nomeCampo, String nomeInfo) throws MapeamentoDependenciasException {
/*  426 */     StringBuilder sbDependente = new StringBuilder();
/*  427 */     int lastIndex = nomeInfo.length();
/*      */     
/*  429 */     while ((lastIndex = nomeInfo.lastIndexOf(".", lastIndex)) >= 0) {
/*  430 */       String prefix = nomeInfo.substring(0, lastIndex);
/*  431 */       if (nomeCampo.startsWith(prefix)) {
/*      */         break;
/*      */       }
/*  434 */       sbDependente.append(".");
/*  435 */       lastIndex--;
/*      */     } 
/*  437 */     sbDependente.append(nomeCampo.substring(lastIndex));
/*      */     
/*  439 */     return sbDependente.toString();
/*      */   }
/*      */   
/*      */   public void mapearInformacoes(Class<?> classFicha, String prefixo) throws MapeamentoDependenciasException {
/*  443 */     String nomeCampo = null;
/*      */     
/*  445 */     FichaInformacao fichaInfo = classFicha.<FichaInformacao>getAnnotation(FichaInformacao.class);
/*      */     
/*  447 */     if (fichaInfo != null) {
/*      */       
/*  449 */       Field[] fields = getFields(classFicha);
/*  450 */       for (Field field : fields) {
/*  451 */         if (!field.getName().startsWith("this$")) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  456 */           nomeCampo = prefixo + "." + prefixo;
/*      */           
/*  458 */           CampoInformacao campoInfo = field.<CampoInformacao>getAnnotation(CampoInformacao.class);
/*  459 */           if (campoInfo != null) {
/*      */             
/*  461 */             String[] dependencias = campoInfo.dependencias();
/*  462 */             if (dependencias.length > 0) {
/*  463 */               System.out.println("\nCampo Dependente: " + nomeCampo);
/*      */               
/*  465 */               for (String dependencia : dependencias) {
/*      */                 
/*  467 */                 String nomeDependencia = nomeInfoPelaReferencia(nomeCampo, dependencia);
/*  468 */                 System.out.println("\t" + nomeDependencia);
/*      */ 
/*      */                 
/*  471 */                 String idDependente = nomeCampo.replaceAll("\\[N?\\]", "");
/*  472 */                 ArrayList<String> listDependencias = this.dependentesMap.get(idDependente);
/*  473 */                 if (listDependencias == null) {
/*  474 */                   listDependencias = new ArrayList<>();
/*  475 */                   this.dependentesMap.put(idDependente, listDependencias);
/*      */                 } 
/*      */                 
/*  478 */                 listDependencias.add(dependencia.replaceAll("\\[N?\\]", ""));
/*      */ 
/*      */                 
/*  481 */                 String idDependencia = nomeDependencia.replaceAll("\\[N?\\]", "");
/*  482 */                 ArrayList<String> listDependentes = this.dependenciasMap.get(idDependencia);
/*  483 */                 if (listDependentes == null) {
/*  484 */                   listDependentes = new ArrayList<>();
/*  485 */                   this.dependenciasMap.put(idDependencia, listDependentes);
/*      */                 } 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*  491 */                 String dependente = referenciaPeloNomeInfo(nomeCampo, nomeDependencia);
/*  492 */                 dependente = dependente.replaceAll("\\[N?\\]", "");
/*  493 */                 listDependentes.add(dependente);
/*      */               } 
/*      */             } 
/*      */           } else {
/*  497 */             mapearInformacoes(field.getType(), nomeCampo);
/*      */           } 
/*      */         } 
/*      */       } 
/*  501 */       ListaFichaInformacao listaFicha = classFicha.<ListaFichaInformacao>getAnnotation(ListaFichaInformacao.class);
/*  502 */       if (listaFicha != null) {
/*  503 */         nomeCampo = prefixo + "._itens[N]";
/*      */         
/*  505 */         mapearInformacoes(listaFicha.classeFicha(), nomeCampo);
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
/*  517 */       this.nomeCampoInformacao = Objects.<String>requireNonNull(nomeCampoInformacao);
/*  518 */       this.ficha = Objects.requireNonNull(ficha);
/*  519 */       this.hashCodeInformacao = "" + ficha.hashCode() + "." + ficha.hashCode();
/*      */     }
/*      */     
/*      */     public String getHashCodeInformacao() {
/*  523 */       return this.hashCodeInformacao;
/*      */     }
/*      */     
/*      */     public String getNomeCampoInformacao() {
/*  527 */       return this.nomeCampoInformacao;
/*      */     }
/*      */     
/*      */     public Object getFicha() {
/*  531 */       return this.ficha;
/*      */     }
/*      */     
/*      */     public String getIdInformacao() throws MapeadorCalculos.MapeamentoDependenciasException {
/*  535 */       MapeadorCalculos.FichaInfo fichaInfo = MapeadorCalculos.this.fichas.get(getFicha());
/*  536 */       if (fichaInfo == null) {
/*  537 */         throw new MapeadorCalculos.MapeamentoDependenciasException("Ficha não mapeada:" + getFicha().getClass().getName());
/*      */       }
/*      */       
/*  540 */       return fichaInfo.getIdFicha() + "." + fichaInfo.getIdFicha();
/*      */     }
/*      */     
/*      */     private Field getField() {
/*  544 */       return MapeadorCalculos.this.getField(getFicha().getClass(), getNomeCampoInformacao());
/*      */     }
/*      */     
/*      */     public Object getInformacao() throws MapeadorCalculos.MapeamentoDependenciasException {
/*      */       try {
/*  549 */         Field field = getField();
/*  550 */         boolean acces = field.isAccessible();
/*  551 */         field.setAccessible(true);
/*      */         try {
/*  553 */           return field.get(getFicha());
/*      */         } finally {
/*  555 */           field.setAccessible(acces);
/*      */         } 
/*  557 */       } catch (Exception e) {
/*  558 */         throw new MapeadorCalculos.MapeamentoDependenciasException("Erro ao acessar Informacao: \"" + getIdInformacao() + "\".", e);
/*      */       } 
/*      */     }
/*      */     
/*      */     private CampoInformacao getCampoInformacao() {
/*  563 */       return getField().<CampoInformacao>getAnnotation(CampoInformacao.class);
/*      */     }
/*      */     
/*      */     public String[] getReferenciasDependencias() throws MapeadorCalculos.MapeamentoDependenciasException {
/*  567 */       return (String[])((ArrayList)MapeadorCalculos.this.dependentesMap.get(getIdInformacao())).toArray((Object[])new String[0]);
/*      */     }
/*      */     
/*      */     public Method obterMetodoAtualizacao(int qtdParametros) {
/*  571 */       String nomeMetodo = getCampoInformacao().metodoAtualizacao();
/*  572 */       Class<?> classeAtualizacao = getCampoInformacao().classeAtualizacao();
/*      */       
/*  574 */       Method[] methods = classeAtualizacao.getMethods();
/*  575 */       for (Method method : methods) {
/*  576 */         if (method.getName().equals(nomeMetodo) && method
/*  577 */           .getParameterCount() == qtdParametros) {
/*  578 */           return method;
/*      */         }
/*      */       } 
/*      */       
/*  582 */       return null;
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
/*      */   public List<IdentificadorInformacao> obterInstanciasDependentes(List<IdentificadorInformacao> dependencias) throws MapeamentoDependenciasException {
/*  594 */     ArrayList<IdentificadorInformacao> listaDependentes = new ArrayList<>();
/*  595 */     LinkedHashMap<String, IdentificadorInformacao> mapaDependentes = new LinkedHashMap<>();
/*      */     
/*  597 */     ArrayList<IdentificadorInformacao> listaDependencias = new ArrayList<>();
/*  598 */     listaDependencias.addAll(dependencias);
/*      */     
/*  600 */     int i = 0;
/*  601 */     while (!listaDependencias.isEmpty() && i < 20) {
/*  602 */       for (IdentificadorInformacao dependencia : listaDependencias) {
/*  603 */         String idDependencia = dependencia.getIdInformacao();
/*      */ 
/*      */ 
/*      */         
/*  607 */         ArrayList<String> refDependentes = this.dependenciasMap.get(idDependencia);
/*      */         
/*  609 */         if (refDependentes != null) {
/*  610 */           for (String refDependente : refDependentes) {
/*  611 */             String refFichaDependente = refDependente.replaceAll("(.*)(\\.)([^\\.]+$)", "$1$2");
/*      */             
/*  613 */             List<?> fichasDependente = obterFichaPorReferencia(dependencia.getFicha(), refFichaDependente);
/*  614 */             for (Object ficha : fichasDependente) {
/*  615 */               String nomeCampoDependente = refDependente.replaceAll("(.*)(\\.)([^\\.]+$)", "$3");
/*  616 */               IdentificadorInformacao idInfo = new IdentificadorInformacao(nomeCampoDependente, ficha);
/*      */               
/*  618 */               mapaDependentes.remove(idInfo.getHashCodeInformacao());
/*  619 */               mapaDependentes.put(idInfo.getHashCodeInformacao(), idInfo);
/*      */               
/*  621 */               listaDependentes.add(idInfo);
/*      */             } 
/*      */           } 
/*      */         }
/*      */       } 
/*  626 */       listaDependencias.clear();
/*  627 */       listaDependencias.addAll(listaDependentes);
/*  628 */       listaDependentes.clear();
/*  629 */       i++;
/*      */     } 
/*      */     
/*  632 */     if (!listaDependencias.isEmpty()) {
/*  633 */       throw new MapeamentoDependenciasException("Foi detectado uma referencia circular.");
/*      */     }
/*      */     
/*  636 */     listaDependentes.addAll(mapaDependentes.values());
/*      */     
/*  638 */     return listaDependentes;
/*      */   }
/*      */   
/*      */   public void executarMetodoAtualizacao(Method metodo, Object ficha, String nomeCampoInformacao, List<List<Object>> listaDependencias) throws MapeamentoDependenciasException {
/*  642 */     Class<?>[] tiposParametros = metodo.getParameterTypes();
/*  643 */     Object[] parametros = new Object[tiposParametros.length];
/*      */     
/*  645 */     if (listaDependencias.size() + 2 != tiposParametros.length) {
/*  646 */       throw new MapeamentoDependenciasException("Erro ao executar metodo de atualizacao \"" + metodo.getName() + "\": Parametros incompativeis.");
/*      */     }
/*      */     
/*  649 */     parametros[0] = ficha;
/*  650 */     parametros[1] = nomeCampoInformacao;
/*  651 */     int i = 0, j = 2;
/*  652 */     for (; i < listaDependencias.size() && j < parametros.length; 
/*  653 */       i++, j++) {
/*  654 */       List<?> lista = listaDependencias.get(i);
/*  655 */       if (tiposParametros[j].isArray()) {
/*  656 */         parametros[j] = lista.toArray((Object[])Array.newInstance(tiposParametros[j].getComponentType(), 0));
/*  657 */       } else if (!lista.isEmpty()) {
/*  658 */         parametros[j] = lista.get(0);
/*      */       } 
/*      */     } 
/*      */     
/*      */     try {
/*  663 */       metodo.invoke(null, parametros);
/*  664 */     } catch (IllegalAccessException|IllegalArgumentException|java.lang.reflect.InvocationTargetException e) {
/*  665 */       throw new MapeamentoDependenciasException("Erro ao executar metodo de atualizacao \"" + metodo.getName() + "\"", e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public List<List<Object>> obterInstanciasDependencias(IdentificadorInformacao dependente) throws MapeamentoDependenciasException {
/*  670 */     List<List<Object>> listaParametros = new ArrayList<>();
/*      */     
/*  672 */     String[] refDependencias = dependente.getReferenciasDependencias();
/*      */     
/*  674 */     if (refDependencias != null) {
/*  675 */       for (String refDependencia : refDependencias) {
/*  676 */         List<Object> dependencias = new ArrayList();
/*  677 */         String refFichaDependencia = refDependencia.replaceAll("(.*)(\\.)([^\\.]+$)", "$1$2");
/*      */         
/*  679 */         List<?> fichasDependencia = obterFichaPorReferencia(dependente.getFicha(), refFichaDependencia);
/*  680 */         for (Object ficha : fichasDependencia) {
/*  681 */           String nomeCampoDependencia = refDependencia.replaceAll("(.*)(\\.)([^\\.]+$)", "$3");
/*      */           
/*  683 */           if ("_quantidadeItensLista".equals(nomeCampoDependencia)) {
/*  684 */             ListaFichaInformacao listaFichaInfo = ficha.getClass().<ListaFichaInformacao>getAnnotation(ListaFichaInformacao.class);
/*  685 */             if (listaFichaInfo != null) {
/*  686 */               List<?> lista = obterListaFichas(ficha, listaFichaInfo.lista());
/*  687 */               dependencias.add(new Integer(lista.size()));
/*      */             }  continue;
/*      */           } 
/*  690 */           dependencias.add((new IdentificadorInformacao(nomeCampoDependencia, ficha)).getInformacao());
/*      */         } 
/*      */ 
/*      */         
/*  694 */         listaParametros.add(dependencias);
/*      */       } 
/*      */     }
/*      */     
/*  698 */     return listaParametros;
/*      */   }
/*      */   
/*      */   protected void executarAtualizacoes(List<IdentificadorInformacao> dependencias) throws MapeamentoDependenciasException {
/*  702 */     List<IdentificadorInformacao> dependentes = obterInstanciasDependentes(dependencias);
/*      */     
/*  704 */     int i = 0;
/*  705 */     while (i < dependentes.size()) {
/*  706 */       Object ficha; IdentificadorInformacao dependente = dependentes.get(i);
/*  707 */       String nomeCampoInformacao = dependente.getNomeCampoInformacao();
/*  708 */       List<List<Object>> listaParametros = obterInstanciasDependencias(dependente);
/*      */ 
/*      */       
/*  711 */       Method metodo = dependente.obterMetodoAtualizacao(listaParametros.size() + 2);
/*  712 */       Class<?>[] tiposParametros = metodo.getParameterTypes();
/*  713 */       if (tiposParametros[0].isArray()) {
/*  714 */         i++;
/*  715 */         ArrayList<Object> listDep = new ArrayList();
/*  716 */         listDep.add(dependente.getFicha());
/*  717 */         while (i < dependentes.size()) {
/*  718 */           IdentificadorInformacao dep = dependentes.get(i);
/*      */           
/*  720 */           if (dep.getIdInformacao().equals(dependente
/*  721 */               .getIdInformacao())) {
/*  722 */             listDep.add(dep.getFicha());
/*  723 */             i++;
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/*  728 */         ficha = listDep.toArray((Object[])Array.newInstance(tiposParametros[0].getComponentType(), 0));
/*      */       } else {
/*  730 */         ficha = dependente.getFicha();
/*  731 */         i++;
/*      */       } 
/*      */       
/*  734 */       executarMetodoAtualizacao(metodo, ficha, nomeCampoInformacao, listaParametros);
/*      */     } 
/*      */   }
/*      */   public MapeadorCalculos() {
/*  738 */     this.dependenciasAlteradas = new ArrayList<>();
/*      */   }
/*      */   public void informarAlteracao(Object ficha, String nomeCampoInformacao) {
/*  741 */     this.dependenciasAlteradas.add(new IdentificadorInformacao(nomeCampoInformacao, ficha));
/*      */   }
/*      */   
/*      */   public void executarAtualizacoes() throws MapeamentoDependenciasException {
/*  745 */     if (this.dependenciasAlteradas.isEmpty());
/*      */ 
/*      */ 
/*      */     
/*  749 */     executarAtualizacoes(this.dependenciasAlteradas);
/*  750 */     this.dependenciasAlteradas.clear();
/*      */   }
/*      */   
/*      */   public void executarAtualizacoes(Object ficha, String nomeCampoInformacao) throws MapeamentoDependenciasException {
/*  754 */     ArrayList<IdentificadorInformacao> dependencias = new ArrayList<>();
/*  755 */     dependencias.add(new IdentificadorInformacao(nomeCampoInformacao, ficha));
/*      */     
/*  757 */     executarAtualizacoes(dependencias);
/*      */   }
/*      */   
/*      */   public class ObservadorDependenciaInformacao
/*      */     extends Observador {
/*      */     Object ficha;
/*      */     String nomeCampoInformacao;
/*      */     
/*      */     public ObservadorDependenciaInformacao(Object ficha, String nomeCampoInformacao) {
/*  766 */       this.ficha = ficha;
/*  767 */       this.nomeCampoInformacao = nomeCampoInformacao;
/*      */     }
/*      */ 
/*      */     
/*      */     public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/*  772 */       MapeadorCalculos.this.informarAlteracao(this.ficha, this.nomeCampoInformacao);
/*      */       try {
/*  774 */         MapeadorCalculos.this.executarAtualizacoes();
/*  775 */       } catch (MapeamentoDependenciasException e) {
/*  776 */         e.printStackTrace();
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static void main_(String[] args) {
/*  783 */     MapeadorCalculos mapeamento = new MapeadorCalculos();
/*      */     
/*      */     try {
/*  786 */       System.out.println("\n--- Teste - mapearInformacoes() -----------------------------------------------");
/*  787 */       mapeamento.mapearInformacoes(DeclaracaoMultiExercicio.class, "dec");
/*  788 */       System.out.println("\ndependentesMap:");
/*  789 */       for (String dependente : mapeamento.dependentesMap.keySet()) {
/*  790 */         System.out.println("\tdependente : " + dependente);
/*  791 */         for (String dependencia : mapeamento.dependentesMap.get(dependente)) {
/*  792 */           System.out.println("\t\tdependencia : " + dependencia);
/*      */         }
/*      */       } 
/*  795 */       System.out.println("\ndependenciasMap:");
/*  796 */       for (String dependencia : mapeamento.dependenciasMap.keySet()) {
/*  797 */         System.out.println("\tdependencia : " + dependencia);
/*  798 */         for (String dependente : mapeamento.dependenciasMap.get(dependencia)) {
/*  799 */           System.out.println("\t\tdependente : " + dependente);
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*  804 */       DeclaracaoMultiExercicio dec = new DeclaracaoMultiExercicio();
/*      */ 
/*      */       
/*  807 */       System.out.println("\n--- Teste - mapearInstanciasFichas() -----------------------------------------------");
/*      */       
/*  809 */       Objects.requireNonNull(dec); dec.getRendPJ().getColecaoRendPJTitular().itens().add(new DeclaracaoMultiExercicio.DetalheRendPJ(dec));
/*      */       
/*  811 */       mapeamento.mapearInstanciasFichas(dec, null, "dec");
/*      */       
/*  813 */       System.out.println("Fichas mapeadas:");
/*  814 */       for (FichaInfo ficha : mapeamento.fichas.values()) {
/*  815 */         System.out.println("\t" + ficha.idFicha);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  820 */       System.out.println("\n--- Teste - mapearInstanciasFichas() -----------------------------------------------");
/*      */       
/*  822 */       Objects.requireNonNull(dec); DeclaracaoMultiExercicio.DetalheRendPJ rendPJTitular = new DeclaracaoMultiExercicio.DetalheRendPJ(dec);
/*  823 */       dec.getRendPJ().getColecaoRendPJTitular().itens().add(rendPJTitular);
/*      */ 
/*      */       
/*  826 */       rendPJTitular = null;
/*      */       
/*  828 */       Objects.requireNonNull(dec); DeclaracaoMultiExercicio.DetalheRendPJ rendPJDependente = new DeclaracaoMultiExercicio.DetalheRendPJ(dec);
/*  829 */       dec.getRendPJ().getColecaoRendPJDependente().itens().add(rendPJDependente);
/*      */ 
/*      */       
/*  832 */       rendPJDependente = null;
/*      */       
/*  834 */       System.out.println("Fichas mapeadas:");
/*  835 */       for (FichaInfo ficha : mapeamento.fichas.values()) {
/*  836 */         System.out.println("\t" + ficha.idFicha);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  841 */       System.out.println("\n\n--- Teste - obterDependentes()  -----------------------------------------");
/*      */       
/*  843 */       ArrayList<IdentificadorInformacao> dependencias = new ArrayList<>();
/*  844 */       Objects.requireNonNull(mapeamento); dependencias.add(new IdentificadorInformacao("rendRecebidoPJ", dec.getRendPJ().getColecaoRendPJTitular().itens().get(0)));
/*  845 */       Objects.requireNonNull(mapeamento); dependencias.add(new IdentificadorInformacao("rendRecebidoPJ", dec.getRendPJ().getColecaoRendPJDependente().itens().get(0)));
/*      */       
/*  847 */       System.out.println("Dependencias => ");
/*  848 */       for (int i = 0; i < dependencias.size(); i++) {
/*  849 */         System.out.println("\t" + ((IdentificadorInformacao)dependencias.get(i)).getIdInformacao());
/*      */       }
/*      */       
/*  852 */       List<IdentificadorInformacao> dependentes = mapeamento.obterInstanciasDependentes(dependencias);
/*  853 */       dependencias = null;
/*      */       
/*  855 */       System.out.println("Dependentes => ");
/*  856 */       for (int j = 0; j < dependentes.size(); j++) {
/*  857 */         System.out.println("\t" + ((IdentificadorInformacao)dependentes.get(j)).getIdInformacao());
/*      */       }
/*  859 */       dependentes = null;
/*      */ 
/*      */ 
/*      */       
/*  863 */       System.out.println("\n\n--- Teste - executarAtualizacoes()  -----------------------------------------");
/*      */       
/*  865 */       ((DeclaracaoMultiExercicio.DetalheRendPJ)dec.getRendPJ().getColecaoRendPJTitular().itens().get(0)).getRendRecebidoPJ().setConteudo("120000,00");
/*      */ 
/*      */       
/*  868 */       ((DeclaracaoMultiExercicio.DetalheRendPJ)dec.getRendPJ().getColecaoRendPJDependente().itens().get(0)).getRendRecebidoPJ().setConteudo("70000,00");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  875 */       System.out.println("\n\n--- Teste - excluir item de lista  -----------------------------------------");
/*      */       
/*  877 */       System.out.println("Fichas mapeadas:");
/*  878 */       for (FichaInfo ficha : mapeamento.fichas.values()) {
/*  879 */         System.out.println("\t" + ficha.idFicha);
/*      */       }
/*  881 */       System.out.println("Numero de fichas mapeadas: " + mapeamento.fichas.values().size());
/*      */ 
/*      */       
/*  884 */       System.out.println("Apagando um item em ColecaoRendPJDependente...");
/*      */       
/*  886 */       WeakReference<Object> w = new WeakReference(dec.getRendPJ().getColecaoRendPJDependente().itens().get(0));
/*  887 */       dec.getRendPJ().getColecaoRendPJDependente().itens().remove(0);
/*      */       
/*  889 */       while (w.get() != null) {
/*  890 */         System.gc();
/*  891 */         System.out.println("Esperando GC..");
/*      */       } 
/*      */       
/*  894 */       System.out.println("Fichas mapeadas:");
/*  895 */       for (FichaInfo ficha : mapeamento.fichas.values()) {
/*  896 */         System.out.println("\t" + ficha.idFicha);
/*      */       }
/*  898 */       System.out.println("Numero de fichas mapeadas: " + mapeamento.fichas.values().size());
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
/*  921 */       System.out.println("Excluindo Declaracao...");
/*  922 */       w = new WeakReference(dec);
/*  923 */       dec = null;
/*  924 */       while (w.get() != null) {
/*  925 */         System.gc();
/*  926 */         System.out.println("Esperando GC...");
/*      */       
/*      */       }
/*      */ 
/*      */     
/*      */     }
/*  932 */     catch (Exception e) {
/*  933 */       e.printStackTrace();
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void main(String[] args) {
/*  938 */     MapeadorCalculos mapeamento = new MapeadorCalculos();
/*      */     
/*      */     try {
/*  941 */       System.out.println("\n--- Teste - mapearInformacoes() -----------------------------------------------");
/*      */       
/*  943 */       mapeamento.mapearInformacoes(DeclaracaoMultiExercicio.class, "dec");
/*      */       
/*  945 */       System.out.println("\ndependentesMap:");
/*  946 */       for (String dependente : mapeamento.dependentesMap.keySet()) {
/*  947 */         System.out.println("\tdependente : " + dependente);
/*  948 */         for (String dependencia : mapeamento.dependentesMap.get(dependente)) {
/*  949 */           System.out.println("\t\tdependencia : " + dependencia);
/*      */         }
/*      */       } 
/*  952 */       System.out.println("\ndependenciasMap:");
/*  953 */       for (String dependencia : mapeamento.dependenciasMap.keySet()) {
/*  954 */         System.out.println("\tdependencia : " + dependencia);
/*  955 */         for (String dependente : mapeamento.dependenciasMap.get(dependencia)) {
/*  956 */           System.out.println("\t\tdependente : " + dependente);
/*      */         }
/*      */       } 
/*      */       
/*  960 */       DeclaracaoMultiExercicio dec = new DeclaracaoMultiExercicio();
/*      */ 
/*      */ 
/*      */       
/*  964 */       System.out.println("\n--- Teste - mapearInstanciasFichas() -----------------------------------------------");
/*      */       
/*  966 */       mapeamento.mapearInstanciasFichas(dec, null, "dec");
/*      */       
/*  968 */       System.out.println("Fichas mapeadas:");
/*  969 */       for (FichaInfo ficha : mapeamento.fichas.values()) {
/*  970 */         System.out.println("\t" + ficha.idFicha);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  975 */       System.out.println("\n--- Teste - Inserir Dados -----------------------------------------------");
/*      */       
/*  977 */       Objects.requireNonNull(dec); dec.getRendPJ().getColecaoRendPJTitular().itens().add(new DeclaracaoMultiExercicio.DetalheRendPJ(dec));
/*  978 */       Objects.requireNonNull(dec); dec.getRendPJ().getColecaoRendPJTitular().itens().add(new DeclaracaoMultiExercicio.DetalheRendPJ(dec));
/*  979 */       Objects.requireNonNull(dec); dec.getRendPJ().getColecaoRendPJTitular().itens().add(new DeclaracaoMultiExercicio.DetalheRendPJ(dec));
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  984 */       System.out.println("Fichas mapeadas:");
/*  985 */       for (FichaInfo ficha : mapeamento.fichas.values()) {
/*  986 */         System.out.println("\t" + ficha.idFicha);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  991 */       System.out.println("\n\n--- Teste - Alterar Dados  -----------------------------------------");
/*      */       
/*  993 */       ((DeclaracaoMultiExercicio.DetalheRendPJ)dec.getRendPJ().getColecaoRendPJTitular().itens().get(0)).getRendRecebidoPJ().setConteudo("50000,00");
/*  994 */       ((DeclaracaoMultiExercicio.DetalheRendPJ)dec.getRendPJ().getColecaoRendPJTitular().itens().get(1)).getRendRecebidoPJ().setConteudo("120000,00");
/*  995 */       ((DeclaracaoMultiExercicio.DetalheRendPJ)dec.getRendPJ().getColecaoRendPJTitular().itens().get(2)).getRendRecebidoPJ().setConteudo("70000,00");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1000 */       System.out.println("\n\n--- Teste - Excluir Dados  -----------------------------------------");
/*      */       
/* 1002 */       dec.getRendPJ().getColecaoRendPJTitular().itens().remove(1);
/*      */     
/*      */     }
/* 1005 */     catch (Exception e) {
/*      */       
/* 1007 */       e.printStackTrace();
/*      */     } 
/*      */   }
/*      */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irp\\util\calculos\MapeadorCalculos.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */