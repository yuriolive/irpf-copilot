/*     */ package serpro.ppgd.irpf.txt.importacao.gcap;
/*     */ 
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Stack;
/*     */ import org.w3c.dom.Element;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.SAXException;
/*     */ import serpro.ppgd.negocio.Colecao;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.util.LogPPGD;
/*     */ import serpro.ppgd.negocio.util.UtilitariosString;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ObjetoNegocioXMLReaderTagHandler
/*     */   extends DefaultTagHandler
/*     */ {
/*  20 */   private Element tipoArquivo = null;
/*  21 */   private Stack<Element> registros = new Stack<>();
/*  22 */   private Element campo = null;
/*     */ 
/*     */   
/*     */   private ObjetoNegocio objetoNegocio;
/*     */   
/*     */   private MapeamentoXmlReader mapeamento;
/*     */ 
/*     */   
/*     */   public ObjetoNegocioXMLReaderTagHandler(ObjetoNegocio objetoNegocio) {
/*  31 */     this.mapeamento = new MapeamentoXmlReader();
/*  32 */     this.objetoNegocio = objetoNegocio;
/*     */   }
/*     */   
/*     */   public ObjetoNegocioXMLReaderTagHandler(ObjetoNegocio objetoNegocio, String chaveMapeamentoXML) {
/*  36 */     this.mapeamento = new MapeamentoXmlReader(chaveMapeamentoXML);
/*  37 */     this.objetoNegocio = objetoNegocio;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void characters(String tagPath, String texto) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String obterTextoFormatado(String texto) {
/*  51 */     return texto.replaceAll("(\\A(\\n|\\s|\\t)+)|((\\n|\\s|\\t)+\\z)", "");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void endElement(String tagPath, String textoCompleto) throws SAXException {
/*  57 */     if (this.campo != null) {
/*     */       
/*  59 */       Element registro = this.registros.peek();
/*  60 */       String tagPathCampo = registro.getAttribute("Nome") + "/" + registro.getAttribute("Nome");
/*     */       
/*  62 */       if (tagPathCampo.toLowerCase().equals(tagPath)) {
/*  63 */         String atributo = this.campo.getAttribute("atributoObjetoNegocio");
/*  64 */         if (atributo != null && atributo.length() > 0) {
/*     */           
/*  66 */           Object o = obterCampo(this.objetoNegocio, atributo);
/*  67 */           if (o instanceof Informacao) {
/*     */             
/*  69 */             Informacao info = (Informacao)o;
/*  70 */             info.setConteudo(obterTextoFormatado(textoCompleto));
/*     */           } else {
/*  72 */             throw new SAXException(tagPath + "@" + tagPath + " não é um objeto Informacao.");
/*     */           } 
/*     */         } 
/*     */         
/*  76 */         this.campo = null;
/*     */       } 
/*  78 */     } else if (!this.registros.empty()) {
/*     */       
/*  80 */       Element registro = this.registros.peek();
/*  81 */       String atributo = registro.getAttribute("Nome");
/*  82 */       if (atributo.toLowerCase().equals(tagPath)) {
/*  83 */         registro = this.registros.pop();
/*     */       
/*     */       }
/*     */     
/*     */     }
/*  88 */     else if (this.tipoArquivo != null) {
/*  89 */       String nomeTipo = this.tipoArquivo.getAttribute("TipoArquivo");
/*  90 */       if (nomeTipo != null && nomeTipo.toLowerCase().equals(tagPath))
/*     */       {
/*  92 */         this.tipoArquivo = null;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startElement(String tagPath, Attributes attributes) throws SAXException {
/* 102 */     if (this.tipoArquivo == null) {
/*     */       
/* 104 */       this.tipoArquivo = this.mapeamento.getMapeamentoTipoArquivo(tagPath);
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 111 */       Element reg = this.mapeamento.getMapeamentoRegistro(tagPath, this.tipoArquivo);
/* 112 */       if (reg != null) {
/*     */         
/* 114 */         this.registros.push(reg);
/*     */ 
/*     */         
/* 117 */         String atributoColecao = reg.getAttribute("Colecao");
/* 118 */         if (atributoColecao != null && atributoColecao.trim().length() > 0) {
/*     */           
/* 120 */           Object o = obterCampo(this.objetoNegocio, atributoColecao);
/* 121 */           if (o instanceof Colecao) {
/* 122 */             Colecao col = (Colecao)o;
/*     */             
/* 124 */             col.novoObjeto();
/*     */           }
/*     */           else {
/*     */             
/* 128 */             throw new SAXException(tagPath + "@" + tagPath + " não é uma Coleção.");
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 133 */         for (int i = 0; i < attributes.getLength(); i++) {
/* 134 */           String nomeCampo = attributes.getQName(i);
/* 135 */           String valor = attributes.getValue(i);
/* 136 */           valor = UtilitariosString.retiraCaracteresEspeciais(UtilitariosString.removeAcentos(valor));
/* 137 */           String tagPathCampo = tagPath + "/" + tagPath;
/* 138 */           Element campo = this.mapeamento.getMapeamentoCampo(tagPathCampo, this.registros.peek());
/* 139 */           if (campo != null) {
/* 140 */             String atributo = campo.getAttribute("atributoObjetoNegocio");
/* 141 */             if (atributo != null && atributo.length() > 0) {
/*     */               
/* 143 */               Object o = obterCampo(this.objetoNegocio, atributo);
/* 144 */               if (o instanceof Informacao) {
/*     */                 
/* 146 */                 Informacao info = (Informacao)o;
/* 147 */                 info.setConteudo(valor);
/*     */               } else {
/* 149 */                 throw new SAXException(tagPathCampo + "@" + tagPathCampo + " não é um objeto Informacao.");
/*     */               }
/*     */             
/*     */             } 
/*     */           } 
/*     */         } 
/* 155 */       } else if (!this.registros.empty()) {
/*     */         
/* 157 */         Element camp = this.mapeamento.getMapeamentoCampo(tagPath, this.registros.peek());
/* 158 */         if (camp != null)
/*     */         {
/* 160 */           this.campo = camp;
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Object getObjectField(Object obj, String fieldName) {
/* 171 */     Object field = null;
/*     */     try {
/* 173 */       Class<?> c = obj.getClass();
/*     */       
/* 175 */       StringBuffer methodName = new StringBuffer("get");
/* 176 */       methodName.append(fieldName.substring(0, 1).toUpperCase());
/* 177 */       methodName.append(fieldName.substring(1));
/* 178 */       Method m = null;
/*     */       
/* 180 */       m = c.getMethod(methodName.toString(), new Class[0]);
/*     */       
/* 182 */       field = m.invoke(obj, new Object[0]);
/*     */     }
/* 184 */     catch (SecurityException e) {
/* 185 */       LogPPGD.erro(e.getMessage());
/*     */     }
/* 187 */     catch (NoSuchMethodException e) {
/*     */       
/* 189 */       LogPPGD.erro(e.getMessage());
/* 190 */     } catch (IllegalArgumentException e) {
/*     */       
/* 192 */       LogPPGD.erro(e.getMessage());
/* 193 */     } catch (IllegalAccessException e) {
/*     */       
/* 195 */       LogPPGD.erro(e.getMessage());
/* 196 */     } catch (InvocationTargetException e) {
/*     */       
/* 198 */       LogPPGD.erro(e.getMessage());
/*     */     } 
/*     */ 
/*     */     
/* 202 */     return field;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Object obterCampo(ObjetoNegocio obj, String nomeCampo) {
/* 207 */     String[] listaObj = nomeCampo.split("\\.");
/*     */     
/* 209 */     Object objAtual = obj;
/* 210 */     for (int i = 0; i < listaObj.length; i++) {
/* 211 */       String nomeCampoCurto = null;
/* 212 */       Integer indiceColecao = null;
/*     */       
/* 214 */       if (listaObj[i].matches("(\\w)+\\[(\\d)*\\]")) {
/*     */         
/* 216 */         int beginIndex = listaObj[i].lastIndexOf('[') + 1;
/* 217 */         int endIndex = listaObj[i].length() - 1;
/* 218 */         if (endIndex - beginIndex <= 0) {
/*     */           
/* 220 */           indiceColecao = new Integer("-1");
/*     */         } else {
/*     */           
/*     */           try {
/* 224 */             indiceColecao = new Integer(listaObj[i].substring(beginIndex, endIndex));
/* 225 */           } catch (Exception e) {
/*     */             
/* 227 */             LogPPGD.erro(e.getMessage());
/* 228 */             return null;
/*     */           } 
/*     */         } 
/*     */         
/* 232 */         nomeCampoCurto = listaObj[i].substring(0, beginIndex - 1);
/*     */       } else {
/* 234 */         nomeCampoCurto = listaObj[i];
/*     */       } 
/* 236 */       if (nomeCampoCurto != null) {
/* 237 */         Object o = null;
/*     */         try {
/* 239 */           o = getObjectField(objAtual, nomeCampoCurto);
/* 240 */         } catch (Exception e) {
/*     */           
/* 242 */           LogPPGD.erro(e.getMessage());
/* 243 */           return null;
/*     */         } 
/*     */         
/* 246 */         if (indiceColecao != null) {
/*     */           
/* 248 */           if (!(o instanceof Colecao))
/*     */           {
/*     */             
/* 251 */             return null;
/*     */           }
/* 253 */           int tqdElementos = ((Colecao)o).itens().size();
/* 254 */           if (indiceColecao.intValue() < 0) {
/* 255 */             indiceColecao = new Integer(tqdElementos - 1);
/*     */           }
/* 257 */           if (indiceColecao.intValue() >= 0 && indiceColecao.intValue() < tqdElementos) {
/* 258 */             o = ((Colecao)o).itens().get(indiceColecao.intValue());
/*     */           } else {
/*     */             
/* 261 */             return null;
/*     */           } 
/*     */         } 
/* 264 */         objAtual = o;
/*     */       }
/*     */       else {
/*     */         
/* 268 */         return null;
/*     */       } 
/*     */     } 
/*     */     
/* 272 */     return objAtual;
/*     */   }
/*     */   
/*     */   public ObjetoNegocio getObjetoNegocio() {
/* 276 */     return this.objetoNegocio;
/*     */   }
/*     */   
/*     */   public void setObjetoNegocio(ObjetoNegocio objetoNegocio) {
/* 280 */     this.objetoNegocio = objetoNegocio;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_importacao-exportacao.jar!\serpro\ppgd\irpf\txt\importacao\gcap\ObjetoNegocioXMLReaderTagHandler.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */