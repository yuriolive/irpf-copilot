/*      */ package serpro.ppgd.irpf.util.calculos;
/*      */ 
/*      */ import java.lang.ref.WeakReference;
/*      */ import java.lang.reflect.Field;
/*      */ import java.lang.reflect.Method;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.LinkedHashSet;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Objects;
/*      */ import java.util.regex.Matcher;
/*      */ import java.util.regex.Pattern;
/*      */ import serpro.ppgd.negocio.Colecao;
/*      */ import serpro.ppgd.negocio.Observador;
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
/*      */ public class MapeadorCalculos2
/*      */ {
/*   28 */   final Map<String, ArrayList<String>> dependenciasMap = new HashMap<>();
/*   29 */   final Map<String, ArrayList<String>> dependentesMap = new HashMap<>();
/*      */   
/*   31 */   final Map<String, CampoInformacao> camposDependentesMap = new HashMap<>();
/*      */   
/*   33 */   WeakReference<?> declaracaoWR = null;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected ArrayList<String> dependenciasAlteradas;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean mapearInstanciasFichas(Object ficha, String prefixo) throws MapeamentoDependenciasException {
/*  179 */     Class<?> classFicha = ficha.getClass();
/*  180 */     boolean ehFicha = classFicha.isAnnotationPresent((Class)FichaInformacao.class);
/*      */     
/*  182 */     if (prefixo == null || prefixo.isEmpty() || !prefixo.contains(".")) {
/*  183 */       this.declaracaoWR = new WeakReference(ficha);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  188 */     if (ehFicha) {
/*      */       
/*  190 */       String idFicha = prefixo.replaceAll("\\[\\d*\\]", "");
/*      */       
/*  192 */       Field[] fields = getFields(classFicha);
/*  193 */       for (Field field : fields) {
/*  194 */         if (!field.getName().startsWith("this$")) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  199 */           String nomeCampo = prefixo + "." + prefixo;
/*      */           
/*  201 */           if (field.isAnnotationPresent((Class)CampoInformacao.class)) {
/*      */             
/*  203 */             String idInformacao = idFicha + "." + idFicha;
/*      */             
/*  205 */             if (this.dependenciasMap.containsKey(idInformacao) && 
/*  206 */               !this.dependentesMap.containsKey(idInformacao)) {
/*  207 */               ObservadorDependenciaInformacao obs = new ObservadorDependenciaInformacao(nomeCampo);
/*      */               
/*      */               try {
/*  210 */                 boolean accessible = field.isAccessible();
/*  211 */                 field.setAccessible(true);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*      */               }
/*  222 */               catch (Exception e) {
/*  223 */                 throw new MapeamentoDependenciasException("Ocorreu um erro ao acessar Informacao (" + nomeCampo + ")", e);
/*      */               }
/*      */             
/*      */             }
/*      */           
/*      */           }
/*  229 */           else if (field.getType().isAnnotationPresent((Class)FichaInformacao.class)) {
/*      */             try {
/*      */               Object filha;
/*  232 */               boolean accessible = field.isAccessible();
/*  233 */               field.setAccessible(true);
/*      */               
/*      */               try {
/*  236 */                 filha = field.get(ficha);
/*      */               } finally {
/*  238 */                 field.setAccessible(accessible);
/*      */               } 
/*  240 */               mapearInstanciasFichas(filha, nomeCampo);
/*  241 */             } catch (Exception e) {
/*  242 */               throw new MapeamentoDependenciasException("Ocorreu um erro ao acessar Ficha (" + nomeCampo + ")", e);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*  247 */       ListaFichaInformacao listaFichaInfo = classFicha.<ListaFichaInformacao>getAnnotation(ListaFichaInformacao.class);
/*  248 */       if (listaFichaInfo != null && listaFichaInfo
/*  249 */         .classeFicha().isAnnotationPresent((Class)FichaInformacao.class)) {
/*  250 */         if (Colecao.class.isAssignableFrom(ficha.getClass())) {
/*  251 */           ObservadorLista obs = new ObservadorLista(prefixo);
/*  252 */           ((Colecao)ficha).addObservador(obs);
/*      */         } 
/*      */         
/*  255 */         String nomeLista = listaFichaInfo.lista();
/*  256 */         List<?> listaItens = obterListaFichas(ficha, nomeLista);
/*      */         
/*  258 */         for (Object item : listaItens) {
/*  259 */           String nomeCampo = prefixo + "._itens[" + prefixo + "]";
/*  260 */           mapearInstanciasFichas(item, nomeCampo);
/*      */         } 
/*      */       } 
/*      */       
/*  264 */       System.out.println("\t" + idFicha);
/*      */     } 
/*      */ 
/*      */     
/*  268 */     return ehFicha;
/*      */   }
/*      */   public List<?> obterListaFichas(Object ficha, String nomeLista) throws MapeamentoDependenciasException {
/*      */     List<?> listaItens;
/*  272 */     Class<?> classFicha = ficha.getClass();
/*      */     
/*      */     try {
/*  275 */       if (!nomeLista.isEmpty()) {
/*      */         
/*  277 */         Field field = getField(classFicha, nomeLista);
/*  278 */         if (field != null) {
/*  279 */           boolean accessible = field.isAccessible();
/*  280 */           field.setAccessible(true);
/*      */           try {
/*  282 */             listaItens = (List)field.get(ficha);
/*      */           } finally {
/*  284 */             field.setAccessible(accessible);
/*      */           } 
/*      */         } else {
/*      */           
/*  288 */           Method m = classFicha.getMethod(nomeLista, new Class[0]);
/*  289 */           listaItens = (List)m.invoke(ficha, new Object[0]);
/*      */         } 
/*      */       } else {
/*  292 */         listaItens = (List)ficha;
/*      */       } 
/*  294 */     } catch (Exception e) {
/*  295 */       throw new MapeamentoDependenciasException("Ocorreu um erro ao acessar lista de Fichas (" + classFicha.getName() + "." + nomeLista + ")", e);
/*      */     } 
/*      */     
/*  298 */     return listaItens;
/*      */   }
/*      */ 
/*      */   
/*      */   public class ObservadorLista
/*      */     extends Observador
/*      */   {
/*      */     String prefixo;
/*      */     
/*      */     public ObservadorLista(String prefixo) {
/*  308 */       this.prefixo = prefixo;
/*      */     }
/*      */ 
/*      */     
/*      */     public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/*      */       try {
/*  314 */         if ("ObjetoInserido".equals(nomePropriedade)) {
/*  315 */           String nomeCampo = this.prefixo + "._itens[" + this.prefixo + "]";
/*      */           
/*  317 */           System.out.println("Mapeando fichas:");
/*  318 */           MapeadorCalculos2.this.mapearInstanciasFichas(valorNovo, nomeCampo);
/*      */           
/*  320 */           MapeadorCalculos2.this.informarAlteracao("_quantidadeItensLista");
/*  321 */           MapeadorCalculos2.this.executarAtualizacoes();
/*  322 */         } else if ("ObjetoRemovido".equals(nomePropriedade)) {
/*      */ 
/*      */           
/*  325 */           MapeadorCalculos2.this.informarAlteracao("_quantidadeItensLista");
/*  326 */           MapeadorCalculos2.this.executarAtualizacoes();
/*      */         } 
/*  328 */       } catch (MapeamentoDependenciasException e) {
/*  329 */         e.printStackTrace();
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public class ObservadorDependenciaInformacao
/*      */     extends Observador
/*      */   {
/*      */     String campoInformacao;
/*      */     
/*      */     public ObservadorDependenciaInformacao(String campoInformacao) {
/*  340 */       this.campoInformacao = campoInformacao;
/*      */     }
/*      */ 
/*      */     
/*      */     public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/*  345 */       MapeadorCalculos2.this.informarAlteracao(this.campoInformacao);
/*      */       try {
/*  347 */         MapeadorCalculos2.this.executarAtualizacoes();
/*  348 */       } catch (MapeamentoDependenciasException e) {
/*  349 */         e.printStackTrace();
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
/*  371 */     Field[] thisFields = c.getDeclaredFields();
/*      */ 
/*      */     
/*  374 */     if (c.getSuperclass() != null) {
/*      */ 
/*      */ 
/*      */       
/*  378 */       Field[] superClassFields = getFields(c.getSuperclass());
/*      */ 
/*      */       
/*  381 */       Field[] allFields = new Field[superClassFields.length + thisFields.length];
/*      */ 
/*      */       
/*  384 */       System.arraycopy(superClassFields, 0, allFields, 0, superClassFields.length);
/*      */ 
/*      */       
/*  387 */       System.arraycopy(thisFields, 0, allFields, superClassFields.length, thisFields.length);
/*      */ 
/*      */       
/*  390 */       return allFields;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  395 */     return thisFields;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Field getField(Class<?> c, String name) {
/*      */     try {
/*  402 */       return c.getDeclaredField(name);
/*  403 */     } catch (Exception e) {
/*      */       
/*  405 */       if (c.getSuperclass() != null)
/*      */       {
/*      */         
/*  408 */         return getField(c.getSuperclass(), name);
/*      */       }
/*      */ 
/*      */       
/*  412 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public class MapeamentoDependenciasException extends Exception { private static final long serialVersionUID = 1L;
/*      */     
/*      */     public MapeamentoDependenciasException(String arg0, Throwable arg1) {
/*  419 */       super(arg0, arg1);
/*      */     }
/*      */     
/*      */     public MapeamentoDependenciasException(String arg0) {
/*  423 */       super(arg0);
/*      */     } }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String nomeInfoPelaReferencia(String nomeCampo, String referencia) throws MapeamentoDependenciasException {
/*  430 */     String nomeInfo = "";
/*      */     
/*  432 */     int lastIndex = nomeCampo.length();
/*  433 */     int i = 0;
/*  434 */     for (; i < referencia.length() && referencia.startsWith(".", i); 
/*  435 */       i++) {
/*  436 */       lastIndex = nomeCampo.lastIndexOf(".", lastIndex);
/*  437 */       if (lastIndex >= 0) {
/*  438 */         nomeInfo = nomeCampo.substring(0, lastIndex) + nomeCampo.substring(0, lastIndex);
/*  439 */         lastIndex--;
/*      */       } else {
/*  441 */         throw new MapeamentoDependenciasException("Identificacao de campo Informacao inconsistente: \"" + referencia + "\"");
/*      */       } 
/*      */     } 
/*      */     
/*  445 */     return nomeInfo;
/*      */   }
/*      */   public String referenciaPeloNomeInfo(String nomeCampo, String nomeInfo) throws MapeamentoDependenciasException {
/*  448 */     StringBuilder sbDependente = new StringBuilder();
/*  449 */     int lastIndex = nomeInfo.length();
/*      */     
/*  451 */     while ((lastIndex = nomeInfo.lastIndexOf(".", lastIndex)) >= 0) {
/*  452 */       String prefix = nomeInfo.substring(0, lastIndex);
/*  453 */       if (nomeCampo.startsWith(prefix)) {
/*      */         break;
/*      */       }
/*  456 */       sbDependente.append(".");
/*  457 */       lastIndex--;
/*      */     } 
/*  459 */     sbDependente.append(nomeCampo.substring(lastIndex));
/*      */     
/*  461 */     return sbDependente.toString();
/*      */   }
/*      */   
/*      */   public void mapearInformacoes(Class<?> classFicha, String prefixo) throws MapeamentoDependenciasException {
/*  465 */     String nomeCampo = null;
/*      */     
/*  467 */     FichaInformacao fichaInfo = classFicha.<FichaInformacao>getAnnotation(FichaInformacao.class);
/*      */     
/*  469 */     if (fichaInfo != null) {
/*      */       
/*  471 */       Field[] fields = getFields(classFicha);
/*  472 */       for (Field field : fields) {
/*  473 */         if (!field.getName().startsWith("this$")) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  478 */           nomeCampo = prefixo + "." + prefixo;
/*      */           
/*  480 */           CampoInformacao campoInfo = field.<CampoInformacao>getAnnotation(CampoInformacao.class);
/*  481 */           if (campoInfo != null) {
/*      */             
/*  483 */             String[] dependencias = campoInfo.dependencias();
/*  484 */             if (dependencias.length > 0) {
/*  485 */               System.out.println("\nCampo Dependente: " + nomeCampo);
/*      */               
/*  487 */               String idDependente = nomeCampo.replaceAll("\\[N?\\]", "");
/*  488 */               this.camposDependentesMap.put(idDependente, campoInfo);
/*      */ 
/*      */               
/*  491 */               for (String dependencia : dependencias) {
/*      */                 
/*  493 */                 String nomeDependencia = nomeInfoPelaReferencia(nomeCampo, dependencia);
/*  494 */                 System.out.println("\t" + nomeDependencia);
/*      */ 
/*      */                 
/*  497 */                 ArrayList<String> listDependencias = this.dependentesMap.get(idDependente);
/*  498 */                 if (listDependencias == null) {
/*  499 */                   listDependencias = new ArrayList<>();
/*  500 */                   this.dependentesMap.put(idDependente, listDependencias);
/*      */                 } 
/*      */                 
/*  503 */                 listDependencias.add(dependencia.replaceAll("\\[N?\\]", ""));
/*      */ 
/*      */                 
/*  506 */                 String idDependencia = nomeDependencia.replaceAll("\\[N?\\]", "");
/*  507 */                 ArrayList<String> listDependentes = this.dependenciasMap.get(idDependencia);
/*  508 */                 if (listDependentes == null) {
/*  509 */                   listDependentes = new ArrayList<>();
/*  510 */                   this.dependenciasMap.put(idDependencia, listDependentes);
/*      */                 } 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*  516 */                 String dependente = referenciaPeloNomeInfo(nomeCampo, nomeDependencia);
/*  517 */                 dependente = dependente.replaceAll("\\[N?\\]", "");
/*  518 */                 listDependentes.add(dependente);
/*      */               } 
/*      */             } 
/*      */           } else {
/*  522 */             mapearInformacoes(field.getType(), nomeCampo);
/*      */           } 
/*      */         } 
/*      */       } 
/*  526 */       ListaFichaInformacao listaFicha = classFicha.<ListaFichaInformacao>getAnnotation(ListaFichaInformacao.class);
/*  527 */       if (listaFicha != null) {
/*  528 */         nomeCampo = prefixo + "._itens[N]";
/*      */         
/*  530 */         mapearInformacoes(listaFicha.classeFicha(), nomeCampo);
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
/*      */   public List<String> obterDependentes(List<String> dependencias) throws MapeamentoDependenciasException {
/*  618 */     ArrayList<String> listaDependentes = new ArrayList<>();
/*  619 */     LinkedHashSet<String> mapaDependentes = new LinkedHashSet<>();
/*      */     
/*  621 */     ArrayList<String> listaDependencias = new ArrayList<>();
/*  622 */     listaDependencias.addAll(dependencias);
/*      */     
/*  624 */     int i = 0;
/*  625 */     while (!listaDependencias.isEmpty() && i < 20) {
/*  626 */       for (String dependencia : listaDependencias) {
/*  627 */         String idDependencia = dependencia.replaceAll("\\[\\d*\\]", "");
/*      */ 
/*      */ 
/*      */         
/*  631 */         ArrayList<String> refDependentes = this.dependenciasMap.get(idDependencia);
/*      */         
/*  633 */         if (refDependentes != null) {
/*  634 */           for (String refDependente : refDependentes) {
/*  635 */             String dependente = nomeInfoPelaReferencia(dependencia, refDependente);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  643 */             mapaDependentes.remove(dependente);
/*  644 */             mapaDependentes.add(dependente);
/*      */             
/*  646 */             listaDependentes.add(dependente);
/*      */           } 
/*      */         }
/*      */       } 
/*      */       
/*  651 */       listaDependencias.clear();
/*  652 */       listaDependencias.addAll(listaDependentes);
/*  653 */       listaDependentes.clear();
/*  654 */       i++;
/*      */     } 
/*      */     
/*  657 */     if (!listaDependencias.isEmpty()) {
/*  658 */       throw new MapeamentoDependenciasException("Foi detectado uma referencia circular.");
/*      */     }
/*      */     
/*  661 */     listaDependentes.addAll(mapaDependentes);
/*      */     
/*  663 */     return listaDependentes;
/*      */   }
/*      */   
/*      */   public void executarMetodoAtualizacao(String dependente, Integer[] listaIdItens) throws MapeamentoDependenciasException {
/*  667 */     Objects.requireNonNull(dependente);
/*  668 */     Objects.requireNonNull(listaIdItens);
/*      */     
/*  670 */     String idDependente = dependente.replaceAll("\\[\\d*\\]", "");
/*  671 */     CampoInformacao campo = this.camposDependentesMap.get(idDependente);
/*      */     
/*  673 */     Object[] parametros = new Object[listaIdItens.length + 2];
/*  674 */     Class<?>[] tipos = new Class[parametros.length];
/*      */     
/*  676 */     parametros[0] = this.declaracaoWR.get();
/*  677 */     parametros[1] = idDependente;
/*      */     
/*  679 */     tipos[0] = parametros[0].getClass();
/*  680 */     tipos[1] = parametros[1].getClass();
/*      */     
/*  682 */     for (int i = 2, i_ = 0; i < parametros.length; i++, i_++) {
/*  683 */       parametros[i] = listaIdItens[i_];
/*  684 */       tipos[i] = parametros[i].getClass();
/*      */     } 
/*      */     
/*  687 */     if (campo != null) {
/*  688 */       if (!campo.metodoAtualizacao().isEmpty()) {
/*      */         Method metodo;
/*      */         try {
/*  691 */           metodo = campo.classeAtualizacao().getMethod(campo.metodoAtualizacao(), tipos);
/*  692 */         } catch (Exception e) {
/*  693 */           throw new MapeamentoDependenciasException("Erro ao acessar metodo para atualizacao: " + campo.metodoAtualizacao(), e);
/*      */         } 
/*      */         
/*      */         try {
/*  697 */           metodo.invoke(null, parametros);
/*  698 */         } catch (Exception e) {
/*  699 */           throw new MapeamentoDependenciasException("Erro ao executar metodo de atualizacao \"" + metodo.getName() + "\"", e);
/*      */         } 
/*      */       } 
/*      */     } else {
/*  703 */       throw new MapeamentoDependenciasException("Campo Informacao nao mapeado: " + dependente);
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
/*      */   protected Integer[] obterIndicesItens(String informacao, Object instanciaFicha) throws MapeamentoDependenciasException {
/*  742 */     Object fichaAtual = instanciaFicha;
/*  743 */     Object fichaAnterior = null;
/*  744 */     List<Integer> indices = new ArrayList<>();
/*      */     
/*  746 */     String[] fichas = informacao.split("\\.");
/*  747 */     for (String ficha : fichas) {
/*  748 */       if (fichaAnterior != null) {
/*  749 */         if (ficha.startsWith("_itens[")) {
/*  750 */           ListaFichaInformacao listaFichaInfo = fichaAnterior.getClass().<ListaFichaInformacao>getAnnotation(ListaFichaInformacao.class);
/*  751 */           if (listaFichaInfo != null) {
/*  752 */             int hashCode; List<?> lista = obterListaFichas(fichaAnterior, listaFichaInfo.lista());
/*  753 */             int indice = 0;
/*      */             
/*      */             try {
/*  756 */               hashCode = Integer.parseInt(ficha.replaceAll("(_itens\\[)|(\\])", ""));
/*  757 */             } catch (Exception e) {
/*  758 */               throw new MapeamentoDependenciasException("", e);
/*      */             } 
/*  760 */             for (Object item : lista) {
/*  761 */               if (item.hashCode() == hashCode) {
/*  762 */                 indices.add(Integer.valueOf(indice));
/*  763 */                 fichaAtual = item;
/*      */                 break;
/*      */               } 
/*  766 */               indice++;
/*      */             } 
/*  768 */             if (indice >= lista.size()) {
/*  769 */               throw new MapeamentoDependenciasException("Ficha com hash (" + hashCode + ") nao encontrada na lista " + ficha);
/*      */             }
/*      */           } else {
/*  772 */             throw new MapeamentoDependenciasException("");
/*      */           } 
/*      */         } else {
/*  775 */           Field field = getField(fichaAnterior.getClass(), ficha);
/*      */           try {
/*  777 */             boolean access = field.isAccessible();
/*  778 */             field.setAccessible(true);
/*      */             try {
/*  780 */               fichaAtual = field.get(fichaAnterior);
/*      */             } finally {
/*  782 */               field.setAccessible(access);
/*      */             } 
/*  784 */           } catch (IllegalArgumentException|IllegalAccessException e) {
/*  785 */             throw new MapeamentoDependenciasException("Erro ao acessar ficha:" + field.getName(), e);
/*      */           } 
/*      */         } 
/*      */       }
/*      */       
/*  790 */       fichaAnterior = fichaAtual;
/*      */     } 
/*      */     
/*  793 */     return indices.<Integer>toArray(new Integer[indices.size()]);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void executarAtualizacoes(List<String> dependencias) throws MapeamentoDependenciasException {
/*  799 */     List<String> dependentes = obterDependentes(dependencias);
/*  800 */     for (String dependente : dependentes) {
/*      */ 
/*      */       
/*      */       try {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  812 */         executarMetodoAtualizacao(dependente, obterIndicesItens(dependente, this.declaracaoWR.get()));
/*  813 */       } catch (MapeamentoDependenciasException e) {
/*  814 */         throw e;
/*  815 */       } catch (Exception e) {
/*  816 */         throw new MapeamentoDependenciasException("Erro ao executar atualizacao do campo:" + dependente);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   public MapeadorCalculos2() {
/*  821 */     this.dependenciasAlteradas = new ArrayList<>();
/*      */   }
/*      */   public void informarAlteracao(String informacao) {
/*  824 */     this.dependenciasAlteradas.add(informacao);
/*      */   }
/*      */   
/*      */   public void executarAtualizacoes() throws MapeamentoDependenciasException {
/*  828 */     if (this.dependenciasAlteradas.isEmpty());
/*      */ 
/*      */ 
/*      */     
/*  832 */     executarAtualizacoes(this.dependenciasAlteradas);
/*  833 */     this.dependenciasAlteradas.clear();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void main_(String[] args) {
/*      */     try {
/*  845 */       System.out.println("dec._itens[824909230].rendPJ._itens[824909231].colecaoRendPJTitular._itens[824909232]._itens[824909233].rendRecebidoPJ"
/*      */           
/*  847 */           .replaceAll("\\][^\\[\\]]+\\[", ",")
/*  848 */           .replaceAll("^.+\\[", "")
/*  849 */           .replaceAll("\\].+$", ""));
/*      */       
/*  851 */       Matcher m = Pattern.compile("\\[\\d+\\]").matcher("dec.ficha1.ficha2.campoA");
/*  852 */       while (m.find()) {
/*  853 */         String s = m.group().replaceAll("\\[|\\]", "");
/*      */         
/*  855 */         System.out.println(s);
/*      */       } 
/*      */       
/*  858 */       System.out.println("_itens[824909230]".replaceAll("(_itens\\[)|(\\])", ""));
/*      */     
/*      */     }
/*  861 */     catch (Exception exception) {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void main(String[] args) {
/*  869 */     MapeadorCalculos2 mapeamento = new MapeadorCalculos2();
/*      */     
/*      */     try {
/*  872 */       System.out.println("\n--- Teste - mapearInformacoes() -----------------------------------------------");
/*  873 */       mapeamento.mapearInformacoes(DeclaracaoMultiExercicio.class, "dec");
/*  874 */       System.out.println("\ndependentesMap:");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  881 */       System.out.println("\ndependenciasMap:");
/*  882 */       for (String dependencia : mapeamento.dependenciasMap.keySet()) {
/*  883 */         System.out.println("\tdependencia : " + dependencia);
/*  884 */         for (String dependente : mapeamento.dependenciasMap.get(dependencia)) {
/*  885 */           System.out.println("\t\tdependente : " + dependente);
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*  890 */       DeclaracaoMultiExercicio dec = new DeclaracaoMultiExercicio();
/*      */ 
/*      */       
/*  893 */       System.out.println("\n--- Teste - mapearInstanciasFichas() -----------------------------------------------");
/*      */       
/*  895 */       Objects.requireNonNull(dec); dec.getRendPJ().getColecaoRendPJTitular().itens().add(new DeclaracaoMultiExercicio.DetalheRendPJ(dec));
/*      */       
/*  897 */       System.out.println("Mapeando fichas:");
/*  898 */       mapeamento.mapearInstanciasFichas(dec, "dec");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  907 */       System.out.println("\n--- Teste - mapearInstanciasFichas() -----------------------------------------------");
/*      */ 
/*      */       
/*  910 */       Objects.requireNonNull(dec); dec.getRendPJ().getColecaoRendPJTitular().itens().add(new DeclaracaoMultiExercicio.DetalheRendPJ(dec));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  916 */       Objects.requireNonNull(dec); dec.getRendPJ().getColecaoRendPJDependente().itens().add(new DeclaracaoMultiExercicio.DetalheRendPJ(dec));
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
/*  928 */       System.out.println("\n\n--- Teste - obterDependentes()  -----------------------------------------");
/*      */       
/*  930 */       ArrayList<String> dependencias = new ArrayList<>();
/*  931 */       dependencias.add("dec.rendPJ.colecaoRendPJTitular.niMaiorFontePagadora");
/*      */ 
/*      */ 
/*      */       
/*  935 */       System.out.println("Dependencias => ");
/*  936 */       for (int i = 0; i < dependencias.size(); i++) {
/*  937 */         System.out.println("\t" + (String)dependencias.get(i));
/*      */       }
/*      */       
/*  940 */       List<String> dependentes = mapeamento.obterDependentes(dependencias);
/*  941 */       dependencias = null;
/*      */       
/*  943 */       System.out.println("Dependentes => ");
/*  944 */       for (int j = 0; j < dependentes.size(); j++) {
/*  945 */         System.out.println("\t" + (String)dependentes.get(j));
/*      */       }
/*  947 */       dependentes = null;
/*      */ 
/*      */ 
/*      */       
/*  951 */       System.out.println("\n\n--- Teste - executarAtualizacoes()  -----------------------------------------");
/*      */       
/*  953 */       ((DeclaracaoMultiExercicio.DetalheRendPJ)dec.getRendPJ().getColecaoRendPJTitular().itens().get(0)).getRendRecebidoPJ().setConteudo("120000,00");
/*      */ 
/*      */       
/*  956 */       ((DeclaracaoMultiExercicio.DetalheRendPJ)dec.getRendPJ().getColecaoRendPJDependente().itens().get(0)).getRendRecebidoPJ().setConteudo("70000,00");
/*      */ 
/*      */       
/*  959 */       ((DeclaracaoMultiExercicio.DetalheRendPJ)dec.getRendPJ().getColecaoRendPJTitular().itens().get(1)).getNIFontePagadora().setConteudo("00000000000191");
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
/*      */     }
/* 1000 */     catch (Exception e) {
/* 1001 */       e.printStackTrace();
/*      */     } 
/*      */   }
/*      */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irp\\util\calculos\MapeadorCalculos2.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */