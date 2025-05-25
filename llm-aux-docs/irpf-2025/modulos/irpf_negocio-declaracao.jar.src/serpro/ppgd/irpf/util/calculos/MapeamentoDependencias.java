/*     */ package serpro.ppgd.irpf.util.calculos;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ 
/*     */ 
/*     */ public class MapeamentoDependencias
/*     */ {
/*  17 */   HashMap<String, ArrayList<String>> dependenciasMap = new HashMap<>();
/*  18 */   HashMap<String, ArrayList<String>> dependentesMap = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @FichaInformacao
/*     */   public class FichaDeclaracao
/*     */   {
/* 150 */     MapeamentoDependencias.ListaRendPJ listaRendPJ = new MapeamentoDependencias.ListaRendPJ();
/*     */     
/* 152 */     MapeamentoDependencias.FichaClaculo fichaCalculo = new MapeamentoDependencias.FichaClaculo();
/*     */     @CampoInformacao
/* 154 */     private String campoA = "FichaDeclaracao::campoA";
/*     */ 
/*     */ 
/*     */     
/*     */     public FichaDeclaracao() {
/* 159 */       this.listaRendPJ.add(new MapeamentoDependencias.FichaDetalheRendPJ());
/* 160 */       this.listaRendPJ.add(new MapeamentoDependencias.FichaDetalheRendPJ());
/* 161 */       this.listaRendPJ.add(new MapeamentoDependencias.FichaDetalheRendPJ());
/*     */     } }
/*     */   
/*     */   @FichaInformacao
/*     */   public class FichaDetalheRend {
/*     */     @CampoInformacao
/* 167 */     private String campoA = "FichaDetalheRend:campoA";
/*     */     
/*     */     @CampoInformacao
/* 170 */     private String campoB = "FichaDetalheRend:campoB";
/*     */     
/*     */     @CampoInformacao(dependencias = {".campoA", ".campoB"})
/* 173 */     private String campoC = "FichaDetalheRend:campoC";
/*     */     
/*     */     @CampoInformacao(dependencias = {"fichaCalculo.campoA"})
/* 176 */     private String campoD = "FichaDetalheRend:campoD";
/*     */     
/*     */     @CampoInformacao(dependencias = {".._itens[].campoA"})
/* 179 */     private String campoE = "FichaDetalheRend:campoE";
/*     */   }
/*     */   
/*     */   @FichaInformacao
/*     */   @ListaFichaInformacao(classeFicha = FichaDetalheRend.class)
/*     */   public class ListaRend
/*     */     extends ArrayList<FichaDetalheRend> {
/*     */     private static final long serialVersionUID = 1L;
/*     */     @CampoInformacao(dependencias = {"._itens[].campoC"})
/* 188 */     private String campoA = "ListaRend:campoA";
/*     */   }
/*     */   
/*     */   @FichaInformacao
/*     */   public class FichaDetalheRendPJ
/*     */   {
/*     */     @CampoInformacao
/* 195 */     private String campoA = "FichaDetalheRendPJ:campoA";
/*     */     
/*     */     @CampoInformacao
/* 198 */     private String campoB = "FichaDetalheRendPJ:campoB";
/*     */     
/*     */     @CampoInformacao(dependencias = {".campoA", ".campoB"})
/* 201 */     private String campoC = "FichaDetalheRendPJ:campoC";
/*     */ 
/*     */ 
/*     */     
/* 205 */     private String campoD = "FichaDetalheRendPJ:campoD";
/*     */ 
/*     */     
/* 208 */     private String campoE = "FichaDetalheRendPJ:campoE";
/*     */     
/* 210 */     private MapeamentoDependencias.ListaRend listaRend = new MapeamentoDependencias.ListaRend();
/*     */     
/*     */     public FichaDetalheRendPJ() {
/* 213 */       this.listaRend.add(new MapeamentoDependencias.FichaDetalheRend());
/* 214 */       this.listaRend.add(new MapeamentoDependencias.FichaDetalheRend());
/* 215 */       this.listaRend.add(new MapeamentoDependencias.FichaDetalheRend());
/* 216 */       this.listaRend.add(new MapeamentoDependencias.FichaDetalheRend());
/*     */     }
/*     */   }
/*     */   
/*     */   @FichaInformacao
/*     */   @ListaFichaInformacao(classeFicha = FichaDetalheRendPJ.class)
/*     */   public class ListaRendPJ extends ArrayList<FichaDetalheRendPJ> {
/*     */     private static final long serialVersionUID = 1L;
/*     */     @CampoInformacao(dependencias = {"._itens[].campoC"})
/* 225 */     private String campoA = "ListaRendPJ:campoA";
/*     */   }
/*     */ 
/*     */   
/*     */   @FichaInformacao
/*     */   public class FichaClaculo
/*     */   {
/*     */     @CampoInformacao
/* 233 */     private String campoA = "FichaClaculo:campoA";
/*     */     
/*     */     @CampoInformacao
/* 236 */     private String campoB = "FichaClaculo:campoB";
/*     */     
/*     */     @CampoInformacao
/* 239 */     private String campoC = "FichaClaculo:campoC";
/*     */     
/*     */     @CampoInformacao(dependencias = {".campoA", ".campoB"})
/* 242 */     private String campoD = "FichaClaculo:campoD";
/*     */     
/*     */     @CampoInformacao(dependencias = {".campoB", ".campoC"})
/* 245 */     private String campoE = "FichaClaculo:campoE";
/*     */     
/*     */     @CampoInformacao(dependencias = {".campoD", ".campoE"})
/* 248 */     private String campoF = "FichaClaculo:campoF";
/*     */     
/*     */     @CampoInformacao(dependencias = {"campoA"})
/* 251 */     private String campoG = "FichaClaculo:campoG";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Field[] getFields(Class<?> c) {
/* 259 */     Field[] thisFields = c.getDeclaredFields();
/*     */ 
/*     */     
/* 262 */     if (c.getSuperclass() != null) {
/*     */ 
/*     */ 
/*     */       
/* 266 */       Field[] superClassFields = getFields(c.getSuperclass());
/*     */ 
/*     */       
/* 269 */       Field[] allFields = new Field[superClassFields.length + thisFields.length];
/*     */ 
/*     */       
/* 272 */       System.arraycopy(superClassFields, 0, allFields, 0, superClassFields.length);
/*     */ 
/*     */       
/* 275 */       System.arraycopy(thisFields, 0, allFields, superClassFields.length, thisFields.length);
/*     */ 
/*     */       
/* 278 */       return allFields;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 283 */     return thisFields;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Field getField(Class<?> c, String name) {
/*     */     try {
/* 290 */       return c.getDeclaredField(name);
/* 291 */     } catch (Exception e) {
/*     */       
/* 293 */       if (c.getSuperclass() != null)
/*     */       {
/*     */         
/* 296 */         return getField(c.getSuperclass(), name);
/*     */       }
/*     */ 
/*     */       
/* 300 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public class MapeamentoDependenciasException
/*     */     extends Exception {
/*     */     public MapeamentoDependenciasException(String arg0, Throwable arg1) {
/* 307 */       super(arg0, arg1);
/*     */     }
/*     */     private static final long serialVersionUID = 1L;
/*     */     public MapeamentoDependenciasException(String arg0) {
/* 311 */       super(arg0);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void mapearInformacoes(Class<?> ficha, String prefixo) throws MapeamentoDependenciasException {
/* 317 */     String nomeDependente = null;
/* 318 */     FichaInformacao fichaInfo = ficha.<FichaInformacao>getAnnotation(FichaInformacao.class);
/*     */     
/* 320 */     if (fichaInfo != null) {
/*     */       
/* 322 */       Field[] fields = getFields(ficha);
/* 323 */       for (Field field : fields) {
/*     */         
/* 325 */         nomeDependente = prefixo + "." + prefixo;
/*     */         
/* 327 */         CampoInformacao campoInfo = field.<CampoInformacao>getAnnotation(CampoInformacao.class);
/* 328 */         if (campoInfo != null) {
/*     */           
/* 330 */           String[] dependencias = campoInfo.dependencias();
/* 331 */           if (dependencias.length > 0) {
/* 332 */             System.out.println("\nCampo Dependente: " + nomeDependente);
/*     */             
/* 334 */             for (String dependencia : dependencias) {
/* 335 */               String nomeDependencia = null;
/*     */               
/* 337 */               int lastIndex = nomeDependente.length();
/* 338 */               int i = 0;
/* 339 */               for (; i < dependencia.length() && dependencia
/* 340 */                 .startsWith(".", i); 
/* 341 */                 i++) {
/* 342 */                 lastIndex = nomeDependente.lastIndexOf(".", lastIndex);
/* 343 */                 if (lastIndex >= 0) {
/* 344 */                   nomeDependencia = nomeDependente.substring(0, lastIndex) + nomeDependente.substring(0, lastIndex);
/* 345 */                   lastIndex--;
/*     */                 } else {
/* 347 */                   throw new MapeamentoDependenciasException("Identificacao de campo Informacao inconsistente: \"" + dependencia + "\"");
/*     */                 } 
/*     */               } 
/*     */ 
/*     */               
/* 352 */               if (nomeDependencia == null) {
/* 353 */                 nomeDependencia = "." + dependencia;
/* 354 */                 int index = nomeDependente.indexOf(".");
/* 355 */                 if (index > 0) {
/* 356 */                   nomeDependencia = nomeDependente.substring(0, index) + nomeDependente.substring(0, index);
/*     */                 }
/*     */               } 
/*     */               
/* 360 */               System.out.println("\t" + nomeDependencia);
/*     */ 
/*     */               
/* 363 */               String idDependente = nomeDependente.replaceAll("\\[N?\\]", "");
/* 364 */               ArrayList<String> listDependencias = this.dependentesMap.get(idDependente);
/* 365 */               if (listDependencias == null) {
/* 366 */                 listDependencias = new ArrayList<>();
/* 367 */                 this.dependentesMap.put(idDependente, listDependencias);
/*     */               } 
/* 369 */               listDependencias.add(nomeDependencia);
/*     */ 
/*     */               
/* 372 */               String idDependencia = nomeDependencia.replaceAll("\\[N?\\]", "");
/* 373 */               ArrayList<String> listDependentes = this.dependenciasMap.get(idDependencia);
/* 374 */               if (listDependentes == null) {
/* 375 */                 listDependentes = new ArrayList<>();
/* 376 */                 this.dependenciasMap.put(idDependencia, listDependentes);
/*     */               } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 383 */               String[] pathNomeDependente = nomeDependente.split("\\.");
/* 384 */               String[] pathNomeDependencia = nomeDependencia.split("\\.");
/* 385 */               for (int k = 0, j = 0; k < pathNomeDependente.length; k++, j++) {
/* 386 */                 if (j < pathNomeDependencia.length) {
/* 387 */                   if (!pathNomeDependente[k].equals(pathNomeDependencia[j])) {
/* 388 */                     if ("_itens[N]".equals(pathNomeDependente[k])) {
/* 389 */                       pathNomeDependente[k] = "_itens[]";
/*     */                     }
/*     */                     
/* 392 */                     if (!pathNomeDependente[k].equals(pathNomeDependencia[j])) {
/* 393 */                       j = pathNomeDependencia.length;
/*     */                     }
/*     */                   }
/*     */                 
/* 397 */                 } else if ("_itens[N]".equals(pathNomeDependente[k])) {
/* 398 */                   pathNomeDependente[k] = "_itens[]";
/*     */                 } 
/*     */               } 
/*     */               
/* 402 */               String nomeDependenteMapeado = String.join(".", (CharSequence[])pathNomeDependente);
/* 403 */               listDependentes.add(nomeDependenteMapeado);
/*     */             } 
/*     */           } 
/*     */         } else {
/* 407 */           mapearInformacoes(field.getType(), nomeDependente);
/*     */         } 
/*     */       } 
/*     */       
/* 411 */       ListaFichaInformacao listaFicha = ficha.<ListaFichaInformacao>getAnnotation(ListaFichaInformacao.class);
/* 412 */       if (listaFicha != null) {
/* 413 */         nomeDependente = prefixo + "._itens[N]";
/*     */         
/* 415 */         mapearInformacoes(listaFicha.classeFicha(), nomeDependente);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<String> obterDependentes(String... dependencias) {
/* 427 */     ArrayList<String> listDependentes = new ArrayList<>();
/* 428 */     LinkedHashSet<String> setDependentes = new LinkedHashSet<>();
/*     */     
/* 430 */     for (String dependencia : dependencias) {
/* 431 */       String idDependencia = dependencia.replaceAll("\\[(N?|\\d+)\\]", "");
/*     */ 
/*     */       
/* 434 */       ArrayList<String> dependentes = this.dependenciasMap.get(idDependencia);
/*     */       
/* 436 */       if (dependentes != null)
/*     */       {
/* 438 */         for (String dependente : dependentes) {
/* 439 */           String[] pathDependente = dependente.split("\\.");
/* 440 */           String[] pathDependencia = dependencia.split("\\.");
/*     */           
/* 442 */           for (int i = 0; i < pathDependente.length && i < pathDependencia.length; i++) {
/* 443 */             if (!pathDependente[i].equals(pathDependencia[i])) {
/* 444 */               if (pathDependencia[i].startsWith("_itens") && "_itens[N]"
/* 445 */                 .equals(pathDependente[i])) {
/* 446 */                 pathDependente[i] = pathDependencia[i];
/*     */               } else {
/*     */                 break;
/*     */               } 
/*     */             }
/*     */           } 
/* 452 */           String _dependente = String.join(".", (CharSequence[])pathDependente);
/*     */           
/* 454 */           setDependentes.remove(_dependente);
/* 455 */           setDependentes.add(_dependente);
/*     */         } 
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 461 */     if (setDependentes.size() > 0) {
/* 462 */       listDependentes.addAll(setDependentes);
/*     */       
/* 464 */       String[] dependentes = new String[setDependentes.size()];
/* 465 */       dependentes = (String[])setDependentes.toArray((Object[])dependentes);
/*     */       
/* 467 */       listDependentes.addAll(obterDependentes(dependentes));
/*     */     } 
/*     */     
/* 470 */     return listDependentes;
/*     */   }
/*     */   
/*     */   public ArrayList<String> obterDependencias(String dependente) {
/* 474 */     ArrayList<String> listDependencias = new ArrayList<>();
/*     */     
/* 476 */     String idDependente = dependente.replaceAll("\\[(N?|\\d+)\\]", "");
/*     */ 
/*     */     
/* 479 */     ArrayList<String> dependencias = this.dependentesMap.get(idDependente);
/*     */     
/* 481 */     if (dependencias != null)
/*     */     {
/* 483 */       for (String dependencia : dependencias) {
/* 484 */         String[] pathDependencia = dependencia.split("\\.");
/* 485 */         String[] pathDependente = dependente.split("\\.");
/*     */         
/* 487 */         for (int i = 0; i < pathDependencia.length && i < pathDependente.length; i++) {
/* 488 */           if (!pathDependencia[i].equals(pathDependente[i])) {
/* 489 */             if (pathDependente[i].startsWith("_itens") && "_itens[N]"
/* 490 */               .equals(pathDependencia[i])) {
/* 491 */               pathDependencia[i] = pathDependente[i];
/*     */             } else {
/*     */               break;
/*     */             } 
/*     */           }
/*     */         } 
/*     */         
/* 498 */         String _dependente = String.join(".", (CharSequence[])pathDependencia);
/*     */         
/* 500 */         listDependencias.add(_dependente);
/*     */       } 
/*     */     }
/*     */     
/* 504 */     return listDependencias;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> List<T> obterInformacao(Object ficha, String nomeInformacao) throws MapeamentoDependenciasException {
/* 509 */     String[] pathInfo = nomeInformacao.split("\\.");
/* 510 */     List<T> listaInformacao = new ArrayList<>();
/* 511 */     Object fichaAtual = ficha;
/* 512 */     for (int i = 0; i < pathInfo.length; i++) {
/* 513 */       Class<?> classeFicha = fichaAtual.getClass();
/* 514 */       Field[] fields = getFields(classeFicha);
/*     */       
/* 516 */       if (pathInfo[i].startsWith("_itens")) {
/* 517 */         ListaFichaInformacao listaFichaInfo = classeFicha.<ListaFichaInformacao>getAnnotation(ListaFichaInformacao.class);
/* 518 */         if (listaFichaInfo != null) {
/* 519 */           List<?> lista = null;
/* 520 */           String indice = pathInfo[i].replaceAll("_itens\\[|\\]", "");
/* 521 */           String nomeLista = listaFichaInfo.lista();
/*     */           
/* 523 */           if (!nomeLista.isEmpty()) {
/*     */             
/* 525 */             Field field = null;
/* 526 */             boolean accessible = false;
/*     */             try {
/* 528 */               field = getField(classeFicha, nomeLista);
/* 529 */               if (field != null) {
/* 530 */                 accessible = field.isAccessible();
/* 531 */                 field.setAccessible(true);
/* 532 */                 lista = (List)field.get(fichaAtual);
/*     */               } 
/* 534 */             } catch (Exception e) {
/* 535 */               throw new MapeamentoDependenciasException("Ocorreu um erro ao acessar Informacao.", e);
/*     */             } finally {
/* 537 */               if (field != null) {
/* 538 */                 field.setAccessible(accessible);
/*     */               }
/*     */             } 
/*     */ 
/*     */             
/* 543 */             if (lista == null) {
/*     */               try {
/* 545 */                 Method m = classeFicha.getMethod(nomeLista, new Class[0]);
/* 546 */                 lista = (List)m.invoke(fichaAtual, new Object[0]);
/* 547 */               } catch (Exception e) {
/* 548 */                 throw new MapeamentoDependenciasException("Ocorreu um erro ao acessar Informacao.", e);
/*     */               } 
/*     */             }
/*     */           } else {
/* 552 */             lista = (List)fichaAtual;
/*     */           } 
/*     */           
/* 555 */           if (indice.isEmpty()) {
/*     */             
/* 557 */             String info = String.join(".", Arrays.<CharSequence>copyOfRange((CharSequence[])pathInfo, i, pathInfo.length));
/* 558 */             for (Object item : lista) {
/* 559 */               listaInformacao.addAll(obterInformacao(item, info));
/*     */             }
/*     */             
/*     */             break;
/*     */           } 
/*     */           try {
/* 565 */             int ind = Integer.parseInt(indice);
/* 566 */             if (ind < lista.size()) {
/* 567 */               fichaAtual = lista.get(ind);
/*     */             } else {
/* 569 */               throw new MapeamentoDependenciasException("A lista (" + String.join(".", (CharSequence[])Arrays.copyOfRange(pathInfo, 0, i)) + ") está vazia.");
/*     */             } 
/* 571 */           } catch (MapeamentoDependenciasException e) {
/* 572 */             throw e;
/* 573 */           } catch (Exception e) {
/* 574 */             throw new MapeamentoDependenciasException("Ocorreu um erro ao acessar Informacao.", e);
/*     */           } 
/*     */         } else {
/*     */           
/* 578 */           throw new MapeamentoDependenciasException("A Classe (" + fichaAtual.getClass().getName() + ") não é uma @ListaFichaInformacao.");
/*     */         } 
/*     */       } else {
/* 581 */         for (Field field : fields) {
/* 582 */           if (field.getName().equals(pathInfo[i])) {
/*     */             
/* 584 */             if (field.isAnnotationPresent((Class)CampoInformacao.class)) {
/* 585 */               boolean accessible = field.isAccessible();
/*     */               try {
/* 587 */                 field.setAccessible(true);
/* 588 */                 listaInformacao.add((T)field.get(fichaAtual));
/* 589 */               } catch (Exception e) {
/* 590 */                 throw new MapeamentoDependenciasException("Ocorreu um erro ao acessar Informacao.", e);
/*     */               } finally {
/* 592 */                 field.setAccessible(accessible);
/*     */               }  break;
/*     */             } 
/*     */             try {
/* 596 */               fichaAtual = field.get(fichaAtual);
/* 597 */             } catch (Exception e) {
/* 598 */               throw new MapeamentoDependenciasException("Ocorreu um erro ao acessar Informacao.", e);
/*     */             } 
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 607 */     return listaInformacao;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void atualizarDependentes(Object ficha, String... dependentes) {
/* 618 */     for (String str : dependentes);
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
/*     */   public class ObservadorDependenciaInformacao
/*     */     extends Observador
/*     */   {
/*     */     String idInfoDependencia;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public ObservadorDependenciaInformacao(String idDependencia) {
/* 660 */       this.idInfoDependencia = idDependencia;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void notifica(Object arg0, String arg1, Object arg2, Object arg3) {
/* 666 */       IRPFFacade.getInstancia();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T> List<T> teste(T[] a) {
/* 674 */     List<T> lista = new ArrayList<>();
/* 675 */     for (T t : a) {
/* 676 */       lista.add(t);
/*     */     }
/*     */ 
/*     */     
/* 680 */     return lista;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void mainTeste1(String[] args) {
/* 686 */     MapeamentoDependencias mapeamento = new MapeamentoDependencias();
/*     */     
/*     */     try {
/* 689 */       mapeamento.mapearInformacoes(FichaDeclaracao.class, "dec");
/*     */       
/* 691 */       System.out.println("\n////////////////////////////////////////////////////");
/* 692 */       System.out.println("dependenciasMap: " + mapeamento.dependenciasMap);
/* 693 */       System.out.println("dependentesMap: " + mapeamento.dependentesMap);
/* 694 */       System.out.println("////////////////////////////////////////////////////");
/*     */ 
/*     */ 
/*     */       
/* 698 */       String dependencia = "dec.listaRendPJ._itens[2].campoA";
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 703 */       ArrayList<String> listDependentes = mapeamento.obterDependentes(new String[] { dependencia });
/* 704 */       System.out.println("\nLista dependentes de \"" + dependencia + "\":\n" + listDependentes);
/* 705 */       System.out.println("---------------------------------------------------");
/*     */ 
/*     */       
/* 708 */       Objects.requireNonNull(mapeamento); FichaDeclaracao ficha = new FichaDeclaracao();
/* 709 */       for (String dependente : listDependentes) {
/* 710 */         List<String> listInfo = mapeamento.obterInformacao(ficha, dependente);
/* 711 */         System.out.println("\nLista dependencias de \"" + dependente + "\" (Conteudo: " + listInfo + ") :");
/*     */ 
/*     */         
/* 714 */         ArrayList<String> listDependencias = mapeamento.obterDependencias(dependente);
/* 715 */         System.out.println("\nLista dependencias de \"" + dependente + "\":\n" + listDependencias);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 731 */     catch (Exception e) {
/*     */       
/* 733 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 742 */     MapeamentoDependencias mapeamento = new MapeamentoDependencias();
/*     */     
/*     */     try {
/* 745 */       mapeamento.mapearInformacoes(FichaDeclaracao.class, "dec");
/*     */       
/* 747 */       System.out.println("\n////////////////////////////////////////////////////");
/* 748 */       System.out.println("dependenciasMap: " + mapeamento.dependenciasMap);
/* 749 */       System.out.println("dependentesMap: " + mapeamento.dependentesMap);
/* 750 */       System.out.println("////////////////////////////////////////////////////");
/*     */ 
/*     */ 
/*     */       
/* 754 */       String dependencia = "dec.listaRendPJ._itens[2].campoA";
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 759 */       ArrayList<String> listDependentes = mapeamento.obterDependentes(new String[] { dependencia });
/* 760 */       System.out.println("\nLista dependentes de \"" + dependencia + "\":\n" + listDependentes);
/* 761 */       System.out.println("---------------------------------------------------");
/*     */ 
/*     */       
/* 764 */       Objects.requireNonNull(mapeamento); FichaDeclaracao ficha = new FichaDeclaracao();
/* 765 */       for (String dependente : listDependentes) {
/* 766 */         List<String> listInfo = mapeamento.obterInformacao(ficha, dependente);
/* 767 */         System.out.println("\nLista dependencias de \"" + dependente + "\" (Conteudo: " + listInfo + ") :");
/*     */ 
/*     */         
/* 770 */         ArrayList<String> listDependencias = mapeamento.obterDependencias(dependente);
/* 771 */         System.out.println("\nLista dependencias de \"" + dependente + "\":\n" + listDependencias);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 787 */     catch (Exception e) {
/*     */       
/* 789 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irp\\util\calculos\MapeamentoDependencias.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */