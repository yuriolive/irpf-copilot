/*     */ package serpro.ppgd.irpf.util.calculos;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Stack;
/*     */ 
/*     */ public class MapeadorCalculosPPGD {
/*  10 */   final Map<String, ArrayList<String>> dependenciasMap = new HashMap<>();
/*  11 */   final Map<String, ArrayList<String>> dependentesMap = new HashMap<>();
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/*  16 */     String[] caminho = "...contrib.id".split("\\.\\.", -1);
/*     */ 
/*     */     
/*  19 */     for (String string : caminho) {
/*  20 */       System.out.println("'" + string + "'");
/*     */     }
/*     */ 
/*     */     
/*  24 */     MapeadorCalculosPPGD map = new MapeadorCalculosPPGD();
/*  25 */     Objects.requireNonNull(map); IdentificadorFicha ficha = new IdentificadorFicha("dec.contrib.id");
/*  26 */     System.out.println(ficha.obterId());
/*  27 */     System.out.println(ficha.obterFichaPorReferencia(".....bens").obterId());
/*  28 */     System.out.println(ficha.obterFichaPorReferencia("...endereco").obterId());
/*  29 */     System.out.println(ficha.obterFichaPorReferencia(".").obterId());
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
/*     */   public Field[] getFields(Class<?> c) {
/*  42 */     Field[] thisFields = c.getDeclaredFields();
/*     */ 
/*     */     
/*  45 */     if (c.getSuperclass() != null) {
/*     */ 
/*     */ 
/*     */       
/*  49 */       Field[] superClassFields = getFields(c.getSuperclass());
/*     */ 
/*     */       
/*  52 */       Field[] allFields = new Field[superClassFields.length + thisFields.length];
/*     */ 
/*     */       
/*  55 */       System.arraycopy(superClassFields, 0, allFields, 0, superClassFields.length);
/*     */ 
/*     */       
/*  58 */       System.arraycopy(thisFields, 0, allFields, superClassFields.length, thisFields.length);
/*     */ 
/*     */       
/*  61 */       return allFields;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  66 */     return thisFields;
/*     */   }
/*     */   
/*     */   public class IdentificadorCampo
/*     */   {
/*     */     private MapeadorCalculosPPGD.IdentificadorFicha ficha;
/*     */     private String campo;
/*     */     
/*     */     public IdentificadorCampo(String idFicha, String campo) {
/*  75 */       this.ficha = new MapeadorCalculosPPGD.IdentificadorFicha(idFicha);
/*  76 */       this.campo = campo;
/*     */     }
/*     */     
/*     */     public IdentificadorCampo obterCampoPorReferencia(String referencia) {
/*  80 */       String[] partes = referencia.split("\\:", -1);
/*     */       
/*  82 */       String idFicha = this.ficha.obterFichaPorReferencia(partes[0]).obterId();
/*     */       
/*  84 */       return new IdentificadorCampo(idFicha, partes[1]);
/*     */     }
/*     */     
/*     */     public String obterId() {
/*  88 */       return this.ficha.obterId() + ":" + this.ficha.obterId();
/*     */     }
/*     */     
/*     */     public String getNomeCampo() {
/*  92 */       return this.campo;
/*     */     }
/*     */   }
/*     */   
/*     */   public class IdentificadorFicha
/*     */   {
/*     */     private Stack<String> fichas;
/*     */     private String raiz;
/*     */     
/*     */     public IdentificadorFicha(String raiz) {
/* 102 */       this.fichas = new Stack<>();
/*     */       
/* 104 */       adicionarFicha(raiz);
/*     */       
/* 106 */       this.raiz = this.fichas.firstElement();
/*     */     }
/*     */     
/*     */     public void adicionarFicha(String ficha) {
/* 110 */       String[] caminho = ficha.split("\\.", -1);
/* 111 */       for (String nomeFicha : caminho) {
/* 112 */         if (!nomeFicha.isEmpty()) {
/* 113 */           this.fichas.push(nomeFicha);
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/*     */     public String obterId() {
/* 119 */       StringBuilder sbId = new StringBuilder(this.raiz);
/* 120 */       for (int i = 1; i < this.fichas.size(); i++) {
/* 121 */         sbId.append(".").append(this.fichas.elementAt(i));
/*     */       }
/*     */       
/* 124 */       return sbId.toString();
/*     */     }
/*     */ 
/*     */     
/*     */     public IdentificadorFicha obterFichaPorReferencia(String referencia) {
/* 129 */       IdentificadorFicha ficha = new IdentificadorFicha(obterId());
/*     */       
/* 131 */       String[] fichas = referencia.split("\\.\\.", -1); int i;
/* 132 */       for (i = 0; i < fichas.length - 1; i++) {
/* 133 */         ficha.fichas.pop();
/*     */       }
/*     */       
/* 136 */       fichas = fichas[fichas.length - 1].split("\\.", -1);
/* 137 */       for (i = 1; i < fichas.length; i++) {
/* 138 */         if (!fichas[i].isEmpty()) {
/* 139 */           ficha.fichas.push(fichas[i]);
/*     */         }
/*     */       } 
/*     */       
/* 143 */       return ficha;
/*     */     }
/*     */   }
/*     */   
/*     */   public class MapeamentoDependenciasException extends Exception {
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     public MapeamentoDependenciasException(String arg0, Throwable arg1) {
/* 151 */       super(arg0, arg1);
/*     */     }
/*     */     
/*     */     public MapeamentoDependenciasException(String arg0) {
/* 155 */       super(arg0);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mapearInformacoes(Class<?> classFicha, IdentificadorFicha ficha) throws MapeamentoDependenciasException {
/* 163 */     FichaInformacao fichaInfo = classFicha.<FichaInformacao>getAnnotation(FichaInformacao.class);
/*     */     
/* 165 */     if (fichaInfo != null) {
/*     */       
/* 167 */       Field[] fields = getFields(classFicha);
/* 168 */       for (Field field : fields) {
/* 169 */         if (!field.getName().startsWith("this$")) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 174 */           String nomeCampo = field.getName();
/*     */ 
/*     */           
/* 177 */           CampoInformacao campoInfo = field.<CampoInformacao>getAnnotation(CampoInformacao.class);
/*     */           
/* 179 */           if (campoInfo != null) {
/*     */             
/* 181 */             String[] dependencias = campoInfo.dependencias();
/* 182 */             if (dependencias.length > 0)
/*     */             {
/* 184 */               IdentificadorCampo idCampoDependente = new IdentificadorCampo(ficha.obterId(), nomeCampo);
/*     */               
/* 186 */               System.out.println("\nCampo Dependente: " + idCampoDependente.obterId());
/*     */ 
/*     */               
/* 189 */               for (String dependencia : dependencias)
/*     */               {
/* 191 */                 ArrayList<String> listDependencias = this.dependentesMap.get(idCampoDependente.obterId());
/* 192 */                 if (listDependencias == null) {
/* 193 */                   listDependencias = new ArrayList<>();
/* 194 */                   this.dependentesMap.put(idCampoDependente.obterId(), listDependencias);
/*     */                 } 
/* 196 */                 listDependencias.add(dependencia);
/*     */ 
/*     */                 
/* 199 */                 IdentificadorCampo idCampoDependencia = idCampoDependente.obterCampoPorReferencia(dependencia);
/* 200 */                 System.out.println("\t" + idCampoDependencia.obterId());
/*     */ 
/*     */                 
/* 203 */                 ArrayList<String> listDependentes = this.dependenciasMap.get(idCampoDependencia.obterId());
/* 204 */                 if (listDependentes == null) {
/* 205 */                   listDependentes = new ArrayList<>();
/* 206 */                   this.dependenciasMap.put(idCampoDependencia.obterId(), listDependentes);
/*     */                 
/*     */                 }
/*     */ 
/*     */               
/*     */               }
/*     */ 
/*     */             
/*     */             }
/*     */           
/*     */           }
/*     */           else {
/*     */             
/* 219 */             ficha.adicionarFicha(nomeCampo);
/* 220 */             mapearInformacoes(field.getType(), ficha);
/*     */           } 
/*     */         } 
/*     */       } 
/* 224 */       ListaFichaInformacao listaFicha = classFicha.<ListaFichaInformacao>getAnnotation(ListaFichaInformacao.class);
/* 225 */       if (listaFicha != null);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irp\\util\calculos\MapeadorCalculosPPGD.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */