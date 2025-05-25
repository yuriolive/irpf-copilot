/*    */ package serpro.ppgd.irpf.atividaderural.brasil;
/*    */ 
/*    */ import serpro.ppgd.irpf.ValidadorNaoNuloIRPF;
/*    */ import serpro.ppgd.irpf.atividaderural.ImovelAR;
/*    */ import serpro.ppgd.irpf.atividaderural.ValidadorCIB;
/*    */ import serpro.ppgd.negocio.Alfa;
/*    */ import serpro.ppgd.negocio.ObjetoFicha;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ import serpro.ppgd.negocio.RetornoValidacao;
/*    */ import serpro.ppgd.negocio.ValidadorIf;
/*    */ import serpro.ppgd.negocio.util.UtilitariosString;
/*    */ 
/*    */ public class ImovelARBrasil extends ImovelAR implements ObjetoFicha {
/* 14 */   protected Alfa cib = new Alfa((ObjetoNegocio)this, "CIB (Nirf)")
/*    */     {
/*    */       public String formatado() {
/* 17 */         return UtilitariosString.formataNIRF(naoFormatado());
/*    */       }
/*    */ 
/*    */ 
/*    */ 
/*    */       
/*    */       public String getConteudoFormatado() {
/* 24 */         return UtilitariosString.formataNIRF(asString());
/*    */       }
/*    */ 
/*    */       
/*    */       public void setConteudo(String conteudo) {
/* 29 */         super.setConteudo(UtilitariosString.retiraMascara(conteudo.toUpperCase()));
/*    */       }
/*    */ 
/*    */       
/*    */       public void setConteudo(Alfa pValor) {
/* 34 */         setConteudo(pValor.naoFormatado().toUpperCase());
/*    */       }
/*    */     };
/*    */ 
/*    */ 
/*    */   
/*    */   public ImovelARBrasil() {
/* 41 */     getCIB().addValidador((ValidadorIf)new ValidadorCIB((byte)3)
/*    */         {
/*    */           
/*    */           public RetornoValidacao validarImplementado()
/*    */           {
/* 46 */             if (ImovelARBrasil.this.getCodigo().isVazio()) {
/* 47 */               return null;
/*    */             }
/*    */ 
/*    */             
/* 51 */             String codigo = ImovelARBrasil.this.getCodigo().getConteudoAtual(0);
/*    */             
/* 53 */             if (!codigo.trim().equals("10") && 
/* 54 */               !codigo.trim().equals("11") && 
/* 55 */               !codigo.trim().equals("12") && 
/* 56 */               !codigo.trim().equals("13") && 
/* 57 */               !codigo.trim().equals("14") && 
/* 58 */               !codigo.trim().equals("99"))
/*    */             {
/* 60 */               return null;
/*    */             }
/* 62 */             setMensagemValidacao("O campo indicativo CIB (Nirf) está inválido");
/* 63 */             return super.validarImplementado();
/*    */           }
/*    */         });
/*    */ 
/*    */     
/* 68 */     getCIB().setMaximoCaracteres(8);
/* 69 */     getCIB().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)2));
/*    */   }
/*    */ 
/*    */   
/*    */   public Alfa getCIB() {
/* 74 */     return this.cib;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getNomeAba() {
/* 79 */     return "Brasil";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getTituloFichaDashboard() {
/* 84 */     return "Atividade Rural - Dados do Imóvel Explorado";
/*    */   }
/*    */   
/*    */   public ImovelARBrasil obterCopia() {
/* 88 */     ImovelARBrasil copia = new ImovelARBrasil();
/* 89 */     copia.getCIB().setConteudo(getCIB());
/* 90 */     copia.getCodigo().setConteudo(getCodigo());
/* 91 */     copia.getNome().setConteudo(getNome());
/* 92 */     copia.getLocalizacao().setConteudo(getLocalizacao());
/* 93 */     copia.getArea().setConteudo(getArea());
/* 94 */     copia.getParticipacao().setConteudo(getParticipacao());
/* 95 */     copia.getCondicaoExploracao().setConteudo(getCondicaoExploracao());
/* 96 */     copia.getIndice().setConteudo(getIndice());
/* 97 */     copia.setParticipantesImovelAR(getParticipantesImovelAR().obterCopia());
/* 98 */     return copia;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\atividaderural\brasil\ImovelARBrasil.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */