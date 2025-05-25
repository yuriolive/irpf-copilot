/*    */ package serpro.ppgd.irpf.rendIsentos;
/*    */ 
/*    */ import serpro.ppgd.cacheni.CacheNI;
/*    */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*    */ import serpro.ppgd.irpf.ObservadorEspacosDuplicados;
/*    */ import serpro.ppgd.irpf.ValidadorNaoNuloIRPF;
/*    */ import serpro.ppgd.irpf.util.MensagemUtil;
/*    */ import serpro.ppgd.negocio.Alfa;
/*    */ import serpro.ppgd.negocio.NI;
/*    */ import serpro.ppgd.negocio.Observador;
/*    */ import serpro.ppgd.negocio.RetornoValidacao;
/*    */ import serpro.ppgd.negocio.ValidadorDefault;
/*    */ import serpro.ppgd.negocio.ValidadorIf;
/*    */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorNI;
/*    */ 
/*    */ public class ItemQuadroTransferenciaPatrimonial extends ItemQuadroAuxiliar {
/* 17 */   private NI niDoadorEspolio = new NI(this, "CPF/CNPJ do doador/espólio");
/* 18 */   private Alfa nomeDoadorEspolio = new Alfa(this, "Nome do doador/espólio", 60);
/*    */ 
/*    */   
/*    */   public ItemQuadroTransferenciaPatrimonial(DeclaracaoIRPF dec) {
/* 22 */     super(dec);
/*    */     
/* 24 */     CacheNI.getInstancia().registrarNINome(this.niDoadorEspolio, this.nomeDoadorEspolio);
/*    */     
/* 26 */     getNiDoadorEspolio().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3));
/* 27 */     getNiDoadorEspolio().addValidador((ValidadorIf)new ValidadorNI((byte)3)
/*    */         {
/*    */           public RetornoValidacao validarImplementado()
/*    */           {
/* 31 */             setMensagemValidacao(MensagemUtil.getMensagem("campo_invalido", new String[] { getInformacao().getNomeCampo() }));
/*    */             
/* 33 */             return super.validarImplementado();
/*    */           }
/*    */         });
/*    */ 
/*    */     
/* 38 */     getNiDoadorEspolio().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*    */         {
/*    */           public RetornoValidacao validarImplementado()
/*    */           {
/* 42 */             if (ItemQuadroTransferenciaPatrimonial.this.getCpfBeneficiario().formatado().equals(getInformacao().formatado())) {
/* 43 */               return new RetornoValidacao(MensagemUtil.getMensagem("rendisentos_cpf_doador_espolio_igual_fonte_pagadora"), 
/* 44 */                   getSeveridade());
/*    */             }
/* 46 */             return null;
/*    */           }
/*    */         });
/*    */ 
/*    */     
/* 51 */     getNomeDoadorEspolio().addObservador((Observador)new ObservadorEspacosDuplicados());
/* 52 */     getNomeDoadorEspolio().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)2));
/*    */   }
/*    */   
/*    */   public Alfa getNomeDoadorEspolio() {
/* 56 */     return this.nomeDoadorEspolio;
/*    */   }
/*    */   
/*    */   public NI getNiDoadorEspolio() {
/* 60 */     return this.niDoadorEspolio;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void adicionaCamposParaPendencia() {
/* 69 */     super.adicionaCamposParaPendencia();
/* 70 */     this.camposPendencia.add(this.niDoadorEspolio);
/* 71 */     this.camposPendencia.add(this.nomeDoadorEspolio);
/*    */   }
/*    */ 
/*    */   
/*    */   public NI getNIFontePagadora() {
/* 76 */     return getNiDoadorEspolio();
/*    */   }
/*    */ 
/*    */   
/*    */   public Alfa getNomeFontePagadora() {
/* 81 */     return getNomeDoadorEspolio();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getTituloFichaDashboard() {
/* 87 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendIsentos\ItemQuadroTransferenciaPatrimonial.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */