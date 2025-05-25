/*     */ package serpro.ppgd.irpf.atividaderural;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import serpro.ppgd.irpf.ValidadorNaoNuloIRPF;
/*     */ import serpro.ppgd.irpf.gui.atividaderural.PainelDadosImovel;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.irpf.util.ObjetoComChaveIRPF;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.Codigo;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.ObjetoFicha;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ import serpro.ppgd.negocio.ValidadorIf;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorCodigo;
/*     */ 
/*     */ 
/*     */ public class ImovelAR
/*     */   extends ObjetoNegocio
/*     */   implements ObjetoComChaveIRPF, ObjetoFicha
/*     */ {
/*  26 */   protected Codigo codigo = new Codigo(this, "Código", CadastroTabelasIRPF.recuperarTipoAtividadesRural());
/*  27 */   protected Alfa nome = new Alfa(this, "Nome do Imóvel", 60);
/*  28 */   protected Alfa localizacao = new Alfa(this, "Localização do Imóvel", 55);
/*  29 */   protected Valor area = new Valor(this, "Área(ha)");
/*  30 */   protected Valor participacao = new Valor(this, "Participação(%)");
/*  31 */   protected Codigo condicaoExploracao = new Codigo(this, "Condição de Exploração", CadastroTabelasIRPF.recuperarCondicoesExploracao());
/*  32 */   protected ParticipantesImovelAR participantesImovelAR = new ParticipantesImovelAR();
/*  33 */   private Alfa indice = new Alfa(this, "Indice");
/*     */ 
/*     */ 
/*     */   
/*     */   public ImovelAR() {
/*  38 */     getParticipacao().setMaximoDigitosParteInteira(3);
/*  39 */     getArea().setMaximoDigitosParteInteira(9);
/*  40 */     getArea().converteQtdCasasDecimais(1);
/*     */     
/*  42 */     getCodigo().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, MensagemUtil.getMensagem("ar_imovel_codigo_branco"))
/*     */         {
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/*  46 */             if (ImovelAR.this.isVazio()) {
/*  47 */               return null;
/*     */             }
/*  49 */             return super.validarImplementado();
/*     */           }
/*     */         });
/*     */     
/*  53 */     getCodigo().addValidador((ValidadorIf)new ValidadorCodigo((byte)3, MensagemUtil.getMensagem("ar_imovel_codigo_branco")));
/*     */     
/*  55 */     getNome().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, MensagemUtil.getMensagem("ar_imovel_nome_branco"))
/*     */         {
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/*  59 */             if (ImovelAR.this.getCodigo().isVazio()) {
/*  60 */               return null;
/*     */             }
/*     */             
/*  63 */             String codigo = ImovelAR.this.getCodigo().getConteudoAtual(0);
/*     */             
/*  65 */             if (codigo.trim().equals("13") || codigo.trim().equals("14") || codigo.trim().equals("15") || codigo.trim().equals("99")) {
/*  66 */               setSeveridade((byte)2);
/*  67 */             } else if (codigo.trim().equals("10") || codigo.trim().equals("11") || codigo.trim().equals("12")) {
/*  68 */               setSeveridade((byte)3);
/*     */             } else {
/*  70 */               return null;
/*     */             } 
/*     */             
/*  73 */             return super.validarImplementado();
/*     */           }
/*     */         });
/*     */     
/*  77 */     getLocalizacao().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, MensagemUtil.getMensagem("ar_imovel_localizacao_branco"))
/*     */         {
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/*  81 */             if (ImovelAR.this.getCodigo().isVazio()) {
/*  82 */               return null;
/*     */             }
/*     */             
/*  85 */             String codigo = ImovelAR.this.getCodigo().getConteudoAtual(0);
/*     */             
/*  87 */             if (codigo.trim().equals("10") || codigo.trim().equals("11") || codigo.trim().equals("12")) {
/*  88 */               setSeveridade((byte)3);
/*  89 */             } else if (codigo.trim().equals("13") || codigo.trim().equals("14") || codigo.trim().equals("99")) {
/*  90 */               setSeveridade((byte)2);
/*     */             } else {
/*  92 */               return null;
/*     */             } 
/*     */             
/*  95 */             return super.validarImplementado();
/*     */           }
/*     */         });
/*     */     
/*  99 */     getArea().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, MensagemUtil.getMensagem("ar_imovel_area_branco"))
/*     */         {
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 103 */             if (ImovelAR.this.getCodigo().isVazio()) {
/* 104 */               return null;
/*     */             }
/*     */             
/* 107 */             String codigo = ImovelAR.this.getCodigo().getConteudoAtual(0);
/*     */             
/* 109 */             if (codigo.trim().equals("10") || codigo.trim().equals("11") || codigo.trim().equals("12")) {
/* 110 */               setSeveridade((byte)3);
/* 111 */             } else if (codigo.trim().equals("13") || codigo.trim().equals("14") || codigo.trim().equals("99")) {
/* 112 */               setSeveridade((byte)2);
/*     */             } else {
/* 114 */               return null;
/*     */             } 
/*     */             
/* 117 */             return super.validarImplementado();
/*     */           }
/*     */         });
/*     */     
/* 121 */     getParticipacao().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, 
/* 122 */           MensagemUtil.getMensagem("ar_imovel_participacao_exploracao_1"))
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 127 */             String codCondExploracao = ImovelAR.this.getCondicaoExploracao().getConteudoAtual(0);
/* 128 */             if (ImovelAR.this.getParticipacao().isVazio() && !codCondExploracao.equals("1")) {
/* 129 */               setMensagemValidacao(MensagemUtil.getMensagem("ar_imovel_participacao_exploracao_4"));
/* 130 */               return new RetornoValidacao(MensagemUtil.getMensagem("ar_imovel_participacao_exploracao_4"), (byte)3);
/*     */             } 
/* 132 */             if (codCondExploracao.equals("1")) {
/* 133 */               if (ImovelAR.this.getParticipacao().comparacao("=", "100,00")) {
/* 134 */                 return null;
/*     */               }
/* 136 */               setMensagemValidacao(MensagemUtil.getMensagem("ar_imovel_participacao_exploracao_1"));
/* 137 */               return new RetornoValidacao(MensagemUtil.getMensagem("ar_imovel_participacao_exploracao_1"), (byte)3);
/*     */             } 
/* 139 */             if ((codCondExploracao.equals("2") || codCondExploracao
/* 140 */               .equals("3")) && 
/* 141 */               ImovelAR.this.getParticipacao().comparacao("=", "100,00")) {
/* 142 */               setMensagemValidacao(MensagemUtil.getMensagem("ar_imovel_participacao_exploracao_2"));
/* 143 */               return new RetornoValidacao(MensagemUtil.getMensagem("ar_imovel_participacao_exploracao_2"), (byte)3);
/*     */             } 
/*     */ 
/*     */             
/* 147 */             if (ImovelAR.this.getParticipacao().comparacao("<", "100,00") && ImovelAR.this
/* 148 */               .getParticipantesImovelAR().isVazio()) {
/* 149 */               setMensagemValidacao(MensagemUtil.getMensagem("ar_imovel_participacao_exploracao_5"));
/* 150 */               return new RetornoValidacao(MensagemUtil.getMensagem("ar_imovel_participacao_exploracao_5"), (byte)3);
/*     */             } 
/* 152 */             if (ImovelAR.this.getParticipacao().comparacao(">", "100,00")) {
/* 153 */               setMensagemValidacao(MensagemUtil.getMensagem("ar_imovel_participacao_exploracao_3"));
/* 154 */               return new RetornoValidacao(MensagemUtil.getMensagem("ar_imovel_participacao_exploracao_3"), (byte)3);
/*     */             } 
/* 156 */             if (ImovelAR.this.getCondicaoExploracao().isVazio()) {
/* 157 */               return null;
/*     */             }
/*     */             
/* 160 */             return null;
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */     
/* 166 */     getCondicaoExploracao().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, 
/* 167 */           MensagemUtil.getMensagem("ar_imovel_condicao_exploracao_invalida"))
/*     */         {
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 171 */             if (ImovelAR.this.isVazio()) {
/* 172 */               return null;
/*     */             }
/* 174 */             return super.validarImplementado();
/*     */           }
/*     */         });
/*     */     
/* 178 */     getCondicaoExploracao().addValidador((ValidadorIf)new ValidadorCodigo((byte)3, 
/* 179 */           MensagemUtil.getMensagem("ar_imovel_condicao_exploracao_invalida")));
/*     */     
/* 181 */     getParticipacao().addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object arg0, String arg1, Object arg2, Object arg3) {
/* 184 */             if (ImovelAR.this.getParticipacao().comparacao("=", "100,00")) {
/* 185 */               ImovelAR.this.getParticipantesImovelAR().clear();
/*     */             }
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public Valor getArea() {
/* 193 */     return this.area;
/*     */   }
/*     */   
/*     */   public Codigo getCodigo() {
/* 197 */     return this.codigo;
/*     */   }
/*     */   
/*     */   public Codigo getCondicaoExploracao() {
/* 201 */     return this.condicaoExploracao;
/*     */   }
/*     */   
/*     */   public Alfa getLocalizacao() {
/* 205 */     return this.localizacao;
/*     */   }
/*     */   
/*     */   public Alfa getNome() {
/* 209 */     return this.nome;
/*     */   }
/*     */   
/*     */   public Valor getParticipacao() {
/* 213 */     return this.participacao;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVazio() {
/* 218 */     Iterator<Informacao> iterator = recuperarCamposInformacao().iterator();
/*     */     
/* 220 */     while (iterator.hasNext()) {
/* 221 */       Informacao informacao = iterator.next();
/* 222 */       if (!informacao.isVazio() && !informacao.getNomeCampo().equals("Indice")) {
/* 223 */         return false;
/*     */       }
/*     */     } 
/* 226 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 233 */     List<Informacao> retorno = recuperarCamposInformacao();
/* 234 */     Iterator<Informacao> iterator = getParticipantesImovelAR().recuperarListaCamposPendencia().iterator();
/*     */     
/* 236 */     while (iterator.hasNext()) {
/* 237 */       Informacao informacao = iterator.next();
/* 238 */       retorno.add(informacao);
/*     */     } 
/* 240 */     return retorno;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getChave() {
/* 245 */     return getCodigo().naoFormatado();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int compareTo(Object o) {
/* 251 */     ImovelAR imovel = (ImovelAR)o;
/* 252 */     int cod = imovel.getCodigo().asInteger();
/* 253 */     return cod - getCodigo().asInteger();
/*     */   }
/*     */ 
/*     */   
/*     */   public Alfa getIndice() {
/* 258 */     return this.indice;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClasseFicha() {
/* 263 */     return PainelDadosImovel.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 268 */     return "Exterior";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 273 */     return "Atividade Rural - Dados e Identificação do Imóvel Rural";
/*     */   }
/*     */   
/*     */   public ImovelAR obterCopia() {
/* 277 */     ImovelAR copia = new ImovelAR();
/* 278 */     copia.getCodigo().setConteudo(getCodigo());
/* 279 */     copia.getNome().setConteudo(getNome());
/* 280 */     copia.getLocalizacao().setConteudo(getLocalizacao());
/* 281 */     copia.getArea().setConteudo(getArea());
/* 282 */     copia.getParticipacao().setConteudo(getParticipacao());
/* 283 */     copia.getCondicaoExploracao().setConteudo(getCondicaoExploracao());
/* 284 */     copia.getIndice().setConteudo(getIndice());
/* 285 */     copia.setParticipantesImovelAR(getParticipantesImovelAR().obterCopia());
/* 286 */     return copia;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ParticipantesImovelAR getParticipantesImovelAR() {
/* 293 */     return this.participantesImovelAR;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParticipantesImovelAR(ParticipantesImovelAR participantesImovelAR) {
/* 300 */     this.participantesImovelAR = participantesImovelAR;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\atividaderural\ImovelAR.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */